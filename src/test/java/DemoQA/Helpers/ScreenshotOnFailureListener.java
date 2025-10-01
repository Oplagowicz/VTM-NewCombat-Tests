package DemoQA.Helpers;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

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
        if (driver == null) return;

        var path = Screenshots.saveFull(driver, result.getMethod().getMethodName());
        if (path != null) {
            System.out.println("[Screenshot saved] " + path.toAbsolutePath());
            result.setAttribute("screenshotPath", path.toAbsolutePath().toString()); // useful in reports
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
