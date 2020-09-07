package com.softserve.edu.greencity.ui.tools.jdbc;


import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsTagsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

    // @Test
    public void checkApp1() {
        System.out.println("***surefire.application.secret = " + System.getProperty("surefire.application.secret"));
        Assert.assertTrue(true);
    }

    // @Test
    public void checkApp2() {
        System.out.println("***surefire.application.password = " + System.getProperty("surefire.application.password"));
        System.out.println("***System.getenv().MY_PASSWORD = " + System.getenv().get("MY_PASSWORD"));
        System.out.println("***System.getenv().MY_IDE = " + System.getenv().get("MY_IDE"));
        Assert.assertTrue(true);
    }

    //@Test
    public void readAllNews() {
        EcoNewsService ecoNewsService = new EcoNewsService();
        for (EcoNewsEntity ecoNewsEntity : ecoNewsService.getAllNews()) {
            System.out.println(ecoNewsEntity);
        }
    }
    
    //@Test
    public void readNewsByTitle() {
        EcoNewsService ecoNewsService = new EcoNewsService();
        for (EcoNewsEntity ecoNewsEntity : ecoNewsService.getNewsByTitle("asdfghjkl")) {
            System.out.println(ecoNewsEntity);
        }
    }
    
    //@Test
    public void readNewsById() {
        EcoNewsService ecoNewsService = new EcoNewsService();
        System.out.println(ecoNewsService.getNewsById(6));
    }
    
    //@Test
    public void readEcoNewsId() {
        EcoNewsService ecoNewsService = new EcoNewsService();
        for (EcoNewsTagsEntity ecoNewsTagsEntity : ecoNewsService.getNewsTagsByEcoNewsId(1701)) {
            System.out.println(ecoNewsTagsEntity);
        }
    }
    
    @Test
    public void deleteNewsByTitle() {
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.deleteNewsByTitle("asdfghjkl");
        //readAllNews(); // Error
    }
    
    //@Test
    public void readNewsCount() {
        EcoNewsService ecoNewsService = new EcoNewsService();
        System.out.println("***count = " + ecoNewsService.getNewsCount());
    }
    
}
