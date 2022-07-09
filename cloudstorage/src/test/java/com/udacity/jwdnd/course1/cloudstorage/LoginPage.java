package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="login-button")
    private WebElement loginButton;

    @FindBy(id="logout-msg")
    private WebElement logoutMsg;

    @FindBy(id="Link-Signup")
    private WebElement SignUpLink;


    public void setInputPassword(WebElement inputPassword) {
        this.inputPassword = inputPassword;
    }

    public void setInputUsername(WebElement inputUsername) {
        this.inputUsername = inputUsername;
    }

    public void setLoginButton(WebElement loginButton) {
        this.loginButton = loginButton;
    }

    public void setLogoutMsg(WebElement logoutMsg) {
        this.logoutMsg = logoutMsg;
    }

    public void login(String username, String password){
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        loginButton.click();
    }
}
