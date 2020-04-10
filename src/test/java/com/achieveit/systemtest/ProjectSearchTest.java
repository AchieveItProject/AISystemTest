package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.achieveit.systemtest.pages.LoginPage;
import com.achieveit.systemtest.pages.ProjectManagementPage;
import com.achieveit.systemtest.pages.WelcomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.achieveit.systemtest.drivers.DriverSingleton.getWebDriver;

public class ProjectSearchTest extends BaseTest{
    ProjectManagementPage projectManagementPage;

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage();
        WelcomePage p= loginPage.goLoginPage("loginPage").inputUsernameDialog("fjm").inputPasswordDialog("123")
                .clickLoginButton("newPage");
        p.verifyTitleOnPageClassName();
        projectManagementPage = p.selectProjectManagementMenu();
    }
    @Test(dataProviderClass = ProjectSearchTestData.class,dataProvider = "idInList",enabled = false)
    public void searchById(String id){
        projectManagementPage.clickSearchButtonToOpenSearchDialog().inputSearchId(id).clickSubmitButton();
        ProjectInfo pi=new ProjectInfo();
        pi.setId(id);
        projectManagementPage.checkProjectInfoExists(pi,true);
        projectManagementPage.checkListAmount(1);
    }
    @Test(dataProviderClass = ProjectSearchTestData.class,dataProvider = "nameInList",enabled = false)
    public void searchByName(String name){
        projectManagementPage.clickSearchButtonToOpenSearchDialog().inputSearchName(name).clickSubmitButton();
        projectManagementPage.checkProjectNameContains(name,true);
    }
    @Test(dataProviderClass = ProjectSearchTestData.class,dataProvider = "leaderNameInList")
    public void searchByLeader(String name){
        projectManagementPage.clickSearchButtonToOpenSearchDialog().inputSearchLeader(name).clickSubmitButton();
        ProjectInfo pi=new ProjectInfo();
        pi.setProjectName(name);
        projectManagementPage.checkProjectLeader(name,true);

    }
    @Test(dataProviderClass = ProjectSearchTestData.class,dataProvider = "idAndNameInList")
    public void searchByIdAndName(String id, String name){
        projectManagementPage.clickSearchButtonToOpenSearchDialog().inputSearchId(id). inputSearchLeader(name).clickSubmitButton();
        ProjectInfo pi=new ProjectInfo();
        pi.setProjectName(name);
        pi.setId(id);
        projectManagementPage.checkProjectInfoExists(pi,true);
        projectManagementPage.checkListAmount(1);
    }
    @Test(dataProviderClass = ProjectSearchTestData.class,dataProvider = "nameAndLeaderNameInList")
    public void searchByNameAndLeader(String name, String leaderName) {
        projectManagementPage.clickSearchButtonToOpenSearchDialog().inputSearchLeader(leaderName)
                .inputSearchName(name).clickSubmitButton();
        projectManagementPage.checkProjectNameContains(name, true);
        projectManagementPage.checkProjectLeader(leaderName, true);
    }
    @Test(dataProviderClass = ProjectSearchTestData.class,dataProvider = "idAndLeaderNameInList")
    public void searchByIdAndLeader(String id, String leaderName){
        projectManagementPage.clickSearchButtonToOpenSearchDialog().inputSearchLeader(leaderName)
                . inputSearchId(id).clickSubmitButton();
        ProjectInfo pi=new ProjectInfo();
        pi.setId(id);
        pi.setLeader(leaderName);
        projectManagementPage.checkProjectInfoExists(pi,true);
        projectManagementPage.checkListAmount(1);

    }
    @AfterMethod
    public void pageDestroy() {
        getWebDriver().get("about:blank");
    }
}
