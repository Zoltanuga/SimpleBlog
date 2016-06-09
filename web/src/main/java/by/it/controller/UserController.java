package by.it.controller;

import by.it.model.User;
import by.it.model.UserRole;
import by.it.services.IUserService;
import by.it.services.exeptions.IncorrectDataException;
import by.it.services.exeptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller

@RequestMapping(value = "/")
public class UserController {
    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/?logout&loggedOut=true";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @RequestMapping(value = "/register/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request) {
        String email = request.getParameter("emailRegister");
        String name = request.getParameter("nameRegister");
        String surname = request.getParameter("surnameRegister");
        String password = request.getParameter("passwordRegister");
        User user = null;
        try {
            user = userService.obtainUser(email);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return "redirect:/?errorRegistration=true";
        }
        if (user == null) {
            try {
                userService.addUser(email, password, name, surname, UserRole.MEMBER);
                return "redirect:/?successRegistration=true";
            }catch (IncorrectDataException e){
                log.error(e.getMessage());
                return "redirect:/register?isNotValidData=true";
            }
            catch (ServiceException e) {
                log.error(e.getMessage());
                return "redirect:/?errorRegistration=true";
            }
        } else {
            log.error("Warning: email is exist");
            return "redirect:/register?isUserExist=true";
        }
    }
    public static String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
