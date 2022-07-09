package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {


    private WebDriver webDriverWait;

    private final JavascriptExecutor js;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
        webDriverWait = webDriver;
    }

    public void setWebDriverWait(WebDriver webDriverWait) {
        this.webDriverWait = webDriverWait;
    }

    public JavascriptExecutor getJs() {
        return js;
    }


    @FindBy(id="btnLogout")
    WebElement btnLogout;

    public void logout(){
        js.executeScript("arguments[0].click();", btnLogout);
    }

//    NOTES

    @FindBy(id = "nav-notes-tab")
    WebElement notesTab;

    @FindBy(id="editnote-button")
    WebElement editNoteButton;

    @FindBy(id="save-note")
    WebElement submitNoteButton;

    @FindBy(id="deletenotelink")
    WebElement deleteNoteLink;

    @FindBy(id="addnote-button")
    WebElement addNoteButton;

    @FindBy(id="note-title")
    WebElement noteTitle;

    @FindBy(id = "notetitle-display")
    private WebElement noteTitleDisplayed;

    @FindBy(id = "notedescription-display")
    private WebElement noteDescriptionDisplayed;

    @FindBy(id="note-description")
    WebElement noteDescription;

    public void NoteTabClicked() {
        js.executeScript("arguments[0].click();", notesTab);
    }

    public void AddNoteButtonClicked() {
        js.executeScript("arguments[0].click();", addNoteButton);
    }

    public void addNote(String noteTitle, String noteDescription) {
        js.executeScript("arguments[0].value='" + noteTitle + "';", this.noteTitle);
        js.executeScript("arguments[0].value='" + noteDescription + "';", this.noteDescription);
        js.executeScript("arguments[0].click();", submitNoteButton);
    }

    public void EditNoteButtonClicked() {
        js.executeScript("arguments[0].click();", editNoteButton);
    }

    public void NoteDeleteClicked() {
        js.executeScript("arguments[0].click();", deleteNoteLink);
    }

    public String getNoteTitleDisplayed() {
        return noteTitleDisplayed.getAttribute("innerHTML");
    }

    public String getNoteDescriptionDisplayed() {
        return noteDescriptionDisplayed.getAttribute("innerHTML");
    }



//    CREDENTIALS

    @FindBy(id="nav-credentials-tab")
    WebElement navCredential;

    @FindBy(id="btnNewCredential")
    WebElement addCredentialbtn;

    @FindBy(id="credentialurl-display")
    WebElement credentialURLDisplay;

    @FindBy(id="credentialusername-display")
    WebElement CredentialUsernameDisplay;

    @FindBy(id="credentialpassword-display")
    WebElement CredentialPasswordDisplay;

    @FindBy(id="editcredential-button")
    WebElement editCredentialbtn;

    @FindBy(id="deletecredential-button")
    WebElement deleteCredentialbtn;

    @FindBy(id="credential-url")
    WebElement credentialUrl;

    @FindBy(id="credential-username")
    WebElement credentialUsername;

    @FindBy(id="credential-password")
    WebElement credentialPassword;

    @FindBy(id="saveCredentialChange")
    WebElement submitCredentialButton;

    public void CredentialTableClicked() {
        js.executeScript("arguments[0].click();", navCredential);
    }

    public String getCredentialURLDisplay() {
        return credentialURLDisplay.getAttribute("innerHTML");
    }

    public String getCredentialUsernameDisplay() {
        return CredentialUsernameDisplay.getAttribute("innerHTML");
    }

    public void AddCredentialbtn() {
        js.executeScript("arguments[0].click();", addCredentialbtn);
    }

    public void EditCredentialbtn() {
        js.executeScript("arguments[0].click();", editCredentialbtn);
    }

    public void DeleteCredentialbtn() {
        js.executeScript("arguments[0].click();", deleteCredentialbtn);
    }

    public void AddCredential(String credentialURL, String credentialUsername, String credentialPassword) {
        js.executeScript("arguments[0].value='" + credentialURL + "';", this.credentialUrl);
        js.executeScript("arguments[0].value='" + credentialUsername + "';", this.credentialUsername);
        js.executeScript("arguments[0].value='" + credentialPassword + "';", this.credentialPassword);
        js.executeScript("arguments[0].click();", submitCredentialButton);
    }

}
