package com.achieveit.systemtest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;

public class DeviceSearchSubPage extends ProjectManagementPage{
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[1]/div/div/input")
    private WebElement managerSearch;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[2]/div/div/input")
    private WebElement statusSearch;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[3]/div/div/input")
    private WebElement ddlSearch;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[4]/div/button[1]")
    private WebElement submit;
    @FindBy(xpath="/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[4]/div/button[2]")
    private WebElement cancel;
    public DeviceSearchSubPage(Page p){
        super(p);
    }
    public DeviceSearchSubPage inputManagerName(String name){
        managerSearch.sendKeys(name);
        return this;
    }
    public DeviceSearchSubPage inputStatus(String status){
        statusSearch.sendKeys(status);
        return this;
    }
    public DeviceSearchSubPage inputDDL(String ddl){
        ddlSearch.sendKeys(ddl);
        return this;
    }
    public DeviceSearchSubPage clickSubmitButton(){
        submit.click();
        pauseOperation(500);
        return this;
    }
    public DeviceSearchSubPage clickCancelButton(){
        cancel.click();
        pauseOperation(500);
        return this;
    }
}
