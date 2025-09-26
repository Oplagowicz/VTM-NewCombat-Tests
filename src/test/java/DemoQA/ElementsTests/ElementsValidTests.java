package DemoQA.ElementsTests;

import DemoQA.Helpers.ListInspector;
import DemoQA.Pages.CheckboxPage;
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


public class ElementsValidTests {

    WebDriverWait wait;
    String mainURL = "https://demoqa.com";
    CheckboxPage checkboxPage;
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

    @Test(priority = -1)
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

    }

    @Test(dependsOnMethods = {"textBoxTest"})
    public void textBoxUserFormTest() {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
        WebElement currentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentAddress")));
        WebElement permanentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("permanentAddress")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));

        Assert.assertTrue(nameInput.isEnabled());
        Assert.assertTrue(emailInput.isEnabled());
        Assert.assertTrue(currentAddressInput.isEnabled());
        Assert.assertTrue(permanentAddressInput.isEnabled());
        Assert.assertTrue(submitButton.isEnabled());

        // Input data
        String nameTest = "Arthur Demichev";
        String emailTest = "arthur.demichev@yahoo.net";
        String addressTest = "Cracow, Poland";

        nameInput.sendKeys(nameTest);
        emailInput.sendKeys(emailTest);
        currentAddressInput.sendKeys(addressTest);
        permanentAddressInput.sendKeys(addressTest);
        submitButton.click();

        WebElement outputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));
        String outputText = outputElement.getText();

        Assert.assertTrue(outputText.contains(nameTest));
        Assert.assertTrue(outputText.contains(emailTest));
        Assert.assertTrue(outputText.contains(addressTest));
        Assert.assertTrue(outputText.contains(addressTest));
    }

    @Test(priority = 1)
    public void checkBoxTest() {
        WebElement checkBoxElement = driver.findElement(By.id("item-1"));
        checkBoxElement.click();

        wait.until(ExpectedConditions.urlContains("/checkbox"));
        String checkBoxURL = driver.getCurrentUrl();
        Assert.assertNotNull(checkBoxURL);
        Assert.assertTrue(checkBoxURL.contains("/checkbox"));
        checkboxPage = new CheckboxPage();
        listInspector.setDriver(driver);
    }

    @Test(priority = 2, dependsOnMethods = {"checkBoxTest"})
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

        listInspector.assertAllCheckboxesEnabled(checkboxPage.checkBoxListLocators);


        // Click Collapse All
        collapseAllBtn.click();
        // After click - all collapsed and return to default state, find all collapsed and check again
        collapsedItems = driver.findElements(checkboxPage.collapsedTreeLocator);
        collapsedItemsCount = collapsedItems.size();
        Assert.assertEquals(collapsedItemsCount, 1);
    }

    @Test(priority = 2, dependsOnMethods = {"checkBoxTest"})
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
