package com.example.pizzashop;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomePageBrowserTest {

    private static HtmlUnitDriver browser;
    @LocalServerPort
    private int port;


    @BeforeClass
    public static void setup(){
        browser = new HtmlUnitDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void teardown(){
        browser.quit();
    }

    @Test
    public void testHomePage(){
        String homePage = "http://localhost:" + port;
        browser.get(homePage);

        String titleText = browser.getTitle();
        Assert.assertEquals("Вкусные пицы", titleText);

        String h1Text = browser.findElementByTagName("h1").getText();
        Assert.assertEquals("Добро пожаловать...", h1Text);

        String imgSrc = browser.findElementByTagName("img").getAttribute("src");
        Assert.assertEquals(homePage + "/images/pizza0001.webp", imgSrc);
    }
}