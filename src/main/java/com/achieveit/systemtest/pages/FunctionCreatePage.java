package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;

public class FunctionCreatePage extends FunctionPage {

    private WebElement sure;
    private WebElement cancel;
    private WebElement functionNameInput;
    private WebElement functionPerson;
    public FunctionCreatePage(FunctionPage p) {
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
            if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "新增功能")) return e;
        }
        return null;
    }
    public void setAllButton(WebElement dialog){
        List<WebElement> list=dialog.findElements(By.tagName("button"));
        cancel= list.get(1);
        sure=list.get(2);
        List<WebElement> inputs=dialog.findElements(By.tagName("input"));
        functionNameInput=inputs.get(1);
        functionPerson=inputs.get(2);
    }
    public FunctionCreatePage inputFuncName(String name){
        functionNameInput.clear();
        functionNameInput.sendKeys(name);
        return this;
    }
    public FunctionCreatePage inputFuncPerson(String name){
        functionPerson.clear();
        functionPerson.sendKeys(name);
        return this;
    }
    public FunctionPage clickMakeSureButton(){
        WebElement dialog= findFuncDialog();
        setAllButton(dialog);
        sure.click();
        pauseOperation(500);
        return this;
    }
    public FunctionPage clickCancelButton(){
        WebElement dialog= findFuncDialog();

        setAllButton(dialog);
        cancel.click();
        pauseOperation(500);

        return this;
    }
}
