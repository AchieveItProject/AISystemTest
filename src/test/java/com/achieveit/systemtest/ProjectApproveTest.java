package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.achieveit.systemtest.pages.LoginPage;
import com.achieveit.systemtest.pages.ProjectCreateDialogPage;
import com.achieveit.systemtest.pages.ProjectManagementPage;
import com.achieveit.systemtest.pages.WelcomePage;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectApproveTest extends BaseTest {
    ProjectCreateDialogPage createProjectDialogPage;
    ProjectManagementPage projectManagementPage;
    LoginPage loginPage;
    WelcomePage welcomePage;



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
    public void approveProject() {
        loginAsLeader("c","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApplyStatusButtonByLeader(e.get(1)).clickApprovalButton().clickMakeSureButton();

        this.projectInfo.setStatus("已立项");
        projectManagementPage.checkProjectInfoExists(this.projectInfo,true);


    }
    @Test( priority = 3,enabled=false)
    public void checkProjectStatusChangeIsUnavailable(){

        loginAsPM("fjm","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
       try {
           projectManagementPage.clickApprovalStatusButtonByPM(e.get(1)).clickMakeSureButton();
       }catch(Exception el){
            assertThat(el.getMessage(), IsNot.not(""));
        }
        assertThat("Should raise exception","Can not click", Is.is("Clickable"));
    }

    @Test( priority = 4, dataProviderClass = ProjectApproveTestData.class,dataProvider = "cancelEPG")
    public void applyEPGButCancel(String id) {
        login("a","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByEPG(e.get(1))
                .inputEPGId(id).clickCancelButton();
        projectManagementPage.clickApprovalStatusButtonByEPG(e.get(1)) .checkContent("8",false);
    }
    @Test( priority = 4, dataProviderClass = ProjectApproveTestData.class,dataProvider = "cancelQA")
    public void applyQAButCancel(String id) {
        login("b","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByQA(e.get(1))
                .inputQAId(id).clickMakeSureButton();
        projectManagementPage.clickApprovalStatusButtonByQA(e.get(1)).checkContent(id,true);
    }
    @Test( priority = 4,enabled=false, dataProviderClass = ProjectApproveTestData.class,dataProvider = "cancelCM")
    public void applyCMButCancel(String id) {
        login("f","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByCM(e.get(1))
                .inputCMId(id).clickMakeSureButton();
        projectManagementPage.clickApprovalStatusButtonByCM(e.get(1)).checkContent(id,true);
    }


    @Test( priority = 5, dataProviderClass = ProjectApproveTestData.class,dataProvider = "applyEPG")
    public void applyEPG(String id) {
        login("a","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByEPG(e.get(1))
        .inputEPGId(id).clickMakeSureButton();
        projectManagementPage.clickApprovalStatusButtonByEPG(e.get(1)).checkContent(id,true);
    }
    @Test( priority = 5, dataProviderClass = ProjectApproveTestData.class,dataProvider = "applyQA")
    public void applyQA(String id) {
        login("b","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByQA(e.get(1))
                .inputQAId(id).clickMakeSureButton();
        projectManagementPage.clickApprovalStatusButtonByQA(e.get(1)).checkContent(id,true);
    }
    @Test( priority = 5,enabled=false, dataProviderClass = ProjectApproveTestData.class,dataProvider = "applyCM")
    public void applyCM(String id) {
        login("f","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByCM(e.get(1))
                .inputCMId(id).clickMakeSureButton();
        projectManagementPage.clickApprovalStatusButtonByCM(e.get(1)).checkContent(id,true);
    }

    @Test( priority = 6,dataProvider = "modifyEPG",dataProviderClass = ProjectApproveTestData.class)
    public void modifyEPG(String id) {
        login("a","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByEPG(e.get(1))
                .inputEPGId(id).clickMakeSureButton();
        projectManagementPage.clickApprovalStatusButtonByEPG(e.get(1)).checkContent(id,true);
    }
    @Test( priority = 6, dataProviderClass = ProjectApproveTestData.class,dataProvider = "modifyQA")
    public void modifyQA(String id) {
        login("b","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByQA(e.get(1))
                .inputQAId(id).clickMakeSureButton();
        projectManagementPage.clickApprovalStatusButtonByQA(e.get(1)).checkContent(id,true);
    }
    @Test( priority = 6,enabled=false, dataProviderClass = ProjectApproveTestData.class,dataProvider = "modifyCM")
    public void modifyCM(String id) {
        login("f","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByCM(e.get(1))
                .inputCMId(id).clickMakeSureButton();
        projectManagementPage.clickApprovalStatusButtonByCM(e.get(1)).checkContent(id,true);
    }


    @Test( priority = 7)
    public void statusChangeToInProgress(){

        loginAsPM("fjm","123");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem((line, lineExt)->line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(projectInfo.getId()));
        projectManagementPage.clickApprovalStatusButtonByPM(e.get(1)).clickMakeSureButton();
        this.projectInfo.setStatus("进行中");
        projectManagementPage.checkProjectInfoExists(this.projectInfo,true);


    }

    @AfterClass
    public void pageDestroy() {
        getWebDriver().get("about:blank");
    }
}
