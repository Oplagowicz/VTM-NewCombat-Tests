package DemoQA.Pages;

import org.openqa.selenium.By;

public class ButtonsPage {
    public By rightClickBtnLocator = By.id("rightClickBtn");
    public By rightClickMessageLocator = By.id("rightClickMessage");

    public By clickMeBtnLocator = By.xpath("//button[text()='Click Me']");
    public By dynamicClickMessage = By.id("dynamicClickMessage");

    public By doubleClickBtnLocator = By.id("doubleClickBtn");
    public By doubleClickMessageLocator = By.id("doubleClickMessage");

}
