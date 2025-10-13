package DemoQA.Tests;

import DemoQA.Factory.Driver;
import DemoQA.Helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    private final String APP_URL = "https://demoqa.com/";

    protected WebDriverWait wait;
    protected ElementHelper inspector;
    protected WebDriver driver;

    public WebDriver getDriver() {
        return this.driver;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Parameters({"browser", "mode"})
    @BeforeClass(alwaysRun = true)
    public void prepSuit(@Optional("chrome") String browser,
                         @Optional("desktop") String mode,
                         ITestContext ctx) {
        System.out.println("Setting up the test");
        WebDriver initDriver = Driver.createDriver(browser, mode);

        setDriver(initDriver);

        wait = new WebDriverWait(initDriver, Duration.ofSeconds(10));
        inspector = new ElementHelper(initDriver);

        ctx.setAttribute("driver", initDriver);
        ctx.setAttribute(getClass().getName() + ".driver", initDriver);
        ctx.setAttribute("browser", browser);
        ctx.setAttribute("mode", mode);
    }

    @BeforeMethod(alwaysRun = true)
    public void openMainPage() {
        this.open();
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        if (getDriver() != null) {
            System.out.println("Closing the browser");
            getDriver().quit();
            setDriver(null);
        }
    }

    public void open(String pageURL) {
        if (getDriver() == null) {
            throw new IllegalStateException("WebDriver is not initialized. Cannot open page.");
        }
        getDriver().navigate().to(APP_URL + pageURL);
    }

    public void open() {
        if (getDriver() == null) {
            throw new IllegalStateException("WebDriver is not initialized. Cannot open page.");
        }
        getDriver().navigate().to(APP_URL);
    }
}
