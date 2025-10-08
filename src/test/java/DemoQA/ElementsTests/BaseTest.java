package DemoQA.ElementsTests;

import DemoQA.Helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    private final String APP_URL = "https://demoqa.com/";
    public WebDriverWait wait;
    public ElementHelper inspector;
    protected WebDriver driver;

    public WebDriver getDriver() {
        if (this.driver == null) {
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
        }
        return this.driver;
    }

    @BeforeClass(alwaysRun = true)
    public void prepSuit() {
        System.out.println("Setting up the test");
        WebDriver initDriver = getDriver();
        wait = new WebDriverWait(initDriver, Duration.ofSeconds(10));
        inspector = new ElementHelper(initDriver);
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
        }
    }

    public void open(String pageURL) {
        getDriver().navigate().to(APP_URL + pageURL);
    }

    public void open() {
        getDriver().navigate().to(APP_URL);
    }
}
