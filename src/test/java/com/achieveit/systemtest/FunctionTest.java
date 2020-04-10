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
        f.clickEditButton(r,false).inputFuncName("222").clickMakeSureButton().checkEqual(r, fi,false);
    }
    @Test(enabled = false)
    public void editFuncButCancel() {
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi= f.translateFromLine(r);
        f.clickEditButton(r,false).clickMakeSureButton().checkEqual(r, fi,false);
    }
    @Test(enabled = false)
    public void watchFunc(){
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);

        FunctionInfo fi =  f.translateFromLine(r);
        f.clickWatchButton(r,false).verifyInfo(fi)
                .clickCancelButton().checkEqual(r, fi,false);
    }
    @Test(enabled = false)
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
    @Test(enabled = false)
    public void deleteFunc(){
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi =  f.translateFromLine(r);

        f= f.clickDeleteButton(r,false).clickMakeSureButton();
        WebElement e= f.checkFunctionLineExists(fi);
        assertThat(e, Is.is(nullValue()));
    }
    @Test(enabled = false)
    public void deleteFuncButCancel(){
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi =  f.translateFromLine(r);

        f= f.clickDeleteButton(r,false).clickCancelButton();
        WebElement e= f.checkFunctionLineExists(fi);
        assertThat(e, Is.is(notNullValue()));
    }

    @Test(enabled = false)
    public void newSubFunc(){
        // Pre
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        // Do
        FunctionInfo fi = new FunctionInfo();
        fi.setName("222");
        fi.setPeople("333");
        WebElement r = f.selectFunctionListItem(line -> true);
        f.clickNewSubFuncButton(r).inputFuncName(fi.getName()).inputFuncPerson(fi.getPeople()).clickMakeSureButton();
        // Check
       WebElement e= f.openDetailDialog(r).checkSubFunctionLineExists(fi);
        assertThat(e, Is.is(notNullValue()));
    }
    @Test
    public void watchSubFunc(){
        // Pre
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        // Do
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi = f.translateFromLine(r);
        WebElement sub= f.openDetailDialog(r).selectSubFunctionListItem(line->true);
        FunctionInfo subFi=f.translateFromSubLine(sub);
        f.clickWatchButton(sub,true).verifyInfo(subFi).clickCancelButton().checkEqual(sub,subFi,true);

    }
    @Test(enabled = false)
    public void editSubFunc() {
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi = new FunctionInfo();
        WebElement sub= f.openDetailDialog(r).selectSubFunctionListItem(line->true);
        FunctionInfo subFi=f.translateFromSubLine(sub);
        fi.setName("222");
        f.clickEditButton(sub,true).inputFuncName("222").clickMakeSureButton().checkEqual(sub, subFi,true);
    }
    @Test(enabled = false)
    public void editSubFuncButCancel() {
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi= f.translateFromLine(r);
        WebElement sub= f.openDetailDialog(r).selectSubFunctionListItem(line->true);
        FunctionInfo subFi=f.translateFromSubLine(sub);
        f.clickEditButton(sub,true).inputFuncName("222").clickMakeSureButton().checkEqual(sub, subFi,true);
    }
    //At least two!!
    @Test(enabled = false)
    public void deleteSubFunc(){
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi =  f.translateFromLine(r);
        WebElement sub= f.openDetailDialog(r).selectSubFunctionListItem(line->true);
        FunctionInfo subFi=f.translateFromSubLine(sub);

        f= f.clickDeleteButton(sub,true).clickMakeSureButton();
        WebElement e= f.checkSubFunctionLineExists(subFi);
        assertThat(e, Is.is(nullValue()));
    }
    @Test(enabled = false)
    public void deleteSubFuncButCancel(){
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        FunctionPage f = projectDetailPage.enterFunctionTab();
        WebElement r = f.selectFunctionListItem(line -> true);
        FunctionInfo fi =  f.translateFromLine(r);
        WebElement sub= f.openDetailDialog(r).selectSubFunctionListItem(line->true);
        FunctionInfo subFi=f.translateFromSubLine(sub);

        f= f.clickDeleteButton(sub,true).clickCancelButton();
        WebElement e= f.checkSubFunctionLineExists(subFi);
        assertThat(e, Is.is(notNullValue()));
    }

}
