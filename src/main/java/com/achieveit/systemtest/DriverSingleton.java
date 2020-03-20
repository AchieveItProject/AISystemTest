package com.achieveit.systemtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class DriverSingleton {
   private static WebDriver webDriver;
  private static  Map<String,Object> vars;
    private static  Map<String,String> handlers;

    public DriverSingleton() {
    }

    public static WebDriver getWebDriver() {
        if(webDriver==null){
            System.setProperty("webdriver.chrome.driver",Constant.chromeDriverPosition);
            webDriver=new ChromeDriver();
            // driver = new FirefoxDriver();   //Firefox浏览器
//    WebDriver driver = new EdgeDriver();      //Edge浏览器
//    WebDriver driver = new OperaDriver();     //Opera浏览器
        }
        return webDriver;
    }
    public static Map<String, Object> getVars(){
        if(vars==null){
            vars=new HashMap<String, Object>();
        }
        return vars;
    }

    public static Map<String, String> getHandlers(){
        if(handlers==null){
            handlers=new HashMap<String, String>();
        }
        return handlers;
    }
    public static void setNewPageHandler(Set<String> oldHandlers, String pageName){
        Set<String> whnow= webDriver.getWindowHandles();
        whnow.removeAll(oldHandlers);
        String[] a= (String[])whnow.toArray();
        handlers.put(pageName,a[0] );
    }

    /**
     *
     * @param oldHandlers
     * @param pageName
     * @param timeout click operation need time to wait!
     */
    public static void setNewPageHandler(Set<String> oldHandlers, String pageName,int timeout){
        waitDriver(timeout);
        setNewPageHandler(oldHandlers,pageName);
    }

    public static void waitDriver(int timeout){
        try{
            Thread.sleep(timeout);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
