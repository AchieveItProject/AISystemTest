package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.entity.ProjectInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.achieveit.systemtest.pages.Page.wait;
import static com.achieveit.systemtest.pages.Page.webDriver;

public class ProjectEditDialogPage extends ProjectManagementPage {

    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form")
    @CacheLookup
    private WebElement editFormDialog;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[1]/div/div/input")
    @CacheLookup
    private WebElement editProjectId;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[2]/div/div/input")
    @CacheLookup
    private WebElement editProjectName;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[3]/div/div/input")
    @CacheLookup
    private WebElement editProjectMaster;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[4]/div/div/input")
    @CacheLookup
    private WebElement editProjectCustomerInfo;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[5]/div/div/input")
    @CacheLookup
    private WebElement editProjectMilestone;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[6]/div/div/textarea")
    @CacheLookup
    private WebElement editProjectFunc;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[7]/div/div/input")
    @CacheLookup
    private WebElement editProjectTechnology;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[8]/div/div/input")
    @CacheLookup
    private WebElement editProjectField;
    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[9]/div/div/input")
    @CacheLookup
    private WebElement editProjectDialog;

    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[2]/form/div[10]/div/div/input")
    @CacheLookup
    private WebElement editProjectFinishDialog;

    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[3]/span/button[2]")
    @CacheLookup
    private WebElement submitButton;
    @FindBy(xpath="//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[3]/span/button[1]")
    @CacheLookup
    private WebElement cancelButton;
    public ProjectEditDialogPage(Page p) {
        super(p);
        System.out.println(webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[1]/span")).getText());
        wait.until((wd)->wd.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/section/section/main/div/div[4]/div/div[1]/span")).getText().equals("编辑"));
        PageFactory.initElements(webDriver,this);
    }

    public ProjectEditDialogPage modifyProjectName(String newName){
        editProjectName.clear();
        editProjectName.sendKeys(newName);
        return this;
    }
    public ProjectEditDialogPage modifyProjectMaster(String newMaster){
        editProjectMaster.clear();
        editProjectMaster.sendKeys(newMaster);
        return this;
    }
    public ProjectEditDialogPage modifyProjectMilestone(String newMileStone){
        editProjectMilestone.clear();
        editProjectMilestone.sendKeys(newMileStone);
        return this;
    }
    public ProjectEditDialogPage modifyProjectCustomerInfo(String newCustomerInfo) {
        editProjectCustomerInfo.clear();
        editProjectCustomerInfo.sendKeys(newCustomerInfo);
        return this;
    }
    public ProjectEditDialogPage modifyProjectFunc(String newFunc) {
        editProjectFunc.clear();
        editProjectFunc.sendKeys(newFunc);
        return this;
    }
    public ProjectEditDialogPage modifyProjectTech(String newTech) {
        editProjectTechnology.clear();
        editProjectTechnology.sendKeys(newTech);
        return this;
    }
    public ProjectEditDialogPage modifyProjectField(String newField) {
        editProjectField.clear();
        editProjectField.sendKeys(newField);
        return this;
    }
    public ProjectEditDialogPage modifyProjectScheduleDate(String newDate,String newTime) {
        editProjectDialog.click();
        editProjectDialog.clear();
        editProjectDialog.sendKeys(newDate+" "+newTime);
        return this;
    }
    public ProjectEditDialogPage modifyProjectFinishDate(String newDate,String newTime) {
        editProjectFinishDialog.click();
        editProjectFinishDialog.clear();
        editProjectFinishDialog.sendKeys(newDate+" "+newTime);
        return this;
    }
    public ProjectManagementPage submitProject(){
        while(!webDriver.findElement(By.xpath(" /html/body/div[1]/div/section/section/section/section/main/div/div[4]")).getCssValue("display").equals("none"))
            {
            submitButton.click();
            try{
                Thread.sleep(500);
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        return this;
    }
    public ProjectManagementPage cancelProject(){
        cancelButton.click();
        return this;
    }
    public ProjectInfo catchCurrentProjectInfo(){
        System.out.println(editProjectDialog.getAttribute("value"));
        ProjectInfo projectInfo=new ProjectInfo(
                editProjectName.getAttribute("value"),
                editProjectMaster.getAttribute("value"),
                editProjectCustomerInfo.getAttribute("value"),
                editProjectMilestone.getAttribute("value"),
                editProjectFunc.getAttribute("value"),
                editProjectTechnology.getAttribute("value"),
                editProjectField.getAttribute("value"),

                editProjectDialog.getAttribute("value"),
                editProjectFinishDialog.getAttribute("value")
        );
        return projectInfo;
    }
}
