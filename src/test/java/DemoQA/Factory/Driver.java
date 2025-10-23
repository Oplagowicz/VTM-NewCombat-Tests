package DemoQA.Factory;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {

    /**
     * Create and configure WebDriver instance.
     * @param browser Browser type ("chrome", "firefox")
     * @param mode Screen mode ("desktop", "mobile")
     * @return Ready-to-use WebDriver
     */
    public static WebDriver createDriver(String browser, String mode) {
        // Detect if running in GitHub Actions (CI)
        boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));

        if (browser == null) browser = "chrome";
        if (mode == null) mode = "desktop";

        // Force headless mode on CI
        if (isCI && !"mobile".equalsIgnoreCase(mode)) {
            mode = "headless";
        }

        WebDriver driver = switch (browser.toLowerCase()) {
            case "chrome" -> createChromeDriver(mode);
            case "firefox" -> createFirefoxDriver(mode);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

        // Resize window based on mode
        try {
            if ("desktop".equalsIgnoreCase(mode)) {
                // Maximize only for desktop to avoid breaking mobile emulation
                driver.manage().window().maximize();
            } else if ("mobile".equalsIgnoreCase(mode)) {
                // For mobile, set fixed resolution to simulate device viewport
                driver.manage().window().setSize(new Dimension(375, 812));
            }
        } catch (Exception e) {
            System.out.println("[WARN] Unable to resize window: " + e.getMessage());
        }

        return driver;
    }

    // ---------- Chrome setup ----------
    private static WebDriver createChromeDriver(String mode) {
        ChromeOptions options = new ChromeOptions();

        // Add safe defaults for CI
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");

        if ("headless".equalsIgnoreCase(mode)) {
            options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
        }

        if ("mobile".equalsIgnoreCase(mode)) {
            options.addArguments("--headless=new", "--disable-gpu", "--window-size=375,812");

        }

        return new ChromeDriver(options);
    }

    // ---------- Firefox setup ----------
    private static WebDriver createFirefoxDriver(String mode) {
        FirefoxOptions options = new FirefoxOptions();

        if ("headless".equalsIgnoreCase(mode)) {
            options.addArguments("-headless", "-width=1920", "-height=1080");
        }


        if ("mobile".equalsIgnoreCase(mode)) {
            options.addArguments("-headless", "-width=375", "-height=812");
        }

        return new FirefoxDriver(options);
    }
}
