package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AchievePage implements Page {
    private WebElement[] files;

    public AchievePage(Page p){
//        super(p);
        files=new WebElement[17];
        for (int i = 0; i < 17; i++) {
            int j=i+1;
            files[i]=webDriver.findElement(By.xpath("/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[2]/div/div/div/div[4]/div[2]/table/tbody/tr["+j+"]/td[2]/div/button"));
        }
    }
    public AchievePage checkCompleteness(int filePos, boolean expected){
       String completeness= files[filePos].getText();
       if(expected){
           assertThat(completeness,is("完整"));
       }
       else{
           assertThat(completeness,is("不完整"));
       }
       return this;
    }
}
