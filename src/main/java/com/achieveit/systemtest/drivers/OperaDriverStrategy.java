package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.achieveit.systemtest.drivers.DriverSingleton.webDriver;

public class OperaDriverStrategy implements DriverStrategy {
    public OperaDriverStrategy() {
    }

    public WebDriver getWebDriver() {
        if (webDriver == null) {
//            System.setProperty("webdriver.opera.driver", Constant.operaDriverPosition);
            try {
                webDriver = new RemoteWebDriver(new URL(Constant.baseTestUrl), DesiredCapabilities.operaBlink());
            }catch (Exception e){
                e.printStackTrace();
            }
//            webDriver = new OperaDriver();
        }
        return webDriver;
    }

    ;
}
