package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.entity.FunctionInfo;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.function.Function;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static org.hamcrest.MatcherAssert.assertThat;

public class FunctionPage extends ProjectDetailPage {
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[2]/div[2]/div[1]/div[2]/button")
    @CacheLookup
    private WebElement newFunctionButton;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[2]/div[2]/div[2]/div/div[3]/table/tbody")
//                     /html/body/div/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[2]/div[2]/div[2]/div/div[3]/table/tbody
    @CacheLookup
    private WebElement functionList;

    public FunctionPage(ProjectDetailPage p) {
        super(p);
    }

    public int selectFunctionListItemPos(Function<WebElement, Boolean> testMethod) {
        List<WebElement> rows = functionList.findElements(By.tagName("tr"));
        // 1. close all subFunction
        boolean breakable = false;
        while (true){
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).getAttribute("class").contains("expanded")) {
                    WebElement expandControl = rows.get(i).findElement(By.tagName("td")).findElement(By.tagName("div")).findElement(By.tagName("div"))
                            .findElement(By.tagName("i"));
                    Actions actions = new Actions(webDriver);
                    actions.moveToElement(expandControl).click().perform();
                    breakable = true;
                    break;
                }
            }
            if (breakable) {
                rows = functionList.findElements(By.tagName("tr"));
            } else {
                break;
            }
        }
        // 2. getElements
        int target=-1;
        for(int i=0;i<rows.size();i++){
         if(  testMethod.apply(rows.get(i))){
             target=i;
               break;
            }
        }
        return target;



    }
    public WebElement selectFunctionListItem(Function<WebElement, Boolean> testMethod) {

        int target= selectFunctionListItemPos(testMethod);
        if(target==-1) return null;
        return functionList.findElements(By.tagName("tr")).get(target);
    }
    public int selectSubFunctionListItemPos(Function<WebElement, Boolean> testMethod) {
        WebElement subList = webDriver.findElements(By.className("el-table__expanded-cell"))
                .get(0)
                .findElement(By.tagName("div")).findElement(By.tagName("tbody"));
        List<WebElement> rows = subList.findElements(By.tagName("tr"));
        int target=-1;
        for(int i=0;i<rows.size();i++){
            if(  testMethod.apply(rows.get(i))){
                target=i;
                break;
            }
        }
        return target;
    }
    public WebElement selectSubFunctionListItem(Function<WebElement, Boolean> testMethod) {

        int target= selectSubFunctionListItemPos(testMethod);
        if(target==-1) return null;
        return webDriver.findElements(By.className("el-table__expanded-cell"))
                .get(0)
                .findElement(By.tagName("div")).findElement(By.tagName("tbody"))
         .findElements(By.tagName("tr")).get(target);
//        return functionList.findElements(By.tagName("tr")).get(target);
    }
   public FunctionEditPage clickEditButton(WebElement line,boolean isSubFunc){
      WebElement click;
      if(isSubFunc){
          click=line.findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElements(By.tagName("i")).get(1);
      }else{
        click=  line.findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElements(By.tagName("i")).get(2);
      }
        Actions actions=new Actions(webDriver);
        actions.moveToElement(click).click().perform();
        return new FunctionEditPage(this);
    }
    public FunctionCreatePage clickNewButton(){
        newFunctionButton.click();
        pauseOperation(500);
        return new FunctionCreatePage(this);
    }
   public FunctionPage checkEqual(WebElement line, FunctionInfo functionInfo,boolean isSubFunc){
       String id;
       String funcName ;
       String funcPeople;
        if(isSubFunc){
             id = line.findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).getText();
             funcName = line.findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).getText();
             funcPeople = line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText();
        }else {
             id = line.findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).getText();
             funcName = line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText();
             funcPeople = line.findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).getText();
        }
       FunctionInfo ff=new FunctionInfo();
       ff.setName(funcName);
       ff.setFunctionId(id);
       ff.setPeople(funcPeople);
       boolean t= ff.equals(functionInfo);
       assertThat(t, Is.is(true));
        return this;
    }
    public FunctionInfo translateFromLine(WebElement line) {
        String id= line.findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).getText();
        String funcName= line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText();
        String funcPeople= line.findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).getText();
        FunctionInfo ff=new FunctionInfo();
        ff.setName(funcName);
        ff.setFunctionId(id);
        ff.setPeople(funcPeople);
        return ff;
//        return null;
    }
    public FunctionInfo translateFromSubLine(WebElement line) {
        String id= line.findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).getText();
        String funcName= line.findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).getText();
        String funcPeople= line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText();
        FunctionInfo ff=new FunctionInfo();
        ff.setName(funcName);
        ff.setFunctionId(id);
        ff.setPeople(funcPeople);
        return ff;
//        return null;
    }
    public WebElement checkFunctionLineExists(FunctionInfo f){
       return selectFunctionListItem(l->translateFromLine(l).equals(f));
    }
    public WebElement checkSubFunctionLineExists(FunctionInfo f){

        return selectSubFunctionListItem(l->translateFromSubLine(l).equals(f));
    }
    public FunctionWatchPage clickWatchButton(WebElement line,boolean isSubFunc){
        WebElement e;
        if(isSubFunc){
          e = line.findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).
                    findElements(By.tagName("i")).get(0);
        }else {
            e = line.findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).
                    findElements(By.tagName("i")).get(1);
        }
        Actions actions=new Actions(webDriver);
        actions.moveToElement(e).click().perform();
        return new FunctionWatchPage(this);
//        return this;
    }
    public FunctionDeletePage   clickDeleteButton(WebElement line,boolean isSubFunc){
        WebElement e;
        if(isSubFunc){
            e=line.findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).
                    findElements(By.tagName("i")).get(2);
        }else {
            e = line.findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).
                    findElements(By.tagName("i")).get(3);
        }
        Actions actions=new Actions(webDriver);
        actions.moveToElement(e).click().perform();
        return new FunctionDeletePage(this);
    }
    public FunctionPage openDetailDialog(WebElement line){
        WebElement e= line.findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).findElement(By.tagName("div")).
                findElement(By.tagName("i"));
        Actions actions=new Actions(webDriver);
        actions.moveToElement(e).click().perform();
        return this;
    }
    public SubFunctionCreatePage   clickNewSubFuncButton(WebElement line){
        WebElement e= line.findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).
                findElements(By.tagName("i")).get(0);
        Actions actions=new Actions(webDriver);
        actions.moveToElement(e).click().perform();
        return new SubFunctionCreatePage(this);
    }
}
