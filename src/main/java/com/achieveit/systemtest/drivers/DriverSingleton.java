package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import com.achieveit.systemtest.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DriverSingleton {
    static WebDriver webDriver;
    static Map<String, String> handlers;

    static DriverStrategy driverStrategy;

    public static void setDriverStrategy(String s) {
        Class webDriverClass;
        switch (s){
            case "chrome":
                webDriverClass=ChromeDriverStrategy.class;
                break;
            case "firefox":
                webDriverClass=FirefoxDriverStrategy.class;
                break;
            case "edge":
                webDriverClass=EdgeDriverStrategy.class;
                break;
            case "opera":
                webDriverClass=OperaDriverStrategy.class;
                break;
            default:
                webDriverClass=ChromeDriverStrategy.class;
                break;
        }
        try {
            driverStrategy = (DriverStrategy) webDriverClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DriverSingleton() {
    }

    public static WebDriver getWebDriver() {
        return driverStrategy.getWebDriver();
    }

    public static Map<String, String> getHandlers() {
        if (handlers == null) {
            handlers = new HashMap<String, String>();
        }
        return handlers;
    }

    public static void setNewPageHandler(Set<String> oldHandlers, String pageName) {
        Set<String> whnow = webDriver.getWindowHandles();
        whnow.removeAll(oldHandlers);
        String[] a = (String[]) whnow.toArray();
        handlers.put(pageName, a[0]);
    }

    /**
     * @param oldHandlers
     * @param pageName
     * @param timeout     click operation need time to wait!
     */
    public static void setNewPageHandler(Set<String> oldHandlers, String pageName, int timeout) {
        waitDriver(timeout);
        setNewPageHandler(oldHandlers, pageName);
    }

    public static void waitDriver(int timeout) {
        try {
//            Thread.sleep(timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putHandler(String name, String handlerId) {
        handlers.put(name, handlerId);
    }

}
