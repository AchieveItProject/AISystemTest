package com.achieveit.systemtest;

import com.achieveit.systemtest.drivers.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static com.achieveit.systemtest.drivers.DriverSingleton.*;

public class BaseTest {
    private  static Map<String,Object> vars;

    public static Class webDriverClass=ChromeDriverStrategy.class;

    public static WebDriver webDriver;

    @BeforeClass
   public static void init(){
         DriverSingleton.setDriverStrategy(webDriverClass);
         webDriver=getWebDriver();
         vars=getVars();
    }
    @AfterClass
    public  static  void quit(){
        webDriver.quit();
    }
}
