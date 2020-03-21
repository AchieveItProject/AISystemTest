package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import static com.achieveit.systemtest.drivers.DriverSingleton.webDriver;

public class FirefoxDriverStrategy implements DriverStrategy {
    public FirefoxDriverStrategy() {
    }

    public WebDriver getWebDriver(){
        if(webDriver==null){
            System.setProperty("webdriver.firefox.driver", Constant.firefoxDriverPosition);
            webDriver= new FirefoxDriver();

        }
        return webDriver;
    } ;
}
