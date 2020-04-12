package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.FunctionInfo;
import com.achieveit.systemtest.entity.MemberInfo;
import com.achieveit.systemtest.pages.MemberPage;
import com.achieveit.systemtest.pages.ProjectDetailPage;
import com.achieveit.systemtest.pages.ProjectManagementPage;
import org.hamcrest.core.Is;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class MemberTest extends BaseTest {
    @Test
    public void newMember(){
        // Pre
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        MemberPage f = projectDetailPage.enterMemberTab();
        // Do
        MemberInfo fi = new MemberInfo();
        fi.setMemberId("1");
        String rolePos="1";
        fi.setMemberRole(rolePos);
       f.clickNewButton().inputMemberIdName(fi.getMemberId()).inputMemberRole(rolePos).clickMakeSureButton();

        WebElement e= f.checkMemberLineExists(fi);
        assertThat(e, Is.is(notNullValue()));
    }
    @Test(priority = 2)
    public void deleteMember(){
        // Pre
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        MemberPage f = projectDetailPage.enterMemberTab();
        // Do
        WebElement r=f.selectMemberListItem(line->true);
        MemberInfo memberInfo=f.translateFromLine(r);
        f= f.clickDeleteButton(r,false).clickMakeSureButton();
        WebElement e= f.checkMemberLineExists(memberInfo);
        assertThat(e, Is.is(nullValue()));


    }
    @Test(priority = 1)
    public void deleteMemberButCancel(){
        // Pre
        login("fjm", "123");
        ProjectManagementPage projectManagementPage = welcomePage.selectProjectManagementMenu();
        ProjectDetailPage projectDetailPage = projectManagementPage.clickDetailButton(projectManagementPage.selectListItem((l, le) -> projectManagementPage.acquireStatusButton(le).getText().equals("进行中")).get(0));
        MemberPage f = projectDetailPage.enterMemberTab();
        // Do
        WebElement r=f.selectMemberListItem(line->true);
        MemberInfo memberInfo=f.translateFromLine(r);
        f= f.clickDeleteButton(r,false).clickCancelButton();
        WebElement e= f.checkMemberLineExists(memberInfo);
        assertThat(e, Is.is(notNullValue()));


    }
}
