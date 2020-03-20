package com.achieveit.systemtest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static com.achieveit.systemtest.DriverSingleton.*;

public class BaseTest {
    private static WebDriver webDriver;
    private  static Map<String,Object> vars;
    @BeforeClass
   public static void init(){
        webDriver=getWebDriver();
        vars=getVars();
    }
    @AfterClass
    public  static  void quit(){
        webDriver.quit();
    }
}
