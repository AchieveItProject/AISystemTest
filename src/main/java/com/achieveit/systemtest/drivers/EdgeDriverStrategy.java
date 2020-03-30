package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.achieveit.systemtest.drivers.DriverSingleton.webDriver;

public class EdgeDriverStrategy implements DriverStrategy {
    public EdgeDriverStrategy() {
    }

    public WebDriver getWebDriver(){
        if(webDriver==null){
            synchronized (DriverSingleton.class){
                if(webDriver==null) {
//            System.setProperty("webdriver.edge.driver", Constant.edgeDriverPosition);
                    try {
                        webDriver = new RemoteWebDriver(new URL(Constant.baseTestUrl), DesiredCapabilities.edge());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }}
        }
        return webDriver;
    } ;
}
