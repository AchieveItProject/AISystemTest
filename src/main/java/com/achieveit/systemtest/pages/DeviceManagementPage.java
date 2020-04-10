package com.achieveit.systemtest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeviceManagementPage implements Page {
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[1]/div/button")
    @CacheLookup
    private WebElement deviceSearchButton;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[2]/button")
    @CacheLookup
    private  WebElement newDeviceButton;
    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[3]/button")
    @CacheLookup
    private WebElement deleteGroupButton;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[2]/div[3]/table/tbody")
    @CacheLookup
    private WebElement deviceList;
    public DeviceManagementPage(Page p) {
        wait.until(wd -> wd.getTitle().equals("设备管理"));
        PageFactory.initElements(webDriver, this);
    }



}
