package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.FunctionInfo;
import com.achieveit.systemtest.pages.FunctionPage;
import com.achieveit.systemtest.pages.ProjectDetailPage;
import com.achieveit.systemtest.pages.ProjectManagementPage;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class FunctionTest extends BaseTest {
    @Test(enabled = false)
    public void editFunc() {
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi = new FunctionInfo();
        fi.setName("222");
        f.clickEditButton(r).inputFuncName("222").clickMakeSureButton().checkEqual(r, fi);
    }
    @Test(enabled = false)
    public void editFuncButCancel() {
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi= f.translateFromLine(r);
        f.clickEditButton(r).clickMakeSureButton().checkEqual(r, fi);
    }
    @Test(enabled = false)
    public void watchFunc(){
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);

        FunctionInfo fi =  f.translateFromLine(r);
        f.clickWatchButton(r).verifyInfo(fi)
                .clickCancelButton().checkEqual(r, fi);
    }
    @Test
    public void newFunc(){
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        FunctionInfo fi = new FunctionInfo();
        fi.setName("222");
        fi.setPeople("333");
        f= f.clickNewButton().inputFuncName(fi.getName()).inputFuncPerson(fi.getPeople()).clickMakeSureButton();
        WebElement e= f.checkFunctionLineExists(fi);
        assertThat(e, Is.is(notNullValue()));
    }
    @Test
    public void deleteFunc(){
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        FunctionInfo fi = new FunctionInfo();
        fi.setName("222");
        fi.setPeople("333");
        f= f.clickNewButton().inputFuncName(fi.getName()).inputFuncPerson(fi.getPeople()).clickMakeSureButton();
        WebElement e= f.checkFunctionLineExists(fi);
        assertThat(e, Is.is(notNullValue()));
    }

}
