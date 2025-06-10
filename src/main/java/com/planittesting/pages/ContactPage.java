package com.planittesting.pages;

import net.bytebuddy.implementation.InvokeDynamic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactPage extends BasePage{
    @FindBy(xpath = "//input[@id=\"forename\"]")
    WebElement forename;

    @FindBy(xpath = "//span[@id=\"forename-err\"]")
    WebElement forenameError;

    @FindBy(xpath = "//input[@id=\"surname\"]")
    WebElement surname;

    @FindBy(xpath = "//input[@id=\"email\"]")
    WebElement email;

    @FindBy(xpath = "//span[@id=\"email-err\"]")
    WebElement emailError;

    @FindBy(xpath = "//input[@id=\"telephone\"]")
    WebElement telephone;

    @FindBy(xpath = "//textarea[@id=\"message\"]")
    WebElement message;

    @FindBy(xpath = "//span[@id=\"message-err\"]")
    WebElement messageError;

    @FindBy(xpath = "//a[text()='Submit']")
    WebElement submitBtn;

    @FindBy(xpath = "//div[@class=\"alert alert-success\"]")
    WebElement submitSuccessMessage;

    String forenameErrorXpath = "//span[@id=\"forename-err\"]";
    String emailErrorXpath = "//span[@id=\"email-err\"]";
    String messageErrorXpath = "//span[@id=\"message-err\"]";

    public ContactPage(WebDriver driver) {super(driver);}

    public void enterForename(String name){
        forename.sendKeys(name);
    }

    public void enterSurname(String name){
        surname.sendKeys(name);
    }

    public void enterEmail(String emailVal){
        email.sendKeys(emailVal);
    }

    public void enterMessage(String messageTxt){
        message.sendKeys(messageTxt);
    }

    public void clickSubmit(){
        submitBtn.click();
    }

    public Boolean isForenameErrorNotFound(){
       return isElementNotFound(forenameErrorXpath);
    }

    public Boolean isEmailErrorFound(){
        return isElementNotFound(emailErrorXpath);
    }

    public Boolean isMessageErrorFound(){
        return isElementNotFound(messageErrorXpath);
        /*if(isElementNotFound(messageError)) {
            System.out.println("Message Error not found");
            return false;
        }else
            return true;*/
    }

    public String getForenameError(){
        if(forenameError.isDisplayed()){
            return forenameError.getText();
        } else
            return null;
    }

    public String getEmailError(){
        if(emailError.isDisplayed()){
            return emailError.getText();
        } else
            return null;
    }

    public String getMessageError(){
        if(messageError.isDisplayed()){
            return messageError.getText();
        } else
            return null;
    }

    public String getContactSubmitMessage(){
        isWebElementFound(submitSuccessMessage);
        return submitSuccessMessage.getText();
    }

}
