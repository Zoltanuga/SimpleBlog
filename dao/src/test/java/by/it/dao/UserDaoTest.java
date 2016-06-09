package by.it.dao;

import by.it.dao.exceptions.DaoException;
import by.it.model.User;
import by.it.model.UserRole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-dao-test.xml"})
@Transactional(value = "transactionManager")
public class UserDaoTest {
    @Autowired
    IUserDao userDao;
    private User user = new User();

    @Before
    public void initUser() throws DaoException {
        user.setEmail("test@gmail.com");
        user.setPassword("Qwertyui$1");
        user.setName("Test");
        user.setSurname("Test");
        user.setRole(UserRole.MODERATOR);
        userDao.saveOrUpdate(user);
    }

    @Test()
    public void testObtainUserList() throws DaoException {
        List<User> users = userDao.obtainUserList();
        Assert.assertNotNull(users);
        Assert.assertEquals(users.get(0), user);
    }

    @Test()
    public void testGet() throws DaoException {
        User secondUser = userDao.get(user.getEmail());
        Assert.assertNotNull(secondUser);
        Assert.assertEquals(user, secondUser);
    }

    @Test()
    public void testLoad() throws DaoException {
        Assert.assertNotNull(userDao.load(user.getEmail()));
    }

    @Test(expected = DaoException.class)
    public void testDelete() throws DaoException {
        userDao.delete(user);
        Assert.assertNull(userDao.load(user.getEmail()));
    }

    @Test(expected = DaoException.class)
    public void testGetError() throws DaoException {
        Assert.assertNull(userDao.get(0));
    }

    @Test(expected = DaoException.class)
    public void testSaveOrUpdateError() throws DaoException {
        userDao.saveOrUpdate(new User());
    }

}
