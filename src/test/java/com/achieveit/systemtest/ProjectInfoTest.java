package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.achieveit.systemtest.pages.*;
import com.github.javafaker.Faker;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.achieveit.systemtest.drivers.DriverSingleton.getWebDriver;

public class ProjectInfoTest extends BaseTest {

    ProjectCreateDialogPage createProjectDialogPage;
    ProjectManagementPage projectManagementPage;
    LoginPage loginPage;


    @BeforeMethod
    public void init() {
        loginPage = new LoginPage();
        WelcomePage p= loginPage.goLoginPage("loginPage").inputUsernameDialog("fjm").inputPasswordDialog("123")
                .clickLoginButton("newPage");
        p.verifyTitleOnPageClassName();
        projectManagementPage = p.selectProjectManagementMenu();
    }

    @Test(dataProvider = "projectInfo", dataProviderClass = ProjectInfoTestData.class)
    public void createProject(ProjectInfo projectInfo) {
        projectManagementPage.clickNewProjectButton();
        createProjectDialogPage = new ProjectCreateDialogPage((Page) projectManagementPage);
        createProjectDialogPage.fillProjectInfo(projectInfo).submitProject();
        projectManagementPage.checkProjectInfo(projectInfo, true);
    }

    // Besides, name there are many other to revise!
    @Test(dataProvider = "randomProjectName", dataProviderClass = ProjectInfoTestData.class, enabled = false)
    public void modifyProjectName(String name) {
        ProjectEditDialogPage editProjectDialogPage = projectManagementPage.clickModifyButton(0);
        ProjectInfo cur = editProjectDialogPage.catchCurrentProjectInfo();
        editProjectDialogPage.modifyProjectName(name).submitProject();
        cur.setProjectName(name);
        editProjectDialogPage.checkProjectInfo(cur, true);
    }
    @Test(dataProvider = "randomProjectName", dataProviderClass = ProjectInfoTestData.class)
    public void modifyProjectNameButCancel(String name) {
        ProjectEditDialogPage editProjectDialogPage = projectManagementPage.clickModifyButton(0);
        ProjectInfo cur = editProjectDialogPage.catchCurrentProjectInfo();
        editProjectDialogPage.submitProject();
        editProjectDialogPage.checkProjectInfo(cur, true);
    }

    @Test(dataProvider = "deleteProjectId", dataProviderClass = ProjectInfoTestData.class, enabled = false,priority = 1)
    public void deleteProjectButCancel(String id) {
        List<WebElement> matchLine = projectManagementPage.selectListItem((line, lineExt) -> line.findElements(By.tagName("td")).get(2)
                .findElement(By.tagName("div")).getText().equals(id));
        ProjectInfo p = projectManagementPage.translateFromLineWebElement(matchLine.get(0),matchLine.get(1));
        projectManagementPage.clickDeleteButton(matchLine.get(0)).clickCancelButton();
        projectManagementPage.checkProjectInfoExists(p, true);
    }

    @Test(dataProvider = "deleteProjectId", dataProviderClass = ProjectInfoTestData.class, priority = 2)
    public void deleteProject(String id) {
       List< WebElement> matchLine = projectManagementPage.selectListItem((line, lineExt) -> line.findElements(By.tagName("td")).get(2)
                .findElement(By.tagName("div")).getText().equals(id));
        ProjectInfo p = projectManagementPage.translateFromLineWebElement(matchLine.get(0),matchLine.get(1));
        projectManagementPage.clickDeleteButton(matchLine.get(0)).clickMakeSureButton();
        projectManagementPage.checkProjectInfoExists(p, false);
    }

    @AfterMethod
    public void pageDestroy() {
        getWebDriver().get("about:blank");
    }
}
