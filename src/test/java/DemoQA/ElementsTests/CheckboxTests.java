package DemoQA.ElementsTests;

import DemoQA.Helpers.ListInspector;
import DemoQA.data.Pages.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class CheckboxTests extends BaseTest {

    CheckboxPage checkboxPage = new CheckboxPage();
    ListInspector listInspector = new ListInspector();

    @BeforeMethod
    public void prep() {
        open("checkbox");
        listInspector.setDriver(getDriver());
    }


    @Test
    public void checkBoxTest() {
        WebElement checkBoxElement = getDriver().findElement(By.id("item-1"));
        checkBoxElement.click();

        wait.until(ExpectedConditions.urlContains("/checkbox"));
        String checkBoxURL = getDriver().getCurrentUrl();
        Assert.assertNotNull(checkBoxURL);
        Assert.assertTrue(checkBoxURL.contains("/checkbox"));
    }

    @Test(priority = 1, dependsOnMethods = {"checkBoxTest"})
    public void checkBox_Expand_Collapse_AllBtnTest() {
        WebElement expandAllBtn = getDriver().findElement(checkboxPage.expandAllBtnLocator);
        WebElement collapseAllBtn = getDriver().findElement(checkboxPage.collapseAllBtnLocator);
        Assert.assertTrue(expandAllBtn.isEnabled());
        Assert.assertTrue(collapseAllBtn.isEnabled());

        // Default state - all collapsed
        List<WebElement> collapsedItems = getDriver().findElements(checkboxPage.collapsedTreeLocator);
        int collapsedItemsCount = collapsedItems.size();
        Assert.assertEquals(collapsedItemsCount, 1);
        // Click Expand All
        expandAllBtn.click();
        //After click - find all expanded
        List<WebElement> expandedItems = getDriver().findElements(checkboxPage.expandedTreeLocator);
        int expandedItemsCount = expandedItems.size();
        // After click - find no collapsed
        collapsedItems = getDriver().findElements(checkboxPage.collapsedTreeLocator);
        collapsedItemsCount = collapsedItems.size();
        // Verify counts
        Assert.assertEquals(expandedItemsCount, 6);
        Assert.assertEquals(collapsedItemsCount, 0);

        // Check that there all tree-nodes are displayed and enabled

        listInspector.assertAllCheckboxesEnabled(checkboxPage.allCheckboxLocators);


        // Click Collapse All
        collapseAllBtn.click();
        // After click - all collapsed and return to default state, find all collapsed and check again
        collapsedItems = getDriver().findElements(checkboxPage.collapsedTreeLocator);
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

        List<String> actualTexts = getDriver().findElements(checkboxPage.resultItemsLocator)
                .stream()
                .map(WebElement::getText) // get innerText from <span class="text-success">
                .toList();

        listInspector.assertAllResultsText(checkboxPage.expectedResults, actualTexts);
    }

}
