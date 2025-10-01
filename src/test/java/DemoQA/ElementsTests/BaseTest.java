package DemoQA.ElementsTests;

import DemoQA.Helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    public WebDriverWait wait;
    public WebDriver driver;
    public ElementHelper inspector;

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    @BeforeSuite
    public void prepSuit() {
        System.out.println("Setting up the test");
        WebDriver initDriver = getDriver();
        wait = new WebDriverWait(initDriver, Duration.ofSeconds(10));
        inspector = new ElementHelper(initDriver);
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("Tearing down the test");
        if (getDriver() != null) {
            System.out.println("Closing the browser");
            getDriver().quit();
        }
    }
}
