package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilites.helperClass;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class testBase {
    protected static WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    protected static ExtentReports reports;
    protected static ExtentTest test;


    @BeforeSuite
    public void setUpSuite(){
        reports =new ExtentReports();
        ExtentSparkReporter Spark = new ExtentSparkReporter("Reports/my-report.html");
        reports.attachReporter(Spark);
    }

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(@Optional("chrome") String browser) throws InterruptedException {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addExtensions(new File("src/main/resources/GIGHMMPIOBKLFEPJOCNAMGKKBIGLIDOM_6_6_0_0.crx"));
                driver = new ChromeDriver(options);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));
                driver.close();
                driver.switchTo().window(tabs.get(0));
                wait.until(ExpectedConditions.numberOfWindowsToBe(1));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid Browser In TestNG XML: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.automationexercise.com/");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture screenshot on failure
            String screenshotPath = helperClass.capture(driver);
            test.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }
        driver.quit();
    }

    @AfterSuite
    public void tearDown() throws IOException {
        reports.flush();
        Desktop.getDesktop().open(new File("Reports/my-report.html"));
    }
    public static void screenShot(String status, String stepDetail) throws InterruptedException, IOException {
        Thread.sleep(2000);
        String screenshotBase64 = helperClass.capture(driver);


        if (status.equalsIgnoreCase("pass")) {
            test.pass(stepDetail);
        } else if (status.equalsIgnoreCase("fail")) {
            test.fail(stepDetail, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } else if (status.equalsIgnoreCase("info")) {
            test.info(stepDetail);
        } else if (status.equalsIgnoreCase("Warning")) {
            test.warning(stepDetail);
        }
    }
}
