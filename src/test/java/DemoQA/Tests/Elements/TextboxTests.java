package DemoQA.Tests.Elements;

import DemoQA.Helpers.ScreenshotOnFailureListener;
import DemoQA.Tests.BaseTest;
import DemoQA.data.Pages.TextboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static DemoQA.data.RegistrationData.*;

@Listeners(ScreenshotOnFailureListener.class)
public class TextboxTests extends BaseTest {

    TextboxPage textboxPage = new TextboxPage();

    @BeforeMethod
    public void prep() {
        clickReliableBy(By.xpath("//*[text()='Elements']"));
        textboxPage.TextBoxPageDrive(getDriver(), wait);
        wait.until(ExpectedConditions.urlContains("/elements"));

        String currentURL = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentURL);
        Assert.assertTrue(currentURL.contains("/elements"));

        clickReliableBy(By.id("item-0"));
    }

    private void textboxPageCheck() {
        String textBoxURL = getDriver().getCurrentUrl();
        Assert.assertNotNull(textBoxURL);
        Assert.assertTrue(textBoxURL.contains("/text-box"));
    }

    @Test
    public void textBoxTest() {
        textboxPageCheck();

        Assert.assertTrue(textboxPage.nameInput().isEnabled());
        Assert.assertTrue(textboxPage.emailInput().isEnabled());
        Assert.assertTrue(textboxPage.currentAddressInput().isEnabled());
        Assert.assertTrue(textboxPage.permanentAddressInput().isEnabled());
        Assert.assertTrue(textboxPage.submitButton().isEnabled());
    }

    @Test(priority = 1, dependsOnMethods = {"textBoxTest"})
    public void textBoxUserFormTest() {
        textboxPageCheck();

        textboxPage.nameInput().sendKeys(nameTest);          // re-find each call
        textboxPage.emailInput().sendKeys(emailTest);
        textboxPage.currentAddressInput().sendKeys(addressTest);
        textboxPage.permanentAddressInput().sendKeys(addressTest);
        clickReliableBy(textboxPage.submitBtnLocator);

        WebElement outputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.outputLocator));
        String outputText = outputElement.getText();

        Assert.assertTrue(outputText.contains(nameTest));
        Assert.assertTrue(outputText.contains(emailTest));
        Assert.assertTrue(outputText.contains(addressTest));
        Assert.assertTrue(outputText.contains(addressTest));

    }

    @Test(priority = 2, dependsOnMethods = {"textBoxUserFormTest"})
    public void textBoxNoInputAfterSubmitTest() {
        textboxPageCheck();

        textboxPage.nameInput().clear();          // re-find each call
        textboxPage.emailInput().clear();
        textboxPage.currentAddressInput().clear();
        textboxPage.permanentAddressInput().clear();
        clickReliableBy(textboxPage.submitBtnLocator);

        WebElement outputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(textboxPage.outputLocator));
        String outputText = outputElement.getText();

        Assert.assertFalse(outputText.contains(nameTest));
        Assert.assertFalse(outputText.contains(emailTest));
        Assert.assertFalse(outputText.contains(addressTest));
        Assert.assertFalse(outputText.contains(addressTest));
    }

    @Test(priority = 3, dependsOnMethods = {"textBoxUserFormTest"})
    public void textBoxInvalidEmailTest() {
        textboxPageCheck();

        textboxPage.emailInput().clear();
        textboxPage.emailInput().sendKeys("invalid-email");
        clickReliableBy(textboxPage.submitBtnLocator);

        String emailClass = textboxPage.emailInput().getAttribute("class");
        Assert.assertNotNull(emailClass);
        Assert.assertTrue(emailClass.contains("field-error"), "Email field does not indicate error for invalid input.");
    }
}
