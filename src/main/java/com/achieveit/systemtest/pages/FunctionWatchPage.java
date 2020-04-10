package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.entity.FunctionInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FunctionWatchPage extends FunctionPage {
    public FunctionWatchPage(ProjectDetailPage p){
        super(p);

    }

    private WebElement cancel;

    public FunctionWatchPage(FunctionPage p) {
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
            if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "查看功能")) return e;
        }
        return null;
    }
    public void setAllButton(WebElement dialog){
        List<WebElement> list=dialog.findElements(By.tagName("button"));
        cancel= list.get(1);

    }


    public FunctionPage clickCancelButton(){
        WebElement dialog= findFuncDialog();

            setAllButton(dialog);
            cancel.click();
            pauseOperation(500);

        return this;
    }
    public FunctionWatchPage verifyInfo(FunctionInfo p){
        WebElement dialog= findFuncDialog();

        List<WebElement> inputs=dialog.findElements(By.tagName("input"));
        assertThat( inputs.get(0).getAttribute("value")  , is(p.getFunctionId()));
        assertThat(inputs.get(1).getAttribute("value")  ,is(p.getName()));
        assertThat(inputs.get(2).getAttribute("value")  ,is(p.getPeople()));
        return this;
    }
}
