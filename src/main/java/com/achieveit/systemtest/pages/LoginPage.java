package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Set;

import static com.achieveit.systemtest.DriverSingleton.setNewPageHandler;
import static com.achieveit.systemtest.DriverSingleton.waitDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginPage implements Page {

    private WebElement usernameDialog;
    private WebElement passwordDialog;

    public LoginPage goLoginPage(String pageName) {
        webDriver.get(Constant.baseUrl + "login");
        putHandler( pageName,webDriver.getWindowHandle());
        assertThat(webDriver.getTitle(), is("AchieveIt软件项目管理"));
        return this;
    }

    public LoginPage clickLoginButton(String newPageName) {
        Set<String> handlers=webDriver.getWindowHandles();
        webDriver.findElement(By.className("loginConfirm-text")).click();
        waitDriver(1000);
//        setNewPageHandler(handlers,newPageName,5000);
        return this;
    }

    public WebElement selectUsernameDialog() {
        if (usernameDialog == null)
            usernameDialog = webDriver.findElement(By.className("loginInfoInput1"));

        return usernameDialog;
    }

    public LoginPage inputUsernameDialog(String username) {
        selectUsernameDialog().clear();
        selectUsernameDialog().sendKeys(new String[] {username });
        return this;
    }

    public WebElement selectPasswordDialog() {
        if (passwordDialog == null)
            passwordDialog = webDriver.findElement(By.className("loginInfoInput2"));

        return passwordDialog;
    }

    public LoginPage inputPasswordDialog(String password) {
        selectPasswordDialog().clear();
        selectPasswordDialog().sendKeys(password);
        return this;
    }

}
