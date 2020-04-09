package com.achieveit.systemtest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FunctionPage extends ProjectDetailPage {
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div/div/div/div/div[2]/div[2]/div[1]/div[2]/button")
    private WebElement newFunctionButton;

    public FunctionPage(ProjectDetailPage p) {
        super(p);
    }
}
