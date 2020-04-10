package com.achieveit.systemtest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;

public class ProjectDetailPage implements Page{
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[1]/div/div/div/div[2]")
    private WebElement infoTab;
@FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[1]/div/div/div/div[3]")
   private WebElement functionTab;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[1]/div/div/div/div[4]")
    private WebElement memberTab;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[1]/div/div/div/div[5]")
    private WebElement timeTab;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[1]/div/div/div/div[6]")
    private WebElement defectTab;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[1]/div/div/div/div[7]")
    private WebElement riskTab;
    public ProjectDetailPage(Page p){
        PageFactory.initElements(webDriver,this);
    }
    public InfoPage enterInfoTab(){
        infoTab.click();
        pauseOperation(500);
        return new InfoPage(this);
    }
    public FunctionPage enterFunctionTab(){
        functionTab.click();
        pauseOperation(500);
        return new FunctionPage(this);
    }
    public MemberPage enterMemberTab(){
        memberTab.click();
        pauseOperation(500);
        return new MemberPage(this);
    }

}
