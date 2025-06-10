package tests;

import com.planittesting.pages.ContactPage;
import com.planittesting.pages.HomePage;
import com.planittesting.utilities.Log;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContactTest extends BaseTest {
    WebDriver driver;
    HomePage homePage;
    ContactPage contactPage;
    String forenameError = "Forename is required";
    String emailError = "Email is required";
    String messageError = "Message is required";

    public static String forenameVal;
    public static String surnameVal;
    public static String emailVal;
    public static String messageVal;

    @BeforeMethod
    public void navigateToBaseUrl(){
        Log.info("-----Test is starting -----");
        BaseTest.driver.get(BASE_URL);
        forenameVal = prop.getProperty("forename");
        surnameVal = prop.getProperty("surname");
        emailVal = prop.getProperty("email");
        messageVal = prop.getProperty("message");
    }

    @AfterMethod
    public void endTest() {
        Log.info("------Test is ending------");
    }

    @Test
    public void verifyContactErrors() throws IOException {
        try {
            homePage = new HomePage(BaseTest.driver);
            homePage.navigateToContactMenu();
            waitForPageLoad();
            contactPage = new ContactPage(BaseTest.driver);
            contactPage.clickSubmit();
            //Verify mandatory fields error
            String forenameErrorRead = contactPage.getForenameError();
            Log.info("Forename error read as: "+ forenameErrorRead);
            Assert.assertEquals(forenameErrorRead,forenameError);
            String emailErrorRead = contactPage.getEmailError();
            Log.info("Email error read as: "+ emailErrorRead);
            Assert.assertEquals(emailErrorRead,emailError);
            String messageErrorRead = contactPage.getMessageError();
            Log.info("Message error read as: "+ messageErrorRead);
            Assert.assertEquals(messageErrorRead,messageError);

            //Enter Mandatory Values
            contactPage.enterForename(forenameVal);
            contactPage.enterEmail(emailVal);
            contactPage.enterMessage(messageVal);
            //contactPage.clickSubmit();

            //Verify no errors shown
            Boolean errorBool = contactPage.isForenameErrorNotFound();
            //System.out.println("Forename error found in page or not value is: "+errorBool);
            if(errorBool)
                Log.info("---Forename error message not found as expected------");
            else
                Log.error("!!!!!!!!!!Forename error message still found!!!!!!!!!!!!");
            Assert.assertTrue(errorBool, "Forename Error not found");
            errorBool = contactPage.isEmailErrorFound();
            if(errorBool)
                Log.info("--- Email error message not found as expected------");
            else
                Log.error("!!!!!!!!!! Email error message still found!!!!!!!!!!!!");
            Assert.assertTrue(errorBool, "Email error not found");
            errorBool = contactPage.isMessageErrorFound();
            if(errorBool)
                Log.info("---Message error message not found as expected------");
            else
                Log.error("!!!!!!!!!!Message error message still found!!!!!!!!!!!!");
            Assert.assertTrue(errorBool, "Message error not found");

        }catch(InterruptedException e){
            Log.error("Interrupted Exception in verify Contact test: "+e.getMessage());
        }
    }

    @Test(invocationCount = 5)
    public void contactSubmitWithMandatoryFields() throws IOException {
        try {
            homePage = new HomePage(BaseTest.driver);
            homePage.navigateToContactMenu();
            waitForPageLoad();
            contactPage = new ContactPage(BaseTest.driver);

            //Enter Mandatory Values
            contactPage.enterForename(forenameVal);
            contactPage.enterEmail(emailVal);
            contactPage.enterMessage(messageVal);
            contactPage.clickSubmit();
            String msg = contactPage.getContactSubmitMessage();
            Log.info("Submitted contact message read as : "+msg);
            if(msg.contains("we appreciate your feedback")){
                Log.info("Valid message");
            }else{
                Log.error("Message is not as expected");
            }
            Assert.assertTrue(msg.contains("we appreciate your feedback"));


        }catch(InterruptedException e){
            Log.info("Interrupted Exception in verify Contact test: "+e.getMessage());
        }
    }
}
