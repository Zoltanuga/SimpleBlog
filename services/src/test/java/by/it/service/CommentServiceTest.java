package by.it.service;

import by.it.model.Comment;
import by.it.model.News;
import by.it.model.User;
import by.it.model.UserRole;
import by.it.services.ICommentService;
import by.it.services.INewsService;
import by.it.services.IUserService;
import by.it.services.exeptions.IncorrectDataException;
import by.it.services.exeptions.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-service-test.xml"})
public class CommentServiceTest {
    @Autowired
    INewsService newsService;
    @Autowired
    IUserService userService;
    @Autowired
    ICommentService commentService;
    private String correctEmail = "test@gmail.com";
    private String correctName = "Test";
    private String correctSurname = "Test";
    private String correctPassword = "Qwertyui$1";
    private String correctText = "test";
    private User user = new User();
    private News news = new News();
    private Comment comment = new Comment();


    @Before
    public void initData() throws ServiceException {
        user.setEmail(correctEmail);
        user.setPassword(correctPassword);
        user.setName(correctName);
        user.setSurname(correctSurname);
        user.setRole(UserRole.MODERATOR);

        news.setHeader(correctText);
        news.setText(correctText);
        news.setUser(user);

        comment.setNews(news);
        comment.setDate(new Date());
        comment.setUser(user);
        comment.setContent(correctText);

        userService.addUser(correctEmail, correctPassword, correctName, correctSurname, UserRole.MODERATOR);
        newsService.addNews(correctText, correctText, correctEmail);
    }

    @Test
    public void testCommentService() throws ServiceException {

        String sortParam = "date";
        List<News> newsList = newsService.obtainNewsList(sortParam, 0, 1);
        long newsId = newsList.get(0).getId();
        commentService.addComment(correctText, correctEmail, (int) newsId);
        List<Comment> comments = commentService.obtainComments(sortParam, 0, 1, (int) newsId);
        Assert.assertNotNull(comments);
        Assert.assertEquals(comments.size(), 1);
        Assert.assertEquals(commentService.countComments(), 1);
        Assert.assertEquals(commentService.countComments((int) newsId), 1);
        long commentId = comments.get(0).getCommentId();
        Comment comment = commentService.obtainComment((int) commentId);
        Assert.assertNotNull(comment);
        Assert.assertEquals(comment, comments.get(0));
        commentService.updateComment((int) commentId, "updated");
        Comment updatedComment = commentService.obtainComment((int) commentId);
        Assert.assertNotNull(updatedComment);
        Assert.assertEquals(comment.equals(updatedComment),false);
        commentService.deleteComment((int) commentId);
        updatedComment = commentService.obtainComment((int) commentId);
        Assert.assertNull(updatedComment);
    }
    @Test(expected = ServiceException.class)
    public void testDeleteComment() throws ServiceException {
        commentService.deleteComment(0);
    }



         @Test
       public void testNewsService() throws ServiceException {
           List<News> newsList = newsService.obtainNewsList("date", 0, 1);
           Assert.assertNotNull(newsList);
           Assert.assertEquals(newsList.size(), 1);
           news.setId(newsList.get(0).getId());
           news.setDate(newsList.get(0).getDate());
           Assert.assertEquals(newsList.get(0), news);

           newsService.updateNews(news.getId(), "updated", "updated");
           News news = newsService.obtainNews(this.news.getId());
           Assert.assertNotNull(news);
           Assert.assertEquals(news.equals(this.news), false);
           Assert.assertEquals(newsService.countNews(), 1);


           newsService.deleteNews(this.news.getId());
           news = newsService.obtainNews(this.news.getId());
           Assert.assertNull(news);
       }
/*       @Test
       public void testCountNews() throws ServiceException {
           long newsQuantity = newsService.countNews();
           Assert.assertNotNull(newsQuantity);
           Assert.assertEquals(newsQuantity, 1);
       }*/
    @Test(expected = ServiceException.class)
    public void testExceptionOnNews() throws ServiceException {
        newsService.deleteNews(0);
    }

    @Test
    public void testAddObtainUser() throws ServiceException {
        User user = userService.obtainUser(correctEmail);
        junit.framework.Assert.assertNotNull(user);
        junit.framework.Assert.assertEquals(user, this.user);
    }

    @Test
    public void testObtainUserList() throws ServiceException {


        List<User> users = userService.obtainUserList();
        junit.framework.Assert.assertNotNull(users);
        int expectedQuantity = 1;
        junit.framework.Assert.assertEquals(users.size(), expectedQuantity);
    }


    @Test(expected = IncorrectDataException.class)
    public void testExceptionOnUser() throws ServiceException {
        userService.addUser("testgmail.com", "Qwe", "Test", "Test", UserRole.MODERATOR);
        userService.addUser("test@gmail.com", "Qwertyui&1", "test", "Test", UserRole.MODERATOR);
        userService.addUser("test@gmail.com", "Qwertyui&1", "Test", "test", UserRole.MODERATOR);
    }

    @Test(expected = IncorrectDataException.class)
    public void testEmptyInput() throws ServiceException {
        userService.addUser("", "", "", "", UserRole.MODERATOR);
    }
}
