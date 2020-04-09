package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static org.openqa.selenium.By.xpath;

public class ChangeToInProgressPage extends ProjectManagementPage {
    public ChangeToInProgressPage(Page page) {
        super(page);
        pauseOperation(500);
        WebElement psc=findProjectStatusChangeDialog();
        setAllButton(psc);
//        wait.until((wd)->wd.findElement(xpath("/html/body/div[2]/div/div[1]/div/span")).getText().equals("提示"));
        pauseOperation(500);
    }

    private WebElement sure;
    private WebElement cancel;


    public WebElement findProjectStatusChangeDialog(){
        List<WebElement> list=webDriver.findElement(By.xpath("/html/body")).findElements(By.tagName("div"));
        for (WebElement e :
                list) {
            if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "变更项目状态")) return e;
        }
        return null;
    }
    public void setAllButton(WebElement dialog){
        List<WebElement> list=dialog.findElements(By.tagName("button"));
        cancel= list.get(1);
        sure=list.get(5);
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
