package com.planittesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    @FindBy(xpath = "//*[@id=\"nav-home\"]/a")
    WebElement homePageMenu;

    @FindBy(xpath = "//h1")
    WebElement homePageHeading;

    @FindBy(xpath = "//a[text()=\"Start Shopping »\"]")
    WebElement startShoppingBtn;

    @FindBy(xpath = "//p[text()=\"Welcome to Jupiter Toys, a magical world for \"]")
    WebElement welcomeText;

    @FindBy(xpath = "//*[@id=\"nav-shop\"]/a")
    WebElement shopMenu;

    @FindBy(xpath = "//*[@id=\"nav-contact\"]/a")
    WebElement contactMenu;

    @FindBy(xpath = "//*[@id=\"nav-cart\"]/a")
    WebElement cartMenu;

    public HomePage(WebDriver driver) {super(driver);}

    public void clickHomeMenu(){
        homePageMenu.click();
    }

    public String getHomePageHeader(){
        return homePageHeading.getText();
    }

    public void getHomePageWelcomeMessage(){
        welcomeText.getText();
    }

    public void navigateToContactMenu() throws InterruptedException{
        if(contactMenu.isDisplayed()){
            contactMenu.click();
        }else{
            driver.wait(5000); //this can be replaced with explicit wait
            contactMenu.click();
        }
    }

    public void navigateToShopMenu() throws InterruptedException{
        if(shopMenu.isDisplayed()){
            shopMenu.click();
        }else{
            driver.wait(5000); //this can be replaced with explicit wait
            shopMenu.click();
        }
    }

    public void navigateToCartMenu() throws InterruptedException{
        if(cartMenu.isDisplayed()){
            cartMenu.click();
        }else{
            cartMenu.wait(5000); //this can be replaced with explicit wait
            cartMenu.click();
        }
    }

}
