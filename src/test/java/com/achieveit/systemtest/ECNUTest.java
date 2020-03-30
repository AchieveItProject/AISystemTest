package com.achieveit.systemtest;

import com.achieveit.systemtest.pages.LoginPage;
import com.achieveit.systemtest.pages.Page;
import com.achieveit.systemtest.pages.WelcomePage;
import org.hamcrest.core.Is;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.achieveit.systemtest.drivers.DriverSingleton.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ECNUTest extends BaseTest{

@BeforeMethod
public void initSub(){
    getWebDriver().get("http://www.ecnu.edu.cn/");
}

    @Test
    public void loginbasictest() {
        try {

           Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement we= getWebDriver().findElement(By.className("logo"));
       assertThat(we.getAttribute("title"), is("华东师范大学首页"));
    }


    @AfterMethod
    public void pageDestroy() {
//        webDriver.close();
    }
}
