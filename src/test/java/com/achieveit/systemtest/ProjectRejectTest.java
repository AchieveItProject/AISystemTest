package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.achieveit.systemtest.pages.*;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectRejectTest extends BaseTest{

    ProjectCreateDialogPage createProjectDialogPage;
    ProjectManagementPage projectManagementPage;




    public void loginAsPM(String username,String password) {
        loginPage = new LoginPage();
        welcomePage= loginPage.goLoginPage("loginPage").inputUsernameDialog(username).inputPasswordDialog(password)
                .clickLoginButton("newPage");
        welcomePage.verifyTitleOnPageClassName();

    }
    public void loginAsLeader(String username,String password) {
        loginPage = new LoginPage();
        welcomePage= loginPage.goLoginPage("loginPage").inputUsernameDialog(username).inputPasswordDialog(password)
                .clickLoginButton("newPage");
        welcomePage.verifyTitleOnPageClassName();

    }


ProjectInfo projectInfo;
    @Test( dataProvider = "projectInfo", dataProviderClass = ProjectRejectTestData.class,priority = 1)
    public void createProject(ProjectInfo projectInfo) {
this.projectInfo=projectInfo;
        loginAsPM("fjm","123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        createProjectDialogPage = projectManagementPage.clickNewProjectButton();
        createProjectDialogPage.fillProjectInfo(projectInfo).submitProject();
        projectInfo.setStatus("申请立项");
        projectManagementPage.checkProjectInfoExists(projectInfo,true);
        // Set id
        int id=projectManagementPage.catchProjectId(projectInfo);
        this.projectInfo.setId(String.valueOf(id));
    }
    @Test( priority = 2)
    public void rejectProject() {
        loginAsLeader("c","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
projectManagementPage.clickApplyStatusButtonByLeader(e.get(1)).clickRejectButton().clickMakeSureButton();

this.projectInfo.setStatus("立即驳回");
projectManagementPage.checkProjectInfoExists(this.projectInfo,true);



    }
    @Test(priority = 3)
    public void checkLeaderRejectStatusProgressUnavailable() {
        login("c", "123", "Leader");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("立即驳回")).get(1), false);
    }
    @Test(priority = 3)
    public void checkQARejectStatusProgressUnavailable() {
        login("b", "123", "QA");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("立即驳回")).get(1), false);
    }
    @Test(priority = 3)
    public void checkCMRejectStatusProgressUnavailable() {
        login("f", "123", "CM");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("立即驳回")).get(1), false);
    }
    @Test(priority = 3)
    public void checkEPGRejectStatusProgressUnavailable() {
        login("a", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("立即驳回")).get(1), false);
    }
    @Test(priority = 3)
    public void checkMemberRejectStatusProgressUnavailable() {
        login("e", "123" );
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("立即驳回")).get(1), false);
    }
    @Test(priority = 3)
    public void checkPMRejectStatusProgressUnavailable() {
        login("fjm", "123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("立即驳回")).get(1), false);
    }

    @AfterClass
    public void pageDestroy() {
        getWebDriver().get("about:blank");
    }
}
