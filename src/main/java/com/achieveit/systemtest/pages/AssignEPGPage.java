package com.achieveit.systemtest.pages;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.xpath;

public class AssignEPGPage extends ProjectManagementPage {
    public AssignEPGPage(Page page) {
        super(page);
        pauseOperation(500);
        WebElement psc=findAssignEPGDialog();
        setAllElements(psc);
        pauseOperation(500);
    }

    private WebElement sure;
    private WebElement cancel;
    private WebElement idInput;


    public WebElement findAssignEPGDialog(){
        List<WebElement> list=webDriver.findElement(By.xpath("/html/body")).findElements(By.tagName("div"));
        for (WebElement e :
                list) {
            if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "分配EPG")) return e;
        }
        return null;
    }
    public void setAllElements(WebElement dialog){
        List<WebElement> list=dialog.findElements(By.tagName("button"));
        cancel= list.get(1);
        sure=list.get(3);
        idInput=dialog.findElement(By.tagName("input"));
    }
    public AssignEPGPage inputEPGId(String id){
        WebElement dialog=findAssignEPGDialog();
        setAllElements(dialog);
        idInput.sendKeys(id);
        return this;
    }
    public ProjectManagementPage clickMakeSureButton(){
        WebElement dialog=findAssignEPGDialog();
        setAllElements(dialog);
        sure.click();
        pauseOperation(500);
        return this;
    }
    public ProjectManagementPage clickCancelButton(){
        WebElement dialog=findAssignEPGDialog();
        setAllElements(dialog);
        cancel.click();
        pauseOperation(500);
        return this;
    }
    public ProjectManagementPage checkContent(String input,boolean expectedResult){
        WebElement dialog= findAssignEPGDialog();
        setAllElements(dialog);
        if(expectedResult) {
            assertThat(idInput.getText(), Is.is(input));
        }else{
            assertThat(idInput.getText(), IsNot.not(input));
        }
        return this;
    }

}
