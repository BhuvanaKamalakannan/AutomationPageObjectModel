package common;

import com.planittesting.utilities.ConfigLoader;
import com.planittesting.utilities.WebEventListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BaseTest {
    public static WebDriver driver;
    public static Properties prop;
    public static WebEventListener eventListener;

    public static String BROWSER_TYPE;
    public static String IMP_WAIT;
    public static String EXP_WAIT;
    public static String PAGE_LOAD_WAIT;
    public static String BASE_URL;


    private static WebDriverWait wait;


    public BaseTest(){
        prop = new Properties();
        prop = ConfigLoader.propertyLoader(getWorkingDir()+ "/src/main/java/com/planittesting"
                    + "/config/config.properties");
        BROWSER_TYPE = prop.getProperty("browserType");
        IMP_WAIT = prop.getProperty("implicitWait");
        EXP_WAIT = prop.getProperty("explicitWait");
        PAGE_LOAD_WAIT = prop.getProperty("pageLoadWait");
        BASE_URL = prop.getProperty("baseUrl");

    }

    public  String getBrowserType() {
        return BROWSER_TYPE;
    }

   // public  void setBrowserType(String browserType) {
   //     BaseTest.BROWSER_TYPE = browserType;
    //}

    public String getWorkingDir() {
        return (System.getProperty("user.dir"));
    }

    public WebDriver setDriver(String browserType) {
        //WebDriver driver;
        Duration implicitWait = Duration.ofSeconds(Long.parseLong(IMP_WAIT));
        Duration pageLoadWait = Duration.ofSeconds(Long.parseLong(PAGE_LOAD_WAIT));
        System.out.println("Browser type is:"+browserType);
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("browser : " + browserType
                        + " is invalid, Launching chrome as browser of choice..");
                driver = initChromeDriver();
        };
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(pageLoadWait);
        driver.manage().timeouts().implicitlyWait(implicitWait);
        driver.get(prop.getProperty("baseUrl"));
        System.out.println("Implicit wait of driver set to: "+ implicitWait);
        return driver;
    }

    public static void initializeExpWait() {
        wait = new WebDriverWait(driver,Duration.ofSeconds(Long.parseLong(EXP_WAIT)));
        //Log.info("Explicit wait set to : "+ ReadBaseExcel.EXP_WAIT_SHORT);
    }



    //****************************************************************************************************************************************************
    public static void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoad = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            //Waits till explicit short wait
            wait.until(pageLoad);
        } catch (Exception e){
            System.out.println("Error in waitForPageLoad"+e.getMessage());
        }catch (Throwable error) {
            //Log.error("!!!!!!!!!!!!! (DriverUtil warning) -  Timedout waiting for Page Load Request to complete -------- "+ error.getMessage());
        }
    }

    //****************************************************************************************************************************************************
    public static void scrollDownHeight(int val){
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + val+ ")", ""); //y value '400' can be altered

        } catch (Exception e){
            //Log.warn("!!!!!!!!!!!!! (DriverUtil warning) -  Exception while scrolling page to bottom ------------ "+e.getMessage() );
        }
    }

    //****************************************************************************************************************************************************
    public static void scrollToElement(WebElement ele) {
        try {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("arguments[0].scrollIntoView(true);", ele);
        } catch (Exception e){
           // Log.warn("!!!!!!!!!!!!! (DriverUtil warning) - Exception while scrolling element ------------ "+e.getMessage() );
        }
    }

    //****************************************************************************************************************************************************
    public static Boolean waitElementVisible(WebElement we) {
        try {
            wait.until(ExpectedConditions.visibilityOf(we)); // visibilityOf checks only whether element is displayed and not checks enabled
            if(we != null && we.isEnabled())
                return true;
            else
                return false;
        } catch (ElementNotInteractableException e){
            scrollDownHeight(200);
            //Log.warn("!!!!!!!!!!!!! (DriverUtil warning) - Scrolled bit down, Waiting for Element is not 'VISIBLE' and exception occured ; ----------  " + e.getMessage());
            return false;
        }
        catch (Exception e){
           // Log.warn("!!!!!!!!!!!!! (DriverUtil warning) - Waiting for Element is not visible and exception occured ; ----------  " + e.getMessage());
            return false;
        }
    }

    @BeforeSuite
    public void initialization() {
        System.out.println("browser read as: "+BROWSER_TYPE);
        driver = setDriver(BROWSER_TYPE);
        initializeExpWait();
    }

    @AfterSuite
    public void destroyDriver() {
        driver.quit();
    }

    private WebDriver initChromeDriver() {

        String driverFile = getWorkingDir()+"/driver/chrome/chromedriver";
        System.out.println("Launching google chrome with new profile......");
        System.setProperty("webdriver.chrome.driver",driverFile);
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();

        options.addArguments( "--disable-popup-blocking", "--ignore-certificate-errors");
        //-----------added extra  ------------
        options.addArguments("--force-device-scale-factor=1");
        options.addArguments("--disable-gpu");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("enable-features=NetworkServiceInProcess") ; //or option.addArguments("disable-features=NetworkService")
        //driver.navigate().to(appURL);
        return new ChromeDriver(options);
    }

    private static WebDriver initEdgeDriver() {
        String driverFile = "driver/edge/MicrosoftWebDriver.exe";
        System.out.println("Launching Microsoft Edge browser......");
        System.setProperty("webdriver.edge.driver",driverFile);
        System.setProperty("webdriver.edge.args","--disable-logging");
        System.setProperty("webdriver.edge.silentOutput", "true");
        DesiredCapabilities dc = new DesiredCapabilities();

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.merge(dc);
        return new EdgeDriver();
    }
}
