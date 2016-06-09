package by.it.service;


import by.it.model.News;
import by.it.model.User;
import by.it.model.UserRole;
import by.it.services.IUserService;
import by.it.services.exeptions.IncorrectDataException;
import by.it.services.exeptions.ServiceException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-service-test.xml"})*/
public class UserServiceTest {
/*    @Autowired
    IUserService userService;
    private String correctEmail = "test@gmail.com";
    private String correctName = "Test";
    private String correctSurname = "Test";
    private String correctPassword = "Qwertyui&1";
    private User user = new User();


    @Before
    public void initData() throws ServiceException {
        user.setEmail(correctEmail);
        user.setPassword(correctPassword);
        user.setName(correctName);
        user.setSurname(correctSurname);
        user.setRole(UserRole.MODERATOR);
        userService.addUser(correctEmail, "Qwertyui&1", "Test", "Test", UserRole.MODERATOR);
    }*/


/*
    @Test
    public void testAddObtainUser() throws ServiceException {
        User user = userService.obtainUser(correctEmail);
        Assert.assertNotNull(user);
        Assert.assertEquals(user, this.user);
    }

    @Test
    public void testObtainUserList() throws ServiceException {

     */
/*   userService.addUser("testgmail.com","Qwe","Test","Test",UserRole.MODERATOR);
        userService.addUser("test@gmail.com","Qwertyui&1","test","Test",UserRole.MODERATOR);
        userService.addUser("test@gmail.com","Qwertyui&1","Test","test",UserRole.MODERATOR);*//*

        List<User> users = userService.obtainUserList();
        Assert.assertNotNull(users);
        int expectedQuantity = 1;
        Assert.assertEquals(users.size(), expectedQuantity);
    }

*/
/*
    @Test(expected = ServiceException.class)
    public void testDeleteUser() throws ServiceException {
        userService.deleteUser(user);
        User user = userService.obtainUser(correctEmail);
        Assert.assertNull(user);
    }
*//*


    @Test(expected = IncorrectDataException.class)
    public void testException() throws ServiceException {
        userService.addUser("testgmail.com", "Qwe", "Test", "Test", UserRole.MODERATOR);
        userService.addUser("test@gmail.com", "Qwertyui&1", "test", "Test", UserRole.MODERATOR);
        userService.addUser("test@gmail.com", "Qwertyui&1", "Test", "test", UserRole.MODERATOR);
    }

    @Test(expected = IncorrectDataException.class)
    public void testEmptyInput() throws ServiceException {
        userService.addUser("", "", "", "", UserRole.MODERATOR);
    }
*/

}
