package DemoQA.ElementsTests;

import DemoQA.Helpers.ScreenshotOnFailureListener;
import DemoQA.data.Pages.TextboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import static DemoQA.data.MainData.mainURL;
import static DemoQA.data.RegistrationData.*;

@Listeners(ScreenshotOnFailureListener.class)
public class TextboxTests {
    WebDriverWait wait;
    TextboxPage textboxPage = new TextboxPage();
    private WebDriver driver;

    @BeforeTest
    public void prep() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        textboxPage.TextBoxPage(driver, wait);
        driver.get(mainURL);
        WebElement elements = driver.findElement(By.xpath("//*[text()='Elements']"));
        elements.click();
    }

    @Test
    public void textBoxTest() {
        wait.until(ExpectedConditions.urlContains("/elements"));

        String currentURL = driver.getCurrentUrl();
        Assert.assertNotNull(currentURL);
        Assert.assertTrue(currentURL.contains("/elements"));

        WebElement textBoxElement = driver.findElement(By.id("item-0"));
        textBoxElement.click();

        wait.until(ExpectedConditions.urlContains("/text-box"));
        String textBoxURL = driver.getCurrentUrl();
        Assert.assertTrue(textBoxURL.contains("/text-box"));

        Assert.assertTrue(textboxPage.nameInput().isEnabled());
        Assert.assertTrue(textboxPage.emailInput().isEnabled());
        Assert.assertTrue(textboxPage.currentAddressInput().isEnabled());
        Assert.assertTrue(textboxPage.permanentAddressInput().isEnabled());
        Assert.assertTrue(textboxPage.submitButton().isEnabled());
    }

    @Test(priority = 1, dependsOnMethods = {"textBoxTest"})
    public void textBoxUserFormTest() {
        textboxPage.nameInput().sendKeys(nameTest);          // re-find each call
        textboxPage.emailInput().sendKeys(emailTest);
        textboxPage.currentAddressInput().sendKeys(addressTest);
        textboxPage.permanentAddressInput().sendKeys(addressTest);
        textboxPage.submitButton().click();

        WebElement outputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.outputLocator));
        String outputText = outputElement.getText();

        Assert.assertTrue(outputText.contains(nameTest));
        Assert.assertTrue(outputText.contains(emailTest));
        Assert.assertTrue(outputText.contains(addressTest));
        Assert.assertTrue(outputText.contains(addressTest));

    }

    @Test(priority = 2, dependsOnMethods = {"textBoxUserFormTest"})
    public void textBoxNoInputAfterSubmitTest() {
        textboxPage.TextBoxPage(driver, wait);

        textboxPage.nameInput().clear();          // re-find each call
        textboxPage.emailInput().clear();
        textboxPage.currentAddressInput().clear();
        textboxPage.permanentAddressInput().clear();
        textboxPage.submitButton().click();

        WebElement outputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.outputLocator));
        String outputText = outputElement.getText();

        Assert.assertFalse(outputText.contains(nameTest));
        Assert.assertFalse(outputText.contains(emailTest));
        Assert.assertFalse(outputText.contains(addressTest));
        Assert.assertFalse(outputText.contains(addressTest));
    }

    @Test(priority = 3, dependsOnMethods = {"textBoxUserFormTest"})
    public void textBoxInvalidEmailTest() {
        textboxPage.emailInput().clear();
        textboxPage.emailInput().sendKeys("invalid-email");
        textboxPage.submitButton().click();

        String emailClass = textboxPage.emailInput().getAttribute("class");
        Assert.assertNotNull(emailClass);
        Assert.assertTrue(emailClass.contains("field-error"), "Email field does not indicate error for invalid input.");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
