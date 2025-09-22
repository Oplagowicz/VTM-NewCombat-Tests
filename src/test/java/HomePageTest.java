import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HomePageTest {
    WebDriver driver;

    @Test
    public void firstTest() {
        driver = new ChromeDriver();
        String homeURL = "https://oplagowicz.github.io/VTM-NewCombat/";
        driver.get(homeURL);
        driver.quit();
    }
}
