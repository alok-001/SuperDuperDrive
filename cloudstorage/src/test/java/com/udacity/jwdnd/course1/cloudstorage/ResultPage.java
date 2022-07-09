package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id ="return-home")
    WebElement returnHome;

    @FindBy(id = "success")
    WebElement homeLinkFromSuccess;

    @FindBy(id = "return-home-error")
    WebElement homeLinkFromError;

    @FindBy(id = "Others")
    WebElement Another;

    private final JavascriptExecutor js;

    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js= (JavascriptExecutor) webDriver;
    }


    public void getAnother() {
        Another.click();
    }

    public void goToHomeLinkFromSuccess() {
        js.executeScript("arguments[0].click();", returnHome);
    }

    public void goToHomeLinkFromError() {
        homeLinkFromError.click();
    }
}
