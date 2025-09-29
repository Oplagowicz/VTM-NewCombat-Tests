package DemoQA.data.Pages;

import org.openqa.selenium.By;

import java.util.Collections;
import java.util.List;

public class CheckboxPage {

    public By expandAllBtnLocator = By.cssSelector(".rct-option-expand-all");
    public By collapseAllBtnLocator = By.cssSelector(".rct-option-collapse-all");

    public By collapsedTreeLocator = By.cssSelector(".rct-node.rct-node-collapsed");
    public By expandedTreeLocator = By.cssSelector(".rct-node.rct-node-expanded");

    public By resultItemsLocator = By.cssSelector("#result .text-success");

    public List<By> allCheckboxLocators = Collections.singletonList(By.cssSelector("label[for^='tree-node-'] span.rct-checkbox"));

//    public List<By> checkBoxListLocators = List.of(
//            By.cssSelector("label[for='tree-node-home'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-desktop'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-notes'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-commands'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-documents'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-workspace'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-react'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-angular'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-veu'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-office'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-public'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-private'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-classified'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-general'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-downloads'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-wordFile'] span.rct-checkbox"),
//            By.cssSelector("label[for='tree-node-excelFile'] span.rct-checkbox")
//    );


    public List<String> expectedResults = List.of(
            "home",
            "desktop", "notes", "commands",
            "documents", "workspace", "react", "angular", "veu",
            "office", "public", "private", "classified", "general",
            "downloads", "wordFile", "excelFile"
    );


    // Individual locators if needed
    public By home_Checkbox = By.cssSelector("label[for='tree-node-home'] span.rct-checkbox");
//        WebElement desktop_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-desktop'] span.rct-checkbox")));
//        WebElement notes_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-notes'] span.rct-checkbox")));
//        WebElement commands_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-commands'] span.rct-checkbox")));
//        WebElement documents_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-documents'] span.rct-checkbox")));
//        WebElement workspace_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-workspace'] span.rct-checkbox")));
//        WebElement react_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-react'] span.rct-checkbox")));
//        WebElement angular_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-angular'] span.rct-checkbox")));
//        WebElement veu_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-veu'] span.rct-checkbox")));
//        WebElement office_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-office'] span.rct-checkbox")));
//        WebElement public_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-public'] span.rct-checkbox")));
//        WebElement private_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-private'] span.rct-checkbox")));
//        WebElement classified_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-classified'] span.rct-checkbox")));
//        WebElement general_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-general'] span.rct-checkbox")));
//        WebElement downloads_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-downloads'] span.rct-checkbox")));
//        WebElement wordFile_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-wordFile'] span.rct-checkbox")));
//        WebElement excelFile_Checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='tree-node-excelFile'] span.rct-checkbox")));
}
