package DemoQA.ElementsTests;

import DemoQA.Helpers.ElementDisplayInspector;
import DemoQA.data.Pages.ButtonsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class BtnTest {
    WebDriverWait wait;
    ButtonsPage buttonsPage;
    ElementDisplayInspector inspector = new ElementDisplayInspector();
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
//         Initialize WebDriver and WebDriverWait here
        driver = new ChromeDriver(); // Example for Chrome
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoqa.com/buttons");
        inspector.setDriver(driver);
        buttonsPage = new ButtonsPage();
    }

    @Test
    public void testDoubleClick() {
        WebElement doubleClickBtn = driver.findElement(buttonsPage.doubleClickBtnLocator);

        Actions action = new Actions(driver);

        Assert.assertTrue(inspector.elementIsDisappear(buttonsPage.doubleClickMessageLocator));

        action.doubleClick(doubleClickBtn).perform();
        Assert.assertTrue(inspector.elementExists(buttonsPage.doubleClickMessageLocator));
        WebElement message = driver.findElement(buttonsPage.doubleClickMessageLocator);

        assert message.getText().equals("You have done a double click");
    }

    @Test
    public void testRightClick() {
        WebElement rightClickBtn = driver.findElement(buttonsPage.rightClickBtnLocator);

        Actions action = new Actions(driver);

        Assert.assertTrue(inspector.elementIsDisappear(buttonsPage.rightClickMessageLocator));

        action.contextClick(rightClickBtn).perform();
        Assert.assertTrue(inspector.elementExists(buttonsPage.rightClickMessageLocator));
        WebElement message = driver.findElement(buttonsPage.rightClickMessageLocator);

        assert message.getText().equals("You have done a right click");
    }

    @Test
    public void testClickMe() {
        WebElement clickMeBtn = driver.findElement(buttonsPage.clickMeBtnLocator);
        Actions action = new Actions(driver);

        Assert.assertTrue(inspector.elementIsDisappear(buttonsPage.dynamicClickMessage));

        action.click(clickMeBtn).perform();
        Assert.assertTrue(inspector.elementExists(buttonsPage.dynamicClickMessage));
        WebElement message = driver.findElement(buttonsPage.dynamicClickMessage);

        assert message.getText().equals("You have done a dynamic click");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
