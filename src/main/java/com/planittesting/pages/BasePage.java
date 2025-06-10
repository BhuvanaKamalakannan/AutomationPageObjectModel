package com.planittesting.pages;

import com.planittesting.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;


public class BasePage{
    protected WebDriver driver;
    private static WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //****************************************************************************************************************************************************
    //Is Element Found
    public Boolean isWebElementFound (WebElement we){
        try {
            wait.until(ExpectedConditions.visibilityOf(we));
            return we.isEnabled() && we.isDisplayed();
        }
        catch (NoSuchElementException ne){
            //Log.warn("!!!!!!!!!!!!! (DriverUtil warning) -  No such Element Exception in isWebElementFound() --------- "+ne.getMessage());
            return false;
        }
        catch (Exception e){
            //Log.warn("!!!!!!!!!!!!! (DriverUtil warning) -  Exception in isWebElementFound() with default time secs ------------ "+e.getMessage());
            return false;
        }
    }

    //****************************************************************************************************************************************************
    public  Boolean isElementNotFound (String xpathVal) {
        try {
            List elementCount = this.driver.findElements(By.xpath(xpathVal));
            if(elementCount.size() > 0 ){
                System.out.println("Element is found");
                return false;
            }else {
                System.out.println("Element not found");
                return true;
            }

        } catch (NoSuchElementException ne) {
            //Log.warn("!!!!!!!!!!!!! (DriverUtil warning) -   NoSuchElementException in element not found function------------ "+ne.getMessage() );
            return true;
        }
    }


}
