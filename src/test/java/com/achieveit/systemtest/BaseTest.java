package com.achieveit.systemtest;

import com.achieveit.systemtest.drivers.*;
import com.achieveit.systemtest.pages.LoginPage;
import com.achieveit.systemtest.pages.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import test.dataprovider.InstanceDataProviderTest;

import java.lang.management.ManagementFactory;
import java.util.Map;

import static com.achieveit.systemtest.drivers.DriverSingleton.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BaseTest {
//    public static Map<String, String> handlers;

//    public static Class webDriverClass = ChromeDriverStrategy.class;

//    public static WebDriver webDriver;
LoginPage loginPage;
    WelcomePage welcomePage;
    @Parameters("browser")
    @BeforeSuite
    public  void init2(String browser) {
        setDriverStrategy(browser);
    }
    public void login(String username,String password) {
        loginPage = new LoginPage();
        welcomePage= loginPage.goLoginPage("loginPage").inputUsernameDialog(username).inputPasswordDialog(password)
                .clickLoginButton("newPage");
        welcomePage.verifyTitleOnPageClassName();

    }
    public void login(String username,String password, String role) {
        login(username,password);

    }
    @AfterSuite
    public  void quit() {
        getWebDriver().quit();
    }
}
