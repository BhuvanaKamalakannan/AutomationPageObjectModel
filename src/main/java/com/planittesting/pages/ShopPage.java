package com.planittesting.pages;

import com.planittesting.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopPage extends BasePage{
    @FindBy(xpath = "//*[@id=\"product-2\"]//a[text()='Buy']")
    WebElement stuffedFrogBuy;

    @FindBy(xpath = "//*[@id=\"product-4\"]//a[text()='Buy']")
    WebElement fluffyBunnyBuy;

    @FindBy(xpath = "//*[@id=\"product-7\"]//a[text()='Buy']")
    WebElement valentineBearBuy;

    public ShopPage(WebDriver driver) {super(driver);}

    public void buyStuffedFrog(){
        if(stuffedFrogBuy.isDisplayed()){
            Log.info("----- About to click stuffed frog buy -----");
            stuffedFrogBuy.click();
        }else{
            Log.error("###### Stuffed Frog is not displayed #######");
        }
    }

    public void buyFluffyBunny(){
        if(fluffyBunnyBuy.isDisplayed()){
            Log.info("----- About to click Fluffy Bunny buy -----");
            fluffyBunnyBuy.click();
        }else{
            Log.error("###### Fluffy Bunny is not displayed #######");
        }
    }

    public void buyValentineBear(){
        if(valentineBearBuy.isDisplayed()){
            Log.info("----- About to click Valentine Bear buy -----");
            valentineBearBuy.click();
        }else{
            Log.error("###### Valentine Bear is not displayed #######");
        }
    }
}
