package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.achieveit.systemtest.drivers.DriverSingleton.webDriver;

public class ChromeDriverStrategy implements DriverStrategy {
    public ChromeDriverStrategy() {
    }

    public WebDriver getWebDriver(){
        if(webDriver==null){
            System.setProperty("webdriver.chrome.driver", Constant.chromeDriverPosition);
            webDriver=new org.openqa.selenium.chrome.ChromeDriver();
        }
        return webDriver;
    } ;
}
