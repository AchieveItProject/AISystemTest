package com.achieveit.systemtest.pages;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;

public class ProjectSearchSubPage extends ProjectManagementPage{
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[1]/div/div/input")
    private WebElement idSearch;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[2]/div/div/input")
    private WebElement nameSearch;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[3]/div/div/input")
    private WebElement leaderSearch;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[4]/div/button[1]")
    private WebElement submit;
    @FindBy(xpath="/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]/div/form/div[4]/div/button[2]")
    private WebElement cancel;
    public ProjectSearchSubPage(Page p){
        super(p);
    }
    public ProjectSearchSubPage inputSearchId(String id){
        idSearch.sendKeys(id);
        return this;
    }
    public ProjectSearchSubPage inputSearchName(String name){
        nameSearch.sendKeys(name);
        return this;
    }
    public ProjectSearchSubPage inputSearchLeader(String leader){
        leaderSearch.sendKeys(leader);
        return this;
    }
    public ProjectSearchSubPage clickSubmitButton(){
        submit.click();
        pauseOperation(500);
        return this;
    }
    public ProjectSearchSubPage clickCancelButton(){
        cancel.click();
        pauseOperation(500);
        return this;
    }
}
