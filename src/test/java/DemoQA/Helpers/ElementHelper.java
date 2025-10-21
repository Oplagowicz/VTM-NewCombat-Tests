package DemoQA.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementHelper {
    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(ElementHelper.class);

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Returns true if the element becomes visible within the wait timeout.
     */

    public Boolean elementExists(By locator) {
        try {
            logger.debug("Checking existence (visibility) of element: {}", locator);
            // Wait until the element is visible
            WebElement button = driver.findElement(locator);
            // Perform actions on the element
            logger.info("end");
            return button.isDisplayed();
        } catch (Exception e) {
            logger.error("Unexpected error while checking element existence: {}", locator, e);
            return false;
        }
    }

    /**
     * Returns true when the element is not present or not visible (i.e. disappeared).
     */

    public Boolean elementIsDisappear(By locator) {
        try {
            logger.debug("Waiting for element to disappear: {}", locator);
            WebElement button = driver.findElement(locator);
            // Perform actions on the element
            logger.info("end");
            return button.isDisplayed();
        } catch (Exception e) {
            logger.error("exception");
            return true;
        }
    }
}
