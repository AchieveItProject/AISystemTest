package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static com.achieveit.systemtest.drivers.DriverSingleton.webDriver;

public class EdgeDriverStrategy implements DriverStrategy {
    public EdgeDriverStrategy() {
    }

    public WebDriver getWebDriver(){
        if(webDriver==null){
            System.setProperty("webdriver.edge.driver", Constant.edgeDriverPosition);
            webDriver=new EdgeDriver();
            // driver = new FirefoxDriver();   //Firefox浏览器
//    WebDriver driver = new EdgeDriver();      //Edge浏览器
//    WebDriver driver = new OperaDriver();     //Opera浏览器
        }
        return webDriver;
    } ;
}
