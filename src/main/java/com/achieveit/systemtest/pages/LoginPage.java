package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

import static com.achieveit.systemtest.drivers.DriverSingleton.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginPage implements Page {
    @FindBy(className = "loginInfoInput1")
    @CacheLookup
    private WebElement usernameDialog;
    @FindBy(className = "loginInfoInput2")
    @CacheLookup
    private WebElement passwordDialog;

    public LoginPage goLoginPage(String pageName) {
        webDriver.get(Constant.baseUrl + "login");
        PageFactory.initElements(webDriver, this);
        putHandler(pageName, webDriver.getWindowHandle());
        assertThat(webDriver.getTitle(), is("AchieveIt软件项目管理"));
        return this;
    }

    public LoginPage clickLoginButton(String newPageName) {
//        Set<String> handlers=webDriver.getWindowHandles();
        webDriver.findElement(By.className("loginConfirm-text")).click();
        waitDriver(1000);
//        setNewPageHandler(handlers,newPageName,5000);
        return this;
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
