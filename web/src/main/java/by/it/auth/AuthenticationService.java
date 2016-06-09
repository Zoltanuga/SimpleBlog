package by.it.auth;

import by.it.model.User;
import by.it.services.IUserService;
import by.it.services.exeptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("authService")
public class AuthenticationService implements UserDetailsService {
    private static Logger log = Logger.getLogger(AuthenticationService.class);

    @Autowired
    private IUserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.obtainUser(userName);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println("User : " + user);
        if (user == null) {
            log.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new CustomUser(user.getEmail(), user.getPassword(),user.getName(),user.getSurname(),
                getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

}
