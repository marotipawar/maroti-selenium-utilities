package com.maroti;

import com.maroti.util.listener.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

@Listeners(TestListener.class)
@Test
public class MyTest {

    private App app;
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws URISyntaxException, IOException {
    app=new App();
    driver=app.driver();
    }

    @Test
    public void login(){
        Assert.assertEquals("Login","Login");
    }
    @Test
    public void home() throws InterruptedException {
       app.book();
    }
    @Test
    public void logout(){
        Assert.assertEquals("Logout","Logout");
    }
}
