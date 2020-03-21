package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;

import static com.achieveit.systemtest.drivers.DriverSingleton.webDriver;

public class OperaDriverStrategy implements DriverStrategy {
    public OperaDriverStrategy() {
    }

    public WebDriver getWebDriver(){
        if(webDriver==null){
            System.setProperty("webdriver.opera.driver", Constant.operaDriverPosition);
            webDriver=new OperaDriver();
            // driver = new FirefoxDriver();   //Firefox浏览器

        }
        return webDriver;
    } ;
}
