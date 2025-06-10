package com.planittesting.pages;

import com.planittesting.utilities.Log;
import com.planittesting.utilities.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    @FindBy(xpath = "//tr[1]/td[3]/input")
    WebElement stuffedFrogInput;

    @FindBy(xpath = "//tr[2]/td[3]/input")
    WebElement fluffyBunnyInput;

    @FindBy(xpath = "//tr[3]/td[3]/input")
    WebElement valentineBearInput;

    @FindBy(xpath = "//tr[1]/td[1]")
    WebElement stuffedFrogItem;

    @FindBy(xpath = "//tr[2]/td[1]")
    WebElement fluffyBunnyItem;

    @FindBy(xpath = "//tr[3]/td[1]")
    WebElement valentineBearItem;

    @FindBy(xpath = "//tr[1]/td[2]")
    WebElement stuffedFrogItemUnitPrice;

    @FindBy(xpath = "//tr[2]/td[2]")
    WebElement fluffyBunnyItemUnitPrice;

    @FindBy(xpath = "//tr[3]/td[2]")
    WebElement valentineBearItemUnitPrice;

    @FindBy(xpath = "//tr[1]/td[4]")
    WebElement stuffedFrogItemTotal;

    @FindBy(xpath = "//tr[2]/td[4]")
    WebElement fluffyBunnyItemTotal;

    @FindBy(xpath = "//tr[3]/td[4]")
    WebElement valentineBearItemTotal;

    @FindBy(xpath = "//tfoot//strong[@class='total ng-binding']")
    WebElement cartTotal;

    public String stuffedFrogUnitPrice;
    public String fluffyBunnyUnitPrice;
    public String valentineBearUnitPrice;

    public String totalPriceStuffedFrog;
    public String totalPriceFluffyBunny;
    public String totalPriceValentineBear;

    public String totalCost;
    //
    public CartPage(WebDriver driver) {super(driver);}

    public void inputQuantityOfStuffedFrog(String stuffFrogVal, String fluffBunnyVal, String valentineBearVal){
        if(stuffedFrogItem.isDisplayed()){
            String str = stuffedFrogItem.getText();
            Log.info("---- Stuffed Frog Item table row get Text value: -----"+str);
            Log.info("String comparison value: "+str.equals("Stuffed Frog"));
            switch(str) {
                case "Stuffed Frog":
                    stuffedFrogInput.clear();
                    stuffedFrogInput.sendKeys(stuffFrogVal);
                    break;
                case "Fluffy Bunny":
                    stuffedFrogInput.clear();
                    stuffedFrogInput.sendKeys(fluffBunnyVal);
                    break;
                case "Valentine Bear":
                    stuffedFrogInput.clear();
                    stuffedFrogInput.sendKeys(valentineBearVal);
                    break;
                default:
                    Log.error("#### Item is not valid ####");
                    break;
            }
        }
    }

    public void inputQuantityOfFluffyBunny(String stuffFrogVal, String fluffBunnyVal, String valentineBearVal){
        if(fluffyBunnyItem.isDisplayed()){
            String str = fluffyBunnyItem.getText();
            Log.info("---- Fluffy Bunny Item table row get Text value: -----"+str);
            Log.info("Fluffy Bunny value: "+str.equals("Fluffy Bunny"));

            switch(str) {
                case "Stuffed Frog":
                    fluffyBunnyInput.clear();
                    fluffyBunnyInput.sendKeys(stuffFrogVal);
                    break;
                case "Fluffy Bunny":
                    fluffyBunnyInput.clear();
                    fluffyBunnyInput.sendKeys(fluffBunnyVal);
                    break;
                case "Valentine Bear":
                    fluffyBunnyInput.clear();
                    fluffyBunnyInput.sendKeys(valentineBearVal);
                    break;
                default:
                    Log.error("#### Item is not valid ####");
                    break;
            }
        }
    }

    public void inputQuantityOfValentineBear(String stuffFrogVal, String fluffBunnyVal, String valentineBearVal){
        if(valentineBearItem.isDisplayed()){
            String str = valentineBearItem.getText();
            Log.info("---- Valentine Bear Item table row get Text value: -----"+str);
            Log.info("FValentine Bear value: "+str.equals("Valentine Bear"));

            switch(str) {
                case "Stuffed Frog":
                    valentineBearInput.clear();
                    valentineBearInput.sendKeys(stuffFrogVal);
                    break;
                case "Fluffy Bunny":
                    valentineBearInput.clear();
                    valentineBearInput.sendKeys(fluffBunnyVal);
                    break;
                case "Valentine Bear":
                    valentineBearInput.clear();
                    valentineBearInput.sendKeys(valentineBearVal);
                    break;
                default:
                    Log.error("#### Item is not valid ####");
                    break;
            }
        }
    }

    public void calculateTotalOfItemsBought(String stuffFrogVal, String fluffBunnyVal, String valentineBearVal){
        if(stuffedFrogItemUnitPrice.isDisplayed()){
            String str = stuffedFrogItemUnitPrice.getText();
            Log.info("---- Valentine Bear Unit Price get Text value: -----"+str);
            Log.info("Valentine Bear value: "+str.equals("Valentine Bear"));
            stuffedFrogUnitPrice = stuffedFrogItemUnitPrice.getText();
            fluffyBunnyUnitPrice = fluffyBunnyItemUnitPrice.getText();
            valentineBearUnitPrice = valentineBearItemUnitPrice.getText();
            stuffedFrogUnitPrice = stuffedFrogUnitPrice.replace("$","");
            fluffyBunnyUnitPrice = fluffyBunnyUnitPrice.replace("$","");
            valentineBearUnitPrice = valentineBearUnitPrice.replace("$","");
            Number sfUnitPrice = TestUtil.parseNumber(stuffedFrogUnitPrice);
            Number fbUnitPrice = TestUtil.parseNumber(fluffyBunnyUnitPrice);
            Number vbUnitPrice = TestUtil.parseNumber(valentineBearUnitPrice);
            int sfInput = Integer.parseInt(stuffFrogVal);
            Number totalSFPrice = (Double)sfUnitPrice * sfInput;
            Number totalFBPrice = (Double)fbUnitPrice * Integer.parseInt(fluffBunnyVal);
            Number totalVBPrice = (Double)vbUnitPrice * Integer.parseInt(valentineBearVal);
            totalPriceStuffedFrog = "$"+totalSFPrice;
            totalPriceFluffyBunny = "$"+totalFBPrice;
            totalPriceValentineBear = "$"+totalVBPrice;
            Number totalPrice = (Double)totalSFPrice+ (Double)totalFBPrice+ (Double)totalVBPrice;
            totalCost = "$"+totalPrice;
        }
    }

    public String getStuffedFrogTotal(){
        if(stuffedFrogItemTotal.isDisplayed()){
            return stuffedFrogItemTotal.getText();
        } else{
            Log.error("##### Stuffed Frog Total element not displayed ######");
            return null;
        }
    }

    public String getFluffyBunnyTotal(){
        if(fluffyBunnyItemTotal.isDisplayed()){
            return fluffyBunnyItemTotal.getText();
        } else{
            Log.error("##### Fluffy Bunny Total element not displayed ######");
            return null;
        }
    }

    public String getCartTotal(){
        if(cartTotal.isDisplayed()){
            return cartTotal.getText();
        } else{
            Log.error("##### Cart Total element not displayed ######");
            return null;
        }
    }

    public String getValentineBearTotal(){
        if(valentineBearItemTotal.isDisplayed()){
            return valentineBearItemTotal.getText();
        } else{
            Log.error("##### Valentine Bear Total element not displayed ######");
            return null;
        }
    }

}
