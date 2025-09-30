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


    public List<String> expectedResults = List.of(
            "home",
            "desktop", "notes", "commands",
            "documents", "workspace", "react", "angular", "veu",
            "office", "public", "private", "classified", "general",
            "downloads", "wordFile", "excelFile"
    );


    // Individual locators if needed
    public By home_Checkbox = By.cssSelector("label[for='tree-node-home'] span.rct-checkbox");
}
