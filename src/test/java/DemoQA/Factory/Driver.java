package DemoQA.Factory;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class Driver {

    /**
     * Create and configure WebDriver instance.
     * @param browser Browser type ("chrome", "firefox")
     * @param mode Screen mode ("desktop", "mobile")
     * @return Ready-to-use WebDriver
     */
    public static WebDriver createDriver(String browser, String mode) {
        if (browser == null) browser = "chrome";
        if (mode == null) mode = "desktop";

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

        if ("mobile".equalsIgnoreCase(mode)) {
            // Simulate mobile using Chrome DevTools Protocol (CDP)
            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", 375);
            deviceMetrics.put("height", 812);
            deviceMetrics.put("pixelRatio", 3);

            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5 like Mac OS X) " +
                            "AppleWebKit/605.1.15 (HTML, like Gecko) Version/13.1 Mobile/15E148 Safari/604.1");

            options.setExperimentalOption("mobileEmulation", mobileEmulation);
        } else {
            options.addArguments("--window-size=1366,768");
        }

        // Optional: enable headless to reduce runtime
        // options.addArguments("--headless=new");

        return new ChromeDriver(options);
    }

    // ---------- Firefox setup ----------
    private static WebDriver createFirefoxDriver(String mode) {
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);

        if ("mobile".equalsIgnoreCase(mode)) {
            driver.manage().window().setSize(new Dimension(375, 812));
        }

        return driver;
    }
}
