package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;

public class MemberCreatePage extends MemberPage {

    private WebElement sure;
    private WebElement cancel;
    private WebElement memberIdInput;
    private WebElement memberRole;
    public MemberCreatePage(MemberPage p) {
        super(p);
        pauseOperation(500);
        WebElement funcDialog= findFuncDialog();
        setAllButton(funcDialog);
        pauseOperation(500);
    }

    public WebElement findFuncDialog(){
        List<WebElement> list=webDriver.findElement(By.xpath("/html/body")).findElements(By.tagName("div"));
        for (int i=0;i<list.size();i++) {
            WebElement e=list.get(i);
            if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "新增组员"))
                return e;
        }
        return null;
    }
    public void setAllButton(WebElement dialog){
        List<WebElement> inputs=dialog.findElements(By.tagName("input"));

            memberIdInput =inputs.get(1);
            memberRole =inputs.get(2);

        List<WebElement> list=dialog.findElements(By.tagName("button"));
        cancel= list.get(1);
        sure=list.get(2);

    }
    public MemberCreatePage inputMemberIdName(String id){
        memberIdInput.clear();
        memberIdInput.sendKeys(id);
        return this;
    }
    public MemberCreatePage inputMemberRole(String pos){
        Actions buttonAction=new Actions(webDriver);
        buttonAction.moveToElement(memberRole).click().perform();
        pauseOperation(500);
       List<WebElement> list= webDriver.findElement(By.className("el-select-dropdown")).findElements(By.tagName("li"));
      Actions actions=new Actions(webDriver);
      actions.moveToElement(list.get(Integer.parseInt(pos))).click().perform();
      pauseOperation(500);

        return this;
    }
    public MemberPage clickMakeSureButton(){
        WebElement dialog= findFuncDialog();
        setAllButton(dialog);
        sure.click();
        pauseOperation(500);
        return this;
    }
    public MemberPage clickCancelButton(){
        WebElement dialog= findFuncDialog();

        setAllButton(dialog);
        cancel.click();
        pauseOperation(500);

        return this;
    }
}
