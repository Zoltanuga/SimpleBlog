package by.it.dao;

import by.it.dao.exceptions.DaoException;
import by.it.model.Comment;
import by.it.model.News;
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

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-dao-test.xml"})
@Transactional(value = "transactionManager")
public class CommentDaoTest {
    public static final int TEST_QUANTITY = 1;
    public static final String TEST_SORT_PARAM = "date";
    public static final int TEST_START_VALUE = 0;

    @Autowired
    private INewsDao newsDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ICommentDao commentDao;


    private Comment comment = new Comment();
    private User user = new User();
    private News news = new News();


    @Before
    public void initData() throws DaoException {
        user.setEmail("test@gmail.com");
        user.setPassword("Qwertyui$1");
        user.setName("Test");
        user.setSurname("Test");
        user.setRole(UserRole.MODERATOR);
        userDao.saveOrUpdate(user);

        news.setHeader("test");
        news.setText("test");
        news.setDate(new Date());
        news.setUser(user);
        newsDao.saveOrUpdate(news);

        comment.setNews(news);
        comment.setDate(new Date());
        comment.setUser(user);
        comment.setContent("test");
        commentDao.saveOrUpdate(comment);
    }

    @Test
    public void testCountComments() throws DaoException {
        long commentQuantity = commentDao.countComments();
        Assert.assertNotNull(commentQuantity);
        Assert.assertEquals(commentQuantity, TEST_QUANTITY);
        Assert.assertEquals(commentQuantity == (TEST_QUANTITY + 1), false);
    }

    @Test
    public void testCountCommentsById() throws DaoException {
        long commentQuantity = commentDao.countComments(news.getId());
        Assert.assertNotNull(commentQuantity);
        Assert.assertEquals(commentQuantity, TEST_QUANTITY);
        Assert.assertEquals(commentQuantity == (TEST_QUANTITY + 1), false);
    }

    @Test
    public void testObtainComments() throws DaoException {
        List<Comment> comments =
                commentDao.obtainComments(TEST_SORT_PARAM, TEST_START_VALUE, TEST_QUANTITY, news.getId());
        Assert.assertNotNull(comments);
        Assert.assertEquals(comments.get(TEST_START_VALUE), comment);
    }

}
