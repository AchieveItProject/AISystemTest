package com.achieveit.systemtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WelcomePage implements Page {
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/header/div[2]/ul/li[1]/i")
    @CacheLookup
    private WebElement fullScreen;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/header/div[2]/ul/li[2]/i")
    @CacheLookup
    private WebElement notice;
    @FindBy(className = "dropdown-menu-7900")
    @CacheLookup
    private WebElement info;
    @FindBy(xpath = "//*[@id=\"dropdown-menu-7900\"]/li[1]")
    @CacheLookup
    private WebElement aboutAuthor;
    @FindBy(xpath = "//*[@id=\"dropdown-menu-7900\"]/li[2]")
    @CacheLookup
    private WebElement logout;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/div/ul/li[1]")
    @CacheLookup
    private WebElement projectManagementMenu;
    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/div/ul/li[2]")
    @CacheLookup
    private WebElement deviceManagementMenu;

    public WelcomePage(Page p) {
        wait.until( wd -> wd.getTitle().equals("系统首页") );
        PageFactory.initElements(webDriver,this);
    }

    public ProjectManagementPage selectProjectManagementMenu() {
       projectManagementMenu.click();
       wait.until(wd->wd.findElement(By.xpath("/html/body/div/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[1]/div/button/span")).getText().equals("项目查询"));
       ProjectManagementPage r= new ProjectManagementPage((Page) this);

       return r;
    }

    public WelcomePage verifyTitleOnPageClassName() {
//        selectTitleOnPage();
//        assertThat(titleOnPage.getTagName(), is("li"));
        return this;
    }

}
