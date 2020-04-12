package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.achieveit.systemtest.pages.ProjectManagementPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class StatusTest extends BaseTest {
    ProjectManagementPage projectManagementPage;
    ProjectInfo projectInfo;

    @Test(priority = 1,dataProviderClass = ProjectInfoData.class,dataProvider = "projectInfo")
    public void newProjectOperation(ProjectInfo p) {
        this.projectInfo=p;
        login("fjm","123","PM");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.clickNewProjectButton().fillProjectInfo(p).submitProject();
    }
    @Test(priority = 2)
    public void checkLeaderApplyStatusChangeUnavailable() {
        login("a", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("申请立项")).get(1), false);
    }
    @Test(priority = 2)
    public void checkQAApplyStatusChangeUnavailable() {
        login("b", "123", "QA");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("申请立项")).get(1), false);
    }
    @Test(priority = 2)
    public void checkCMApplyStatusChangeUnavailable() {
        login("f", "123", "CM");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("申请立项")).get(1), false);
    }
    @Test(priority = 2)
    public void checkEPGApplyStatusChangeUnavailable() {
        login("a", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("申请立项")).get(1), false);
    }
    @Test(priority = 2)
    public void checkMemberApplyStatusChangeUnavailable() {
        login("e", "123" );
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("申请立项")).get(1), false);
    }
    @Test(priority = 2)
    public void checkPMApplyStatusChangeUnavailable() {
        login("fjm", "123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("申请立项")).get(1), false);
    }

    @Test(priority = 3)
    public void fromApplyToApprove(){
        login("c", "123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.clickApplyStatusButtonByLeader(projectManagementPage.selectListItem((l,le)->projectManagementPage.acquireStatusButton(le).getText().equals("申请立项")).get(1))
        .clickMakeSureButton();
    }
    @Test(priority = 4)
    public void checkLeaderApplyStatusApproveUnavailable() {
        login("c", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1), false);
    }
    @Test(priority = 4)
    public void checkQAApplyStatusApproveAvailable() {
        login("b", "123", "QA");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1), true);
    }
    @Test(priority = 4,enabled = false)
    public void checkCMApplyStatusApproveAvailable() {
        login("f", "123", "CM");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1), true);
    }
    @Test(priority = 4)
    public void checkEPGApplyStatusApproveAvailable() {
        login("a", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1), true);
    }
    @Test(priority = 4)
    public void checkMemberApplyStatusApproveUnavailable() {
        login("e", "123" );
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1), false);
    }
    @Test(priority = 4)
    public void checkPMApplyStatusApproveAvailable() {
        login("fjm", "123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1), true);
   //But actually, it should not enter into next until 5 is done, or dialog is set to warning to set.
    }

    @Test(priority = 5)
    public void fromApproveToProgress(){
        login("a", "123");
       projectManagementPage= welcomePage.selectProjectManagementMenu();
      WebElement e=  projectManagementPage.selectListItem((l,le)->projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1);
        projectManagementPage   .clickApprovalStatusButtonByEPG(
                        e
                )
                .inputEPGId("2")
                .clickMakeSureButton();
        login("b", "123");
        welcomePage.selectProjectManagementMenu()
                .clickApprovalStatusButtonByQA(projectManagementPage.selectListItem((l,le)->projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1))
                .inputQAId("3")
                .clickMakeSureButton();
//        login("f", "123");
//        welcomePage.selectProjectManagementMenu()
//                .clickApprovalStatusButtonByCM(projectManagementPage.selectListItem((l,le)->projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1))
//                .inputCMId("1")
//                .clickMakeSureButton();
        login("fjm", "123");
        welcomePage.selectProjectManagementMenu()
                .clickApprovalStatusButtonByPM(projectManagementPage.selectListItem(
                        (l,le)->projectManagementPage.acquireStatusButton(le).getText().equals("已立项")).get(1))
                .clickMakeSureButton();
    }
    @Test(priority = 6)
    public void checkLeaderApplyStatusProgressUnavailable() {
        login("c", "123", "Leader");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(1), false);
    }
    @Test(priority = 6)
    public void checkQAApplyStatusProgressUnavailable() {
        login("b", "123", "QA");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(1), false);
    }
    @Test(priority = 6)
    public void checkCMApplyStatusProgressUnavailable() {
        login("f", "123", "CM");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(1), false);
    }
    @Test(priority = 6)
    public void checkEPGApplyStatusProgressUnavailable() {
        login("a", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(1), false);
    }
    @Test(priority = 6)
    public void checkMemberApplyStatusProgressUnavailable() {
        login("e", "123" );
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(1), false);
    }
    @Test(priority = 6)
    public void checkPMApplyStatusProgressAvailable() {
        login("fjm", "123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(1), true);
    }

    @Test(priority = 7)
    public void fromProgressToSubmit(){
        login("fjm", "123");
        welcomePage.selectProjectManagementMenu()
                .clickProgressStatusButtonByPM(projectManagementPage.selectListItem((l,le)->projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(1))
                .clickMakeSureButton();
    }
    @Test(priority = 8)
    public void checkLeaderSubmitStatusProgressUnavailable() {
        login("c", "123", "Leader");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已交付")).get(1), false);
    }
    @Test(priority = 8)
    public void checkQASubmitStatusProgressUnavailable() {
        login("b", "123", "QA");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已交付")).get(1), false);
    }
    @Test(priority = 8)
    public void checkCMSubmitStatusProgressUnavailable() {
        login("f", "123", "CM");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已交付")).get(1), false);
    }
    @Test(priority = 8)
    public void checkEPGSubmitStatusProgressUnavailable() {
        login("a", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已交付")).get(1), false);
    }
    @Test(priority = 8)
    public void checkMemberSubmitStatusProgressUnavailable() {
        login("e", "123" );
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已交付")).get(1), false);
    }
    @Test(priority = 8)
    public void checkPMSubmitStatusProgressAvailable() {
        login("fjm", "123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已交付")).get(1), true);
    }

    @Test(priority = 11)
    public void fromSubmitToEnd(){
        login("fjm", "123");
        welcomePage.selectProjectManagementMenu()
                .clickSubmitStatusButtonByPM(projectManagementPage.selectListItem((l,le)->projectManagementPage.acquireStatusButton(le).getText().equals("已交付")).get(1))
                .clickMakeSureButton();
    }
    @Test(priority = 12)
    public void checkLeaderEndStatusProgressUnavailable() {
        login("c", "123", "Leader");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("结束")).get(1), false);
    }
    @Test(priority = 12)
    public void checkQAEndStatusProgressUnavailable() {
        login("b", "123", "QA");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("结束")).get(1), false);
    }
    @Test(priority = 12)
    public void checkCMEndStatusProgressAvailable() {
        login("f", "123", "CM");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("结束")).get(1), true);
    }
    @Test(priority = 12)
    public void checkEPGEndStatusProgressUnavailable() {
        login("a", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("结束")).get(1), false);
    }
    @Test(priority = 12)
    public void checkMemberEndStatusProgressUnavailable() {
        login("e", "123" );
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("结束")).get(1), false);
    }
    @Test(priority = 12)
    public void checkPMEndStatusProgressUnavailable() {
        login("fjm", "123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("结束")).get(1), false);
    }

    @Test(priority = 13)
    public void fromEndToAchieve(){
        login("f", "123");
        welcomePage.selectProjectManagementMenu()
                .clickEndButtonByCM(projectManagementPage.selectListItem((l,le)->projectManagementPage.acquireStatusButton(le).getText().equals("结束")).get(1))
                .clickMakeSureButton();
    }
    @Test(priority = 14)
    public void checkLeaderAchieveStatusProgressUnavailable() {
        login("c", "123", "Leader");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已归档")).get(1), false);
    }
    @Test(priority = 14)
    public void checkQAAchieveStatusProgressUnavailable() {
        login("b", "123", "QA");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已归档")).get(1), false);
    }
    @Test(priority = 14)
    public void checkCMAchieveStatusProgressUnavailable() {
        login("f", "123", "CM");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已归档")).get(1), false);
    }
    @Test(priority = 14)
    public void checkEPGAchieveStatusProgressUnavailable() {
        login("a", "123", "EPG");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已归档")).get(1), false);
    }
    @Test(priority = 14)
    public void checkMemberAchieveStatusProgressUnavailable() {
        login("e", "123" );
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已归档")).get(1), false);
    }
    @Test(priority = 14)
    public void checkPMAchieveStatusProgressUnavailable() {
        login("fjm", "123");
        projectManagementPage = welcomePage.selectProjectManagementMenu();
        projectManagementPage.checkStatusButtonClickable(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("已归档")).get(1), false);
    }
}
