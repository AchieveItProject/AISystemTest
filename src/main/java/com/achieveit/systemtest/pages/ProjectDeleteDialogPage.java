package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static org.openqa.selenium.By.xpath;

public class ProjectDeleteDialogPage extends ProjectManagementPage {

    private WebElement sure;
    private WebElement cancel;
    public ProjectDeleteDialogPage(Page p) {
        super(p);
        pauseOperation(500);
        WebElement deleteDialog=findDeleteDialog();
        setAllButton(deleteDialog);
        wait.until((wd)->wd.findElement(xpath("/html/body/div[2]/div/div[1]/div/span")).getText().equals("提示"));
pauseOperation(500);
    }

    public WebElement findDeleteDialog(){
        List<WebElement> list=webDriver.findElement(By.xpath("/html/body")).findElements(By.tagName("div"));
        for (WebElement e :
                list) {
            if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "提示")) return e;
        }
        return null;
    }
    public void setAllButton(WebElement dialog){
        List<WebElement> list=dialog.findElements(By.tagName("button"));
       cancel= list.get(0);
       sure=list.get(2);
    }
    public ProjectManagementPage clickMakeSureButton(){
        WebElement dialog=findDeleteDialog();
        while(!dialog.getCssValue("display").equals("none")){
            setAllButton(dialog);
            sure.click();
            pauseOperation(500);
        }
        return this;
    }
    public ProjectManagementPage clickCancelButton(){
        WebElement dialog=findDeleteDialog();
        while(!dialog.getCssValue("display").equals("none")){
            setAllButton(dialog);
            cancel.click();
            pauseOperation(500);
        }
        return this;
    }
}
