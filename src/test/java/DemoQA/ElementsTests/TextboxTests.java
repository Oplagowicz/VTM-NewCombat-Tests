package DemoQA.ElementsTests;

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
import org.testng.annotations.Test;

import java.time.Duration;

import static DemoQA.data.MainData.mainURL;
import static DemoQA.data.RegistrationData.*;

public class TextboxTests {
    WebDriverWait wait;
    TextboxPage textboxPage = new TextboxPage();
    private WebDriver driver;

    @BeforeTest
    public void prep() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

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

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.nameInputLocator));
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.emailInputLocator));
        WebElement currentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.currentAddressLocator));
        WebElement permanentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.permanentAddressLocator));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(textboxPage.submitBtnLocator));

        Assert.assertTrue(nameInput.isEnabled());
        Assert.assertTrue(emailInput.isEnabled());
        Assert.assertTrue(currentAddressInput.isEnabled());
        Assert.assertTrue(permanentAddressInput.isEnabled());
        Assert.assertTrue(submitButton.isEnabled());

    }

    @Test(priority = 1, dependsOnMethods = {"textBoxTest"})
    public void textBoxUserFormTest() {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.nameInputLocator));
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.emailInputLocator));
        WebElement currentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.currentAddressLocator));
        WebElement permanentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.permanentAddressLocator));
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.submitBtnLocator));

        nameInput.sendKeys(nameTest);
        emailInput.sendKeys(emailTest);
        currentAddressInput.sendKeys(addressTest);
        permanentAddressInput.sendKeys(addressTest);
        submitButton.click();

        WebElement outputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.outputLocator));
        String outputText = outputElement.getText();

        Assert.assertTrue(outputText.contains(nameTest));
        Assert.assertTrue(outputText.contains(emailTest));
        Assert.assertTrue(outputText.contains(addressTest));
        Assert.assertTrue(outputText.contains(addressTest));

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
