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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginBasicTest extends BaseTest {

  LoginPage loginPage;

    @DataProvider(name = "login",parallel = false)
    public static Object[][] parallel2Test(){
        return new Object[][]{
                {"fjm","123"},
                {"br","123"}};
    }



  @BeforeMethod
  public void pageInit() {
    loginPage = new LoginPage();
  }

  @Test(dataProvider = "login")
  public void loginbasictest(String username, String password) {

    WelcomePage p =  loginPage.goLoginPage("loginPage").inputUsernameDialog(username).inputPasswordDialog(password)
            .clickLoginButton("newPage");
    p.verifyTitleOnPageClassName();

  }


  @AfterMethod
  public void pageDestroy() {
    loginPage.closePage("loginPage");
  }
}
