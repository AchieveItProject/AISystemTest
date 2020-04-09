package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.By.xpath;

public class ProjectApprovalPage extends ProjectManagementPage{

        private WebElement sure;
        private WebElement cancel;
        private WebElement approval;
        private WebElement reject;

        public ProjectApprovalPage(Page p) {
            super(p);
            pauseOperation(500);
            WebElement approvalDialog=findApprovalDialog();
            setAllButton(approvalDialog);
//            wait.until((wd)->wd.findElement(xpath("/html/body/div[2]/div/div[1]/div/span")).getText().equals("提示"));
            pauseOperation(500);
        }

        public WebElement findApprovalDialog(){
            List<WebElement> list=webDriver.findElement(By.xpath("/html/body")).findElements(By.tagName("div"));
            for (int i=0;i<list.size();i++) {
                WebElement e=list.get(i);
                if (  e.getAttribute("aria-label") !=null&&e.getAttribute("aria-label").equals( "审批项目")) {
                    return e;
                }

            }
            return null;
        }
        public void setAllButton(WebElement dialog){
            List<WebElement> l= dialog.findElements(By.tagName("span"));
            approval=l.get(1);
            reject=l.get(2);
            List<WebElement> list=dialog.findElements(By.tagName("button"));
            cancel= list.get(1);
            sure=list.get(2);
        }

    public ProjectApprovalPage clickApprovalButton() {
        Actions actions=new Actions(webDriver);

    approval.click();
        return this;
    }
    public ProjectApprovalPage clickRejectButton() {
        Actions actions=new Actions(webDriver);
        actions.moveToElement(reject).click().perform();
//        reject.click();
        return this;
    }
        public ProjectManagementPage clickMakeSureButton(){
            WebElement dialog=findApprovalDialog();
            setAllButton(dialog);

            sure.click();
            pauseOperation(500);

            return this;
        }
        public ProjectManagementPage clickCancelButton(){
            WebElement dialog=findApprovalDialog();
            setAllButton(dialog);
            cancel.click();
            pauseOperation(500);
            return this;
        }

}
