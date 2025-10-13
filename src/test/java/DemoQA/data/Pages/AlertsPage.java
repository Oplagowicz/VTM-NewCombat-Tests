package DemoQA.data.Pages;

import org.openqa.selenium.By;

public class AlertsPage {
    public static String alertButtonText = "You clicked a button";
    public static String timerAlertButtonText = "This alert appeared after 5 seconds";
    public static String confirmButtonTextOk = "You selected Ok";
    public static String confirmButtonTextCancel = "You selected Cancel";
    public static String promptButtonText = "You entered Test";
    public By alertButton = By.id("alertButton");
    public By timerAlertButton = By.id("timerAlertButton");
    public By confirmButton = By.id("confirmButton");
    public By promptButton = By.id("promtButton");
    public By confirmResult = By.id("confirmResult");
    public By promptResult = By.id("promptResult");

}
