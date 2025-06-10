package tests;

import com.planittesting.pages.HomePage;
import com.planittesting.utilities.Log;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomeTest extends BaseTest {
    WebDriver driver;
    HomePage homePage;
    String homePageTitle = "Jupiter Toys";

    @BeforeMethod
    public void navigateToBaseUrl(){
        Log.info("-----Test is starting -----");
        BaseTest.driver.get(BASE_URL);
    }

    @AfterMethod
    public void endTest() {
        Log.info("------Test is ending------");
    }

    @Test
    public void verifyHomePageDetails() throws IOException{
        homePage = new HomePage(BaseTest.driver);
        String homeHeader = homePage.getHomePageHeader();
        Log.info("----- Home header read as: -----"+homeHeader);
        Assert.assertEquals(homeHeader,homePageTitle);
    }

}
