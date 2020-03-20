package com.achieveit.systemtest;

import com.achieveit.systemtest.pages.LoginPage;
import com.achieveit.systemtest.pages.Page;
import com.achieveit.systemtest.pages.WelcomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LoginBasicTest extends BaseTest{
  LoginPage loginPage;
  @Before
  public void pageInit(){
    loginPage=new LoginPage();
  }
@Test
  public void loginbasictest() {
    loginPage.goLoginPage("loginPage").inputUsernameDialog("admin").inputPasswordDialog("123456")
            .clickLoginButton("newPage");
   WelcomePage p=new WelcomePage( (Page)loginPage).verifyTitleOnPageClassName();


  }
  @After
  public  void pageDestroy(){
    loginPage.closePage("loginPage");
  }
}
