package DemoQA.ElementsTests;

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

    WebDriver driver;
    WebDriverWait wait;
    String mainURL = "https://demoqa.com";

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
    }

    @Test(priority = 2, dependsOnMethods = {"checkBoxTest"})
    public void checkBox_Expand_Collapse_AllBtnTest() {
        WebElement expandAllBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rct-option-expand-all")));
        WebElement collapseAllBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rct-option-collapse-all")));
        Assert.assertTrue(expandAllBtn.isDisplayed());
        Assert.assertTrue(expandAllBtn.isEnabled());
        Assert.assertTrue(collapseAllBtn.isDisplayed());
        Assert.assertTrue(collapseAllBtn.isEnabled());

        // Default state - all collapsed
        List<WebElement> defaultItems = driver.findElements(By.className("rct-node-collapsed"));
        int defaultItemsCount = defaultItems.size();
        Assert.assertEquals(defaultItemsCount, 1);
        // Click Expand All
        expandAllBtn.click();
        //After click - all expanded
        List<WebElement> expandedItems = driver.findElements(By.className("rct-node-expanded"));
        int expandedItemsCount = expandedItems.size();
        Assert.assertEquals(expandedItemsCount, 6);

        // Check that there all tree-nodes are displayed and enabled

        WebElement home_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-home'] span.rct-checkbox")));
        WebElement desktop_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-desktop'] span.rct-checkbox")));
        WebElement notes_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-notes'] span.rct-checkbox")));
        WebElement commands_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-commands'] span.rct-checkbox")));
        WebElement documents_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-documents'] span.rct-checkbox")));
        WebElement workspace_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-workspace'] span.rct-checkbox")));
        WebElement react_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-react'] span.rct-checkbox")));
        WebElement angular_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-angular'] span.rct-checkbox")));
        WebElement veu_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-veu'] span.rct-checkbox")));
        WebElement office_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-office'] span.rct-checkbox")));
        WebElement public_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-public'] span.rct-checkbox")));
        WebElement private_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-private'] span.rct-checkbox")));
        WebElement classified_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-classified'] span.rct-checkbox")));
        WebElement general_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-general'] span.rct-checkbox")));
        WebElement downloads_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-downloads'] span.rct-checkbox")));
        WebElement wordFile_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-wordFile'] span.rct-checkbox")));
        WebElement excelFile_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-excelFile'] span.rct-checkbox")));
        Assert.assertTrue(home_Checkbox.isDisplayed());
        Assert.assertTrue(home_Checkbox.isEnabled());
        Assert.assertTrue(desktop_Checkbox.isDisplayed());
        Assert.assertTrue(desktop_Checkbox.isEnabled());
        Assert.assertTrue(notes_Checkbox.isDisplayed());
        Assert.assertTrue(notes_Checkbox.isEnabled());
        Assert.assertTrue(commands_Checkbox.isDisplayed());
        Assert.assertTrue(commands_Checkbox.isEnabled());
        Assert.assertTrue(documents_Checkbox.isDisplayed());
        Assert.assertTrue(documents_Checkbox.isEnabled());
        Assert.assertTrue(workspace_Checkbox.isDisplayed());
        Assert.assertTrue(workspace_Checkbox.isEnabled());
        Assert.assertTrue(react_Checkbox.isDisplayed());
        Assert.assertTrue(react_Checkbox.isEnabled());
        Assert.assertTrue(angular_Checkbox.isDisplayed());
        Assert.assertTrue(angular_Checkbox.isEnabled());
        Assert.assertTrue(veu_Checkbox.isDisplayed());
        Assert.assertTrue(veu_Checkbox.isEnabled());
        Assert.assertTrue(office_Checkbox.isDisplayed());
        Assert.assertTrue(office_Checkbox.isEnabled());
        Assert.assertTrue(public_Checkbox.isDisplayed());
        Assert.assertTrue(public_Checkbox.isEnabled());
        Assert.assertTrue(private_Checkbox.isDisplayed());
        Assert.assertTrue(private_Checkbox.isEnabled());
        Assert.assertTrue(classified_Checkbox.isDisplayed());
        Assert.assertTrue(classified_Checkbox.isEnabled());
        Assert.assertTrue(general_Checkbox.isDisplayed());
        Assert.assertTrue(general_Checkbox.isEnabled());
        Assert.assertTrue(downloads_Checkbox.isDisplayed());
        Assert.assertTrue(downloads_Checkbox.isEnabled());
        Assert.assertTrue(wordFile_Checkbox.isDisplayed());
        Assert.assertTrue(wordFile_Checkbox.isEnabled());
        Assert.assertTrue(excelFile_Checkbox.isDisplayed());
        Assert.assertTrue(excelFile_Checkbox.isEnabled());
        // Click Collapse All
        collapseAllBtn.click();
        // After click - all collapsed and return to default state
        List<WebElement> collapsedItems = driver.findElements(By.className("rct-node-collapsed"));
        int collapsedItemsCount = collapsedItems.size();
        Assert.assertEquals(collapsedItemsCount, 1);
    }

    @Test(priority = 2, dependsOnMethods = {"checkBoxTest"})
    public void checkBox_Select_Home_CheckboxTest() {
        WebElement home_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-home'] span.rct-checkbox")));
        Assert.assertTrue(home_Checkbox.isDisplayed());
        Assert.assertTrue(home_Checkbox.isEnabled());
        home_Checkbox.click();

        WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        String resultText = resultElement.getText();
        Assert.assertTrue(resultText.contains("You have selected :"));
        Assert.assertTrue(resultText.contains("home"));
        Assert.assertTrue(resultText.contains("desktop"));
        Assert.assertTrue(resultText.contains("notes"));
        Assert.assertTrue(resultText.contains("commands"));
        Assert.assertTrue(resultText.contains("documents"));
        Assert.assertTrue(resultText.contains("workspace"));
        Assert.assertTrue(resultText.contains("react"));
        Assert.assertTrue(resultText.contains("angular"));
        Assert.assertTrue(resultText.contains("veu"));
        Assert.assertTrue(resultText.contains("office"));
        Assert.assertTrue(resultText.contains("public"));
        Assert.assertTrue(resultText.contains("private"));
        Assert.assertTrue(resultText.contains("classified"));
        Assert.assertTrue(resultText.contains("general"));
        Assert.assertTrue(resultText.contains("downloads"));
        Assert.assertTrue(resultText.contains("wordFile"));
        Assert.assertTrue(resultText.contains("excelFile"));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
