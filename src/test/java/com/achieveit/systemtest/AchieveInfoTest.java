package com.achieveit.systemtest;

import com.achieveit.systemtest.pages.LoginPage;
import com.achieveit.systemtest.pages.ProjectCreateDialogPage;
import com.achieveit.systemtest.pages.ProjectManagementPage;
import com.achieveit.systemtest.pages.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AchieveInfoTest extends BaseTest {
    ProjectCreateDialogPage createProjectDialogPage;
    ProjectManagementPage projectManagementPage;


    @BeforeClass
    public void init(){
        login("f","123","CM");
        projectManagementPage= welcomePage.selectProjectManagementMenu();
        List<WebElement> e=projectManagementPage.selectListItem(
                (line, lineExt)->projectManagementPage.acquireStatusButton(lineExt).getText().equals("结束"));

    }
    @Test
    public void test(){
//        List<WebElement> e=projectManagementPage.selectListItem(
//                (line, lineExt)->projectManagementPage.acquireStatusButton(lineExt).getText().equals("结束"));
//        projectManagementPage.clickDetailButtonByCM(e.get(0))
//                .checkCompleteness(0,true).checkCompleteness(1,false);
    }
}
