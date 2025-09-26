package DemoQA.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListInspector {
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private List<String> normalize(List<String> in) {
        return in.stream()
                .map(s -> s == null ? "" : s.trim().toLowerCase())
                .collect(Collectors.toList());
    }

    public void assertAllCheckboxesEnabled(List<By> locators) {
        // Map each locator to a visible element and keep disabled ones with readable info
        List<String> disabled = locators.stream()
                .map(by -> Map.entry(by, driver.findElement(by)))
                .filter(entry -> !entry.getValue().isEnabled())
                .map(entry -> entry.getKey().toString()) // Convert By to String for better readability
                .toList();

        // One clear assertion
        Assert.assertTrue(disabled.isEmpty(), "Disabled checkboxes found: " + disabled);
    }

    public void assertAllResultsText(List<String> expected, List<String> actual) {
        List<String> exp = normalize(expected);
        List<String> act = normalize(actual);

        // Size must match
        Assert.assertEquals(act.size(), exp.size(),
                "Results count mismatch. Actual=" + act.size() + ", Expected=" + exp.size() + "\nActual: " + act + "\nExpected: " + exp);

        // Order & content must match
        Assert.assertEquals(act, exp,
                "Results content/order mismatch.\nActual: " + act + "\nExpected: " + exp);
    }
}
