package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.entity.ProjectInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static com.achieveit.systemtest.pages.Page.wait;
import static com.achieveit.systemtest.pages.Page.webDriver;

public class ProjectCreateDialogPage extends ProjectManagementPage {

    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form")
    @CacheLookup
    private WebElement newFormDialog;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[1]/div/div/input")
    @CacheLookup
    private WebElement newProjectName;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[2]/div/div/input")
    @CacheLookup
    private WebElement newProjectMaster;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[3]/div/div/input")
    @CacheLookup
    private WebElement newProjectCustomerInfo;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[4]/div/div/input")
    @CacheLookup
    private WebElement newProjectMilestone;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[5]/div/div/textarea")
    @CacheLookup
    private WebElement newProjectFunc;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[6]/div/div/input")
    @CacheLookup
    private WebElement newProjectTechnology;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[7]/div/div/input")
    @CacheLookup
    private WebElement newProjectField;
    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[8]/div/div/input")
    @CacheLookup
    private WebElement newProjectDialog;

    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[9]/div/div/input")
    @CacheLookup
    private WebElement newProjectFinishDialog;

    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[3]/span/button[2]")
    @CacheLookup
    private WebElement submitButton;
    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[3]/span/button[1]")
    @CacheLookup
    private WebElement cancelButton;
    public ProjectCreateDialogPage(Page p) {
        super(p);
        System.out.println(webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[1]/span")).getText());
        wait.until((wd)->wd.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[1]/span")).getText().equals("新增"));
        PageFactory.initElements(webDriver,this);
    }
    public ProjectCreateDialogPage fillProjectInfo(ProjectInfo p){
        newProjectName.clear();
        newProjectName.sendKeys(p.getProjectName());
        newProjectMaster.clear();
        newProjectMaster.sendKeys(p.getLeader());
        newProjectCustomerInfo.clear();
        newProjectCustomerInfo.sendKeys(p.getCustomerInfo());
        newProjectMilestone.clear();
        newProjectMilestone.sendKeys(p.getMilepost());
        newProjectFunc.clear();
        newProjectFunc.sendKeys(p.getProjectFunction());
        newProjectTechnology.clear();
        newProjectTechnology.sendKeys(p.getTechnology());
        newProjectField.clear();
        newProjectField.sendKeys(p.getBusinessArea());

        newProjectDialog.click();
        newProjectDialog.clear();
        newProjectDialog.sendKeys(p.getScheduleDate());


        newProjectFinishDialog.click();
        newProjectFinishDialog.clear();
        newProjectFinishDialog.sendKeys(p.getDeliveryDate());

        return this;
    }
    // Bad net test!
    public ProjectManagementPage submitProject(){

        submitButton.click();
        pauseOperation(500);
        WebElement dialog=webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]"));
        while(!dialog.getCssValue("display").equals("none")){
            submitButton.click();
            pauseOperation(500);
        }
        return this;
    }
    public ProjectManagementPage cancelProject(){
        cancelButton.click();
        return this;
    }
}
