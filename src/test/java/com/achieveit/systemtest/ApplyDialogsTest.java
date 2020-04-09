package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.achieveit.systemtest.pages.LoginPage;
import com.achieveit.systemtest.pages.ProjectCreateDialogPage;
import com.achieveit.systemtest.pages.ProjectManagementPage;
import com.achieveit.systemtest.pages.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ApplyDialogsTest extends BaseTest {
    ProjectCreateDialogPage createProjectDialogPage;
    ProjectManagementPage projectManagementPage;
    LoginPage loginPage;
    WelcomePage welcomePage;
    public void login(String username,String password) {
        loginPage = new LoginPage();
        welcomePage= loginPage.goLoginPage("loginPage").inputUsernameDialog(username).inputPasswordDialog(password)
                .clickLoginButton("newPage");
        welcomePage.verifyTitleOnPageClassName();

    }
    ProjectInfo projectInfo;
    @Test( dataProvider = "projectInfo", dataProviderClass = ProjectApproveTestData.class,priority = 1)
    public void createProject(ProjectInfo projectInfo) {
        this.projectInfo=projectInfo;
        login("fjm","123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        createProjectDialogPage = projectManagementPage.clickNewProjectButton();
        createProjectDialogPage.fillProjectInfo(projectInfo).submitProject();
        projectInfo.setStatus("申请立项");
        projectManagementPage.checkProjectInfoExists(projectInfo,true);
        int id=projectManagementPage.catchProjectId(projectInfo);
        this.projectInfo.setId(String.valueOf(id));
    }
    @Test( priority = 2)
    public void approveProject() {
        login("c","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApplyStatusButtonByLeader(e.get(1)).clickApprovalButton().clickMakeSureButton();

        this.projectInfo.setStatus("已立项");
        projectManagementPage.checkProjectInfoExists(this.projectInfo,true);


    }
}
