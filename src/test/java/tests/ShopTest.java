package tests;

import com.planittesting.pages.CartPage;
import com.planittesting.pages.HomePage;
import com.planittesting.pages.ShopPage;
import com.planittesting.utilities.Log;
import common.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class ShopTest extends BaseTest {
    //WebDriver driver;
    HomePage homePage;
    ShopPage shopPage;
    CartPage cartPage;

    public static String stuffedFrogVal;
    public static String fluffyBunnyVal;
    public static String valentineBearVal;


    @BeforeMethod
    public void navigateToBaseUrl(){
        Log.info("-----Test is starting -----");
        BaseTest.driver.get(BASE_URL);
        stuffedFrogVal = prop.getProperty("stuffedFrog");
        fluffyBunnyVal = prop.getProperty("fluffyBunny");
        valentineBearVal = prop.getProperty("valentineBear");
    }

    @AfterMethod
    public void endTest() {
        Log.info("------Test is ending------");
    }

    @Test
    public void buyItemsAndVerifyTotal(){
        try {
            //navigate to Shop page
            homePage = new HomePage(BaseTest.driver);
            homePage.navigateToShopMenu();
            waitForPageLoad();
            shopPage = new ShopPage(BaseTest.driver);
            //buy Stuffed Frog, Fluffy Bunny, Valentine Bear
            shopPage.buyStuffedFrog();
            shopPage.buyFluffyBunny();
            shopPage.buyValentineBear();
            //navigate to Cart menu
            homePage.navigateToCartMenu();
            cartPage = new CartPage(BaseTest.driver);

            //input in cart for Stuffed Frog, Fluffy Bunny, Valentine Bear
            cartPage.inputQuantityOfStuffedFrog(stuffedFrogVal,fluffyBunnyVal,valentineBearVal);
            cartPage.inputQuantityOfFluffyBunny(stuffedFrogVal,fluffyBunnyVal,valentineBearVal);
            cartPage.inputQuantityOfValentineBear(stuffedFrogVal,fluffyBunnyVal,valentineBearVal);

            //Get Item Totals
            String sfTotal = cartPage.getStuffedFrogTotal();
            Log.info("----StuffedFrogTotal is:------"+sfTotal);
            String fbTotal = cartPage.getFluffyBunnyTotal();
            Log.info("----FluffyBunnyTotal is:------"+fbTotal);
            String vbTotal = cartPage.getValentineBearTotal();
            Log.info("----ValentineBearTotal is:------"+vbTotal);

            String cartTotal = cartPage.getCartTotal();
            Log.info("----CartTotal is:------"+cartTotal);

            //Verify Read Item totals to calculated totals
            cartPage.calculateTotalOfItemsBought(stuffedFrogVal,fluffyBunnyVal,valentineBearVal);
            Log.info("String comparison StuffedFrog total value: "+sfTotal.equals(cartPage.totalPriceStuffedFrog));
            Log.info("String comparison FluffyBunny total value: "+sfTotal.equals(cartPage.totalPriceFluffyBunny));
            Log.info("String comparison Valentine Bear total value: "+sfTotal.equals(cartPage.totalPriceValentineBear));
            Log.info("String comparison TotalCart total value: "+sfTotal.equals(cartPage.totalCost));

            //wait(10000);
            Log.info("Test completed");
        }catch(InterruptedException e){
            Log.error("Interrupted Exception in verify Contact test: "+e.getMessage());
        }catch(Exception e){
            Log.error("IO Exception in verify Contact test:"+e.getMessage());
        }
    }
}
