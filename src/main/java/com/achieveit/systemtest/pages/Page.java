package com.achieveit.systemtest.pages;

import org.openqa.selenium.WebDriver;

import java.util.Map;

import static com.achieveit.systemtest.drivers.DriverSingleton.*;

public interface Page {
    WebDriver webDriver = getWebDriver();
    Map<String, Object> vars = getVars();
    Map<String,String> handlers=getHandlers();


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
    default Page switchToWin(String win) {
        webDriver.switchTo().window(vars.get(win).toString());
        return this;
    }

    default Page putHandler(String name, String handlerId) {
        handlers.put(name, handlerId);
        return this;
    }
//
//    default Page putWin(String win) {
//        vars.put(win, waitForWindow(5000));
//        return this;
//    }



//    default Page closePage(String win) {
//        webDriver.switchTo().window(vars.get(win).toString());
//        webDriver.close();
//        return this;
//    }

}
