package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;

public class FunctionUploadPage extends FunctionPage {

    private WebElement sure;
    private WebElement template;
    private WebElement uploadInput;
    private WebElement uploadButton;
    public FunctionUploadPage(FunctionPage p) {
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
            if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "上传excel文件"))
                return e;
        }
        return null;
    }
    public void setAllButton(WebElement dialog){
        uploadInput=dialog.findElement(By.tagName("input"));
uploadButton=dialog.findElements(By.tagName("button")).get(1);
template=dialog.findElements(By.tagName("span")).get(0);
    }
    public FunctionUploadPage inputUploadPath(String name){
        uploadInput.clear();

        uploadInput.sendKeys(name);
        return this;
    }

    public FunctionPage clickUploadButton(){
        WebElement dialog= findFuncDialog();
        setAllButton(dialog);
        uploadButton.click();
        pauseOperation(500);
//        Actions actions=new Actions(webDriver);
//        actions.sendKeys(f.getAbsolutePath()).sendKeys(Keys.ENTER).perform();
//        pauseOperation(500);
        return this;
    }
    public FunctionPage clickDownloadTemplateButton(){
        WebElement dialog= findFuncDialog();
        setAllButton(dialog);
        uploadButton.click();
        pauseOperation(500);
        return this;
    }

}
