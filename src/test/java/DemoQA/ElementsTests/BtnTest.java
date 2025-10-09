package DemoQA.ElementsTests;

import DemoQA.Helpers.ScreenshotOnFailureListener;
import DemoQA.data.Pages.ButtonsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotOnFailureListener.class)
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BtnTest extends BaseTest {
    ButtonsPage buttonsPage = new ButtonsPage();

    @BeforeMethod
    public void prep() {
        open("buttons");
    }


    @Test
    public void testDoubleClick() {
        WebElement doubleClickBtn = getDriver().findElement(buttonsPage.doubleClickBtnLocator);

        Actions action = new Actions(getDriver());

        Assert.assertTrue(inspector.elementIsDisappear(buttonsPage.doubleClickMessageLocator));

        action.doubleClick(doubleClickBtn).perform();
        Assert.assertTrue(inspector.elementExists(buttonsPage.doubleClickMessageLocator));
        WebElement message = getDriver().findElement(buttonsPage.doubleClickMessageLocator);

        assert message.getText().equals("You have done a double click");
    }

    @Test
    public void testRightClick() {
        WebElement rightClickBtn = getDriver().findElement(buttonsPage.rightClickBtnLocator);

        Actions action = new Actions(getDriver());

        Assert.assertTrue(inspector.elementIsDisappear(buttonsPage.rightClickMessageLocator));

        action.contextClick(rightClickBtn).perform();
        Assert.assertTrue(inspector.elementExists(buttonsPage.rightClickMessageLocator));
        WebElement message = getDriver().findElement(buttonsPage.rightClickMessageLocator);

        assert message.getText().equals("You have done a right click");
    }

    @Test
    public void testClickMe() {
        WebElement clickMeBtn = getDriver().findElement(buttonsPage.clickMeBtnLocator);
        Actions action = new Actions(getDriver());

        Assert.assertTrue(inspector.elementIsDisappear(buttonsPage.dynamicClickMessage));

        action.click(clickMeBtn).perform();
        Assert.assertTrue(inspector.elementExists(buttonsPage.dynamicClickMessage));
        WebElement message = getDriver().findElement(buttonsPage.dynamicClickMessage);

        assert message.getText().equals("You have done a dynamic click");
    }


}
