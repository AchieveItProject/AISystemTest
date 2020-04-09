package com.achieveit.systemtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static com.achieveit.systemtest.drivers.DriverSingleton.*;

public interface Page {
    WebDriver webDriver = getWebDriver();
    Map<String,String> handlers=getHandlers();
    WebDriverWait wait=getWebDriverWait();
     default Page navigateTo(String pageName){
        String str= handlers.get(pageName);
        webDriver.switchTo().window(str);
        return this;

    }
    default Page closePage(String pageName){
        String str= handlers.get(pageName);
        webDriver.switchTo().window(str);
        webDriver.close();
        handlers.remove(pageName);
        return this;
    }




}
