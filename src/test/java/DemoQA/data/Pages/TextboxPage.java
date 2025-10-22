package DemoQA.data.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextboxPage {
    public By nameInputLocator = By.id("userName");
    public By emailInputLocator = By.id("userEmail");
    public By currentAddressLocator = By.id("currentAddress");
    public By permanentAddressLocator = By.id("permanentAddress");
    public By submitBtnLocator = By.id("submit");
    public By outputLocator = By.id("output");
    WebDriver driver;
    private WebDriverWait wait;


    public void TextBoxPageDrive(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Lazy getters: always re-find the element
    public WebElement nameInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputLocator));
    }

    public WebElement emailInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLocator));
    }

    public WebElement currentAddressInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentAddressLocator));
    }

    public WebElement permanentAddressInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(permanentAddressLocator));
    }

    public WebElement submitButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtnLocator));
    }

}
