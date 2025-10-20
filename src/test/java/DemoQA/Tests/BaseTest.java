package DemoQA.Tests;

import DemoQA.Factory.Driver;
import DemoQA.Helpers.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class BaseTest {
    private final String APP_URL = "https://demoqa.com/";

    protected WebDriverWait wait;
    protected ElementHelper inspector;
    protected WebDriver driver;
    protected Actions actions;

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
        actions = new Actions(initDriver);

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

    private boolean cordsChanged(List<Number> a, List<Number> b) {
        if (a == null || b == null || a.size() < 2 || b.size() < 2) return true;
        double dx = Math.abs(a.get(0).doubleValue() - b.get(0).doubleValue());
        double dy = Math.abs(a.get(1).doubleValue() - b.get(1).doubleValue());
        return dx > 5.0 || dy > 5.0;
    }

    @SuppressWarnings("unchecked")
    private void ensureStableScroll(WebElement el) {
        WebDriver driver = getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        final int maxAttempts = 3;
        final long pauseMs = 300;

        for (int attempt = 0; attempt < maxAttempts; attempt++) {

            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", el);

            List<Number> before = (List<Number>) js.executeScript(
                    "var r = arguments[0].getBoundingClientRect(); return [r.x, r.y];", el);

            try { Thread.sleep(pauseMs); } catch (InterruptedException ignored) {}

            List<Number> after = (List<Number>) js.executeScript(
                    "var r = arguments[0].getBoundingClientRect(); return [r.x, r.y];", el);

            if (!cordsChanged(before, after)) {
                return;
            }
        }
    }

    protected void clickReliableBy(By locator) {
        WebDriver driver = getDriver();
        WebDriverWait w = wait;

        WebElement el = w.until(ExpectedConditions.presenceOfElementLocated(locator));
        ensureStableScroll(el);

        try {
            w.until(ExpectedConditions.elementToBeClickable(locator));
            el.click();
            return;
        } catch (Exception ignored) {}

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            return;
        } catch (Exception ignored) {}

        try {
            actions.moveToElement(el).click().perform();
        } catch (Exception ignored) {}

        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> Objects.equals(((JavascriptExecutor) d).executeScript("return document.readyState"), "complete"));
        } catch (Exception ignored) {}

    }
}
