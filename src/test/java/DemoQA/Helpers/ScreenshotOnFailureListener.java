package DemoQA.Helpers;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static DemoQA.Helpers.ElementHelper.logger;

public class ScreenshotOnFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        capture(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        capture(result);
    }

    private void capture(ITestResult result) {
        WebDriver driver = resolveDriver(result.getInstance());

        if (driver == null) {
            // fallback - read driver from TestNG context set in BaseTest
            Object v = result.getTestContext().getAttribute("driver");
            if (v instanceof WebDriver) driver = (WebDriver) v;
        }

        if (driver != null) {
            Screenshots.save(driver, result.getName());
        } else {
            logger.error("[WARN] No WebDriver found for screenshot.");
        }
    }

    private WebDriver resolveDriver(Object testInstance) {
        if (testInstance == null) return null;
        try {
            var f = testInstance.getClass().getDeclaredField("driver");
            f.setAccessible(true);
            Object val = f.get(testInstance);
            return (val instanceof WebDriver) ? (WebDriver) val : null;
        } catch (Exception ignored) {
            return null;
        }
    }
}
