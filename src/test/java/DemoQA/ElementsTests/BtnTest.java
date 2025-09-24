package DemoQA.ElementsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class BtnTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp() {
//         Initialize WebDriver and WebDriverWait here
        driver = new ChromeDriver(); // Example for Chrome
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoqa.com/buttons");
    }

    @Test
    public void testDoubleClick() {
        By doubleClickBtnLocator = By.id("doubleClickBtn");
        By doubleClickMessageLocator = By.id("doubleClickMessage");

        WebElement doubleClickBtn = driver.findElement(doubleClickBtnLocator);

        Actions action = new Actions(driver);

        Assert.assertTrue(elementIsDisappear(doubleClickMessageLocator));

        action.doubleClick(doubleClickBtn).perform();
        Assert.assertTrue(elementExists(doubleClickMessageLocator));
        WebElement message = driver.findElement(doubleClickMessageLocator);

        assert message.getText().equals("You have done a double click");
    }

    @Test
    public void testRightClick() {
        By rightClickBtnLocator = By.id("rightClickBtn");
        By rightClickMessageLocator = By.id("rightClickMessage");

        WebElement rightClickBtn = driver.findElement(rightClickBtnLocator);

        Actions action = new Actions(driver);

        Assert.assertTrue(elementIsDisappear(rightClickMessageLocator));

        action.contextClick(rightClickBtn).perform();
        Assert.assertTrue(elementExists(rightClickMessageLocator));
        WebElement message = driver.findElement(rightClickMessageLocator);

        assert message.getText().equals("You have done a right click");
    }

    @Test
    public void testClickMe() {
        By clickMeBtnLocator = By.xpath("//button[text()='Click Me']");
        By dynamicClickMessage = By.id("dynamicClickMessage");

        WebElement clickMeBtn = driver.findElement(clickMeBtnLocator);
        Actions action = new Actions(driver);

        Assert.assertTrue(elementIsDisappear(dynamicClickMessage));

        action.click(clickMeBtn).perform();
        Assert.assertTrue(elementExists(dynamicClickMessage));
        WebElement message = driver.findElement(dynamicClickMessage);

        assert message.getText().equals("You have done a dynamic click");
    }

    public Boolean elementExists(By locator) {
        try {
            System.out.println("Element is exist: " + locator.toString());
            // Wait until the element is visible
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            // Perform actions on the element
            System.out.println("end");
            return button.isDisplayed();
        } catch (Exception e) {
            System.out.println("exeption");
            return false;
        }
    }

    public Boolean elementIsDisappear(By locator) {
        try {
            System.out.println("Element is disappear: " + locator.toString());
            WebElement button = driver.findElement(locator);
            // Perform actions on the element
            System.out.println("end");
            return button.isDisplayed();
        } catch (Exception e) {
            System.out.println("exeption");
            return true;
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
