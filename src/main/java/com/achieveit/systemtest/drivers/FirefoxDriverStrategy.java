package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.achieveit.systemtest.drivers.DriverSingleton.webDriver;

public class FirefoxDriverStrategy implements DriverStrategy {
    public FirefoxDriverStrategy() {
    }

    public WebDriver getWebDriver(){
        if(webDriver==null){
//            System.setProperty("webdriver.gecko.driver", Constant.firefoxDriverPosition);
            try {
                webDriver = new RemoteWebDriver(new URL(Constant.baseTestUrl), DesiredCapabilities.firefox());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return webDriver;
    } ;
}
