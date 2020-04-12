package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static com.achieveit.systemtest.drivers.DriverSingleton.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class LoginPage implements Page {
    @FindBy(className = "loginInfoInput1")
    @CacheLookup
    private WebElement usernameDialog;
    @FindBy(className = "loginInfoInput2")
    @CacheLookup
    private WebElement passwordDialog;

    public LoginPage goLoginPage(String pageName) {
        String url=Constant.baseUrl;
        webDriver.get(url);
        wait.until( wd -> wd.getTitle().equals("登录页") );
        PageFactory.initElements(webDriver, this);
//        putHandler(pageName, webDriver.getWindowHandle());
        return this;
    }

    public WelcomePage clickLoginButton(String newPageName) {
//        Set<String> handlers=webDriver.getWindowHandles();
        webDriver.findElement(By.className("loginConfirm-text")).click();
        System.out.println(webDriver.getTitle());
        wait.until( wd -> wd.getTitle().equals("系统首页") );
//        setNewPageHandler(handlers,newPageName,5000);
        return new WelcomePage((Page) this);
    }

    public LoginPage inputUsernameDialog(String username) {
        usernameDialog.clear();
        usernameDialog.sendKeys(username);
        return this;
    }

    public LoginPage inputPasswordDialog(String password) {
        passwordDialog.clear();
        passwordDialog.sendKeys(password);
        return this;
    }

}
