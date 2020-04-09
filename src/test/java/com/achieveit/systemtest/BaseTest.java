package com.achieveit.systemtest;

import com.achieveit.systemtest.drivers.*;
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

    @Parameters("browser")
    @BeforeSuite
    public  void init2(String browser) {
        setDriverStrategy(browser);
    }

    @AfterSuite
    public  void quit() {
        getWebDriver().quit();
    }
}
