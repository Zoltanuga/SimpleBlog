package by.it.service;


import by.it.model.News;
import by.it.model.User;
import by.it.model.UserRole;
import by.it.services.INewsService;
import by.it.services.IUserService;
import by.it.services.exeptions.ServiceException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-service-test.xml"})*/
public class NewsServiceTest  {
/*
    @Autowired
    INewsService newsService;
    @Autowired
    IUserService userService;
    private String correctEmail = "test@gmail.com";
    private String correctName = "Test";
    private String correctSurname = "Test";
    private String correctPassword = "Qwertyui&1";
    private String correctText = "test";
    private User user = new User();
    private News news = new News();


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

        userService.addUser(correctEmail, correctPassword, correctName, correctSurname, UserRole.MODERATOR);
        newsService.addNews(correctText, correctText, correctEmail);

    }*/

/*
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
        System.out.println("counted   "+newsService.countNews());
        Assert.assertEquals(newsService.countNews(), 3);


        newsService.deleteNews(this.news.getId());
        news = newsService.obtainNews(this.news.getId());
        Assert.assertNull(news);
    }
*/



 /*   @Test
    public void testCountNews() throws ServiceException {
        long newsQuantity = newsService.countNews();
        Assert.assertNotNull(newsQuantity);
        Assert.assertEquals(newsQuantity, 0);
    }*/
  /*  @Test(expected = ServiceException.class)
    public void testException() throws ServiceException {
       newsService.deleteNews(0);
    }*/

}
