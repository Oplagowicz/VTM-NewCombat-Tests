package DemoQA.ElementsTests;

import DemoQA.Helpers.ListInspector;
import DemoQA.data.Pages.CheckboxPage;
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
import java.util.List;

import static DemoQA.data.MainData.mainURL;


public class CheckboxTests {

    WebDriverWait wait;
    CheckboxPage checkboxPage = new CheckboxPage();
    ListInspector listInspector = new ListInspector();
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
    public void checkBoxTest() {
        WebElement checkBoxElement = driver.findElement(By.id("item-1"));
        checkBoxElement.click();

        wait.until(ExpectedConditions.urlContains("/checkbox"));
        String checkBoxURL = driver.getCurrentUrl();
        Assert.assertNotNull(checkBoxURL);
        Assert.assertTrue(checkBoxURL.contains("/checkbox"));
        listInspector.setDriver(driver);
    }

    @Test(priority = 1, dependsOnMethods = {"checkBoxTest"})
    public void checkBox_Expand_Collapse_AllBtnTest() {
        WebElement expandAllBtn = driver.findElement(checkboxPage.expandAllBtnLocator);
        WebElement collapseAllBtn = driver.findElement(checkboxPage.collapseAllBtnLocator);
        Assert.assertTrue(expandAllBtn.isEnabled());
        Assert.assertTrue(collapseAllBtn.isEnabled());

        // Default state - all collapsed
        List<WebElement> collapsedItems = driver.findElements(checkboxPage.collapsedTreeLocator);
        int collapsedItemsCount = collapsedItems.size();
        Assert.assertEquals(collapsedItemsCount, 1);
        // Click Expand All
        expandAllBtn.click();
        //After click - find all expanded
        List<WebElement> expandedItems = driver.findElements(checkboxPage.expandedTreeLocator);
        int expandedItemsCount = expandedItems.size();
        // After click - find no collapsed
        collapsedItems = driver.findElements(checkboxPage.collapsedTreeLocator);
        collapsedItemsCount = collapsedItems.size();
        // Verify counts
        Assert.assertEquals(expandedItemsCount, 6);
        Assert.assertEquals(collapsedItemsCount, 0);

        // Check that there all tree-nodes are displayed and enabled

        listInspector.assertAllCheckboxesEnabled(checkboxPage.allCheckboxLocators);


        // Click Collapse All
        collapseAllBtn.click();
        // After click - all collapsed and return to default state, find all collapsed and check again
        collapsedItems = driver.findElements(checkboxPage.collapsedTreeLocator);
        collapsedItemsCount = collapsedItems.size();
        Assert.assertEquals(collapsedItemsCount, 1);
    }

    @Test(priority = 1, dependsOnMethods = {"checkBoxTest"})
    public void checkBox_Select_Home_CheckboxTest() {
        WebElement home_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxPage.home_Checkbox));
        Assert.assertTrue(home_Checkbox.isEnabled());
        home_Checkbox.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(
                checkboxPage.resultItemsLocator,
                checkboxPage.expectedResults.size()
        ));

        List<String> actualTexts = driver.findElements(checkboxPage.resultItemsLocator)
                .stream()
                .map(WebElement::getText) // get innerText from <span class="text-success">
                .toList();

        listInspector.assertAllResultsText(checkboxPage.expectedResults, actualTexts);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
