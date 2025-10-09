package DemoQA.Tests.ModulsAndAlerts;


import DemoQA.Helpers.ScreenshotOnFailureListener;
import DemoQA.Tests.BaseTest;
import DemoQA.data.Pages.AlertsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotOnFailureListener.class)
public class AlertsTests extends BaseTest {
    AlertsPage alertsPage = new AlertsPage();

    @BeforeMethod
    public void prep() {
        open("alerts");
    }

    @Test
    public void testAlertButton() {
        WebElement alertBtn = getDriver().findElement(alertsPage.alertButton);
        alertBtn.click();
        Assert.assertEquals(getDriver().switchTo().alert().getText(), "You clicked a button");
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testTimerAlertButton() throws InterruptedException {
        WebElement timerAlertBtn = getDriver().findElement(alertsPage.timerAlertButton);
        timerAlertBtn.click();
        Thread.sleep(6000);
        Assert.assertEquals(getDriver().switchTo().alert().getText(), "This alert appeared after 5 seconds");
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testConfirmButton() {
        WebElement confirmBtn = getDriver().findElement(alertsPage.confirmButton);
        confirmBtn.click();
        getDriver().switchTo().alert().dismiss();
        WebElement confirmResult = getDriver().findElement(alertsPage.confirmResult);
        Assert.assertEquals(confirmResult.getText(), "You selected Cancel");
        confirmBtn.click();
        getDriver().switchTo().alert().accept();
        Assert.assertEquals(confirmResult.getText(), "You selected Ok");
    }

    @Test
    public void testPromptButton() {
        WebElement promptBtn = getDriver().findElement(alertsPage.promptButton);
        promptBtn.click();
        getDriver().switchTo().alert().sendKeys("Test");
        getDriver().switchTo().alert().accept();
        WebElement promptResult = getDriver().findElement(alertsPage.promptResult);
        Assert.assertEquals(promptResult.getText(), "You entered Test");
    }
}
