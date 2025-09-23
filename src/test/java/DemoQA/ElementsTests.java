package DemoQA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class ElementsTests {

    WebDriver driver;
    String mainURL = "https://demoqa.com/";

    @BeforeTest
    public void prep() {
        driver = new ChromeDriver();
        driver.get(mainURL);
    }

}
