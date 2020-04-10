package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;

public class ChangeToAchievePage extends ProjectManagementPage{
    public ChangeToAchievePage(Page page) {
        super(page);
        pauseOperation(500);
        WebElement psc=findProjectStatusChangeDialog();
        setAllButton(psc);
        pauseOperation(500);
    }

    private WebElement sure;
    private WebElement cancel;


    public WebElement findProjectStatusChangeDialog(){
        List<WebElement> list=webDriver.findElement(By.xpath("/html/body")).findElements(By.tagName("div"));
        for (int i=0;i<list.size();i++) {
            WebElement e=list.get(i);
            if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "项目已归档")) return e;
        }
        return null;
    }
    public void setAllButton(WebElement dialog){
        List<WebElement> list=dialog.findElements(By.tagName("button"));
        cancel= list.get(1);
        sure=list.get(8);
    }
    public ProjectManagementPage clickMakeSureButton(){
        WebElement dialog=findProjectStatusChangeDialog();
        setAllButton(dialog);
        sure.click();
        pauseOperation(500);
        return this;
    }
    public ProjectManagementPage clickCancelButton(){
        WebElement dialog=findProjectStatusChangeDialog();
        setAllButton(dialog);
        cancel.click();
        pauseOperation(500);
        return this;
    }
}
