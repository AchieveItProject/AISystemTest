package com.achieveit.systemtest.drivers;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverCommandExecutor;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.achieveit.systemtest.drivers.DriverSingleton.webDriver;

public class ChromeDriverStrategy implements DriverStrategy {
    public ChromeDriverStrategy() {
    }

    public WebDriver getWebDriver(){
        if(webDriver==null){
            synchronized (DriverSingleton.class){
                if(webDriver==null){
                    try {
//                        System.setProperty("webdriver.chrome.driver","D:/Software Development Practice/driver/chromedriver.exe");
//                        webDriver=new ChromeDriver();

                        HashMap<String,Object> prefs=new HashMap<>();
                        prefs.put("download.default_directory",Constant.downloadPath);
//                        prefs.put("profile.default_content_settings.popups","0");
//                        prefs.put( "download.prompt_for_download","False");
                        ChromeOptions chromeOptions=new ChromeOptions();
                        chromeOptions.setExperimentalOption("prefs",prefs);
//                        chromeOptions.addArguments("start-maximized");
//                        chromeOptions.addArguments("--headless");
//                        chromeOptions.addArguments("--window-size=1024,1024");
                        DesiredCapabilities desiredCapabilities=DesiredCapabilities.chrome();
                        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);

                        webDriver = new RemoteWebDriver(new URL(Constant.baseTestUrl), desiredCapabilities);
                        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return webDriver;
    } ;
}
