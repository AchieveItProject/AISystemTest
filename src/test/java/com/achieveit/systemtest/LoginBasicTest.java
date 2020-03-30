package com.achieveit.systemtest;

import com.achieveit.systemtest.drivers.ChromeDriverStrategy;
import com.achieveit.systemtest.drivers.EdgeDriverStrategy;
import com.achieveit.systemtest.drivers.FirefoxDriverStrategy;
import com.achieveit.systemtest.drivers.OperaDriverStrategy;
import com.achieveit.systemtest.pages.LoginPage;
import com.achieveit.systemtest.pages.Page;
import com.achieveit.systemtest.pages.WelcomePage;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginBasicTest extends BaseTest {

  LoginPage loginPage;

//    @DataProvider(name = "test2",parallel = true)
//    public static Object[][] parallel2Test(){
//        return new Object[][]{
//                {ChromeDriverStrategy.class},
//                {ChromeDriverStrategy.class}};
//    }



  @BeforeMethod
  public void pageInit() {
    loginPage = new LoginPage();
  }

  @Test
  public void loginbasictest() {
    try {
      loginPage.goLoginPage("loginPage").inputUsernameDialog("fjm").inputPasswordDialog("123")
              .clickLoginButton("newPage");
      WelcomePage p = new WelcomePage((Page) loginPage).verifyTitleOnPageClassName();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @AfterMethod
  public void pageDestroy() {
    loginPage.closePage("loginPage");
  }
}
