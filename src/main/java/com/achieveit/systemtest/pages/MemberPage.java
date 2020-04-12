package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.entity.MemberInfo;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.function.Function;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static org.hamcrest.MatcherAssert.assertThat;

public class MemberPage extends ProjectDetailPage {
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[2]/div[3]/div[1]/div[2]/button")
    private WebElement newMemberButton;
@FindBy(xpath="/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[2]/div[3]/div[2]/div/div[3]/table/tbody")
private WebElement memberList;

    public MemberPage(ProjectDetailPage p) {
        super(p);
    }

    public int selectMemberListItemPos(Function<WebElement, Boolean> testMethod) {
        List<WebElement> rows = memberList.findElements(By.tagName("tr"));

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
    public WebElement selectMemberListItem(Function<WebElement, Boolean> testMethod) {

        int target= selectMemberListItemPos(testMethod);
        if(target==-1) return null;
        return memberList.findElements(By.tagName("tr")).get(target);
    }


    public MemberCreatePage clickNewButton(){
        newMemberButton.click();
        pauseOperation(500);
        return new MemberCreatePage(this);
    }
    public MemberPage checkEqual(WebElement line, MemberInfo info, boolean isSubFunc){
        String id;
        String role ;

        id = line.findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).getText();
        role = line.findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).getText();

        MemberInfo ff=new MemberInfo();
        ff.setMemberRole(role);
        ff.setMemberId(id);
        boolean t= ff.equals(info);
        assertThat(t, Is.is(true));
        return this;
    }
    public MemberInfo translateFromLine(WebElement line) {
        String id= line.findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).getText();
        String role= line.findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).getText();
        String name= line.findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText();
        MemberInfo ff=new MemberInfo();
        ff.setMemberRole(role);
        ff.setMemberId(id);
        ff.setMemberName(name);
        return ff;
    }

    public WebElement checkMemberLineExists(MemberInfo f){
        return selectMemberListItem(l->translateFromLine(l).equals(f));
    }


    public MemberDeletePage   clickDeleteButton(WebElement line,boolean isSubFunc){
        WebElement e;
        e = line.findElements(By.tagName("td")).get(8).findElement(By.tagName("div")).
                    findElements(By.tagName("i")).get(0);
        Actions actions=new Actions(webDriver);
        actions.moveToElement(e).click().perform();
        return new MemberDeletePage(this);
    }

}
