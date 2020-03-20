package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WelcomePage implements Page {
    private WebElement titleOnPage;

    public WelcomePage(Page p) {

    }

    public WelcomePage selectTitleOnPage(){
        if(titleOnPage==null)
            titleOnPage=webDriver.findElement(By.className("nohover"));
        return this;
    }
    public WelcomePage verifyTitleOnPageClassName(){
        selectTitleOnPage();
        assertThat(titleOnPage.getTagName(),is("li"));
        return this;
    }

}
