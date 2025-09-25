package DemoQA.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementDisplayInspector {
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean elementExists(By locator) {
        try {
            System.out.println("Element is exist: " + locator.toString());
            // Wait until the element is visible
            WebElement button = driver.findElement(locator);
            // Perform actions on the element
            System.out.println("end");
            return button.isDisplayed();
        } catch (Exception e) {
            System.out.println("exeption");
            return false;
        }
    }

    public Boolean elementIsDisappear(By locator) {
        try {
            System.out.println("Element is disappear: " + locator.toString());
            WebElement button = driver.findElement(locator);
            // Perform actions on the element
            System.out.println("end");
            return button.isDisplayed();
        } catch (Exception e) {
            System.out.println("exeption");
            return true;
        }
    }
}
