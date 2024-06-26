package testcases;

import java.io.IOException;

import com.product.analytica.browsers.Browsers;
import com.product.analytica.enums.BrowserTypes;
import com.product.analytica.pages.AmazonProductPage;
import com.product.analytica.utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.product.analytica.utils.ScreenshotUtils;

/**
 * Test case class for analyzing Amazon products.
 *
 * @author Bhavin.Thumar
 */
public class AmazonProductTest {

    private WebDriver driver;

    /**
     * Sets up the test environment before executing the test cases.
     */
    @BeforeTest
    public void setUp() {
        Browsers browser = new Browsers();
        driver = browser.startBrowser(BrowserTypes.CHROME, PropertiesUtils.getAmazon_URL(), PropertiesUtils.getHeadlessMode());
    }

    /**
     * Test method for analyzing product reviews on Amazon.
     *
     * @throws InterruptedException If the thread is interrupted while waiting.
     * @throws IOException          If an I/O error occurs.
     */
    @Test
    public void productAnalysis() throws InterruptedException {
        AmazonProductPage obj = PageFactory.initElements(driver, AmazonProductPage.class);
        obj.productReviewsAnalysis();
    }

    /**
     * If the test method fails, captures a screenshot of the browser window.
     *
     * @param result The result of the executed test method.
     * @throws IOException If an I/O error occurs while capturing the screenshot.
     */
    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.captureScreenshot(driver, "Screenshot" + result.getName());
            System.out.println("Screenshot is generated " + '\n');
        }
    }
}
