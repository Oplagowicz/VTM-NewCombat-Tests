package DemoQA.Pages;

import org.openqa.selenium.By;

public class TextboxPage {

    public By nameInputLocator = By.id("userName");
    public By emailInputLocator = By.id("userEmail");
    public By currentAddressLocator = By.id("currentAddress");
    public By permanentAddressLocator = By.id("permanentAddress");
    public By submitBtnLocator = By.id("submit");
    public By outputLocator = By.id("output");


    // Input data
    public String nameTest = "Arthur Demichev";
    public String emailTest = "arthur.demichev@yahoo.net";
    public String addressTest = "Cracow, Poland";
}
