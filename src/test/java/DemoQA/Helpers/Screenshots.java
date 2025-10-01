package DemoQA.Helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Screenshots {
    private static final DateTimeFormatter DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("HH-mm-ss-SSS");

    private Screenshots() {
    }

    public static Path save(WebDriver driver, String testName) {
        if (driver == null) return null;
        if (!(driver instanceof TakesScreenshot)) return null;

        try {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            String day = LocalDate.now().format(DAY);
            Path dir = Paths.get("target", "screenshots", day);
            Files.createDirectories(dir);

            String safe = testName == null ? "test" : testName.replaceAll("[^a-zA-Z0-9._-]", "_");
            String fileName = safe + "_" + LocalTime.now().format(TS) + ".png";

            Path file = dir.resolve(fileName);
            Files.write(file, png, StandardOpenOption.CREATE);
            return file;
        } catch (Exception e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }

    }

    public static Path saveFull(WebDriver driver, String testName) {
        if (driver == null) return null;

        String safe = (testName == null || testName.isBlank()) ? "test"
                : testName.replaceAll("[^a-zA-Z0-9._-]", "_");

        try {
            Screenshot stitched = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(driver);
            BufferedImage img = stitched.getImage();

            String day = LocalDate.now().format(DAY);
            Path dir = Paths.get("target", "screenshots", day);
            Files.createDirectories(dir);

            String fileName = safe + "_full_" + LocalTime.now().format(TS) + ".png";

            Path file = dir.resolve(fileName);
            ImageIO.write(img, "PNG", file.toFile());
            return file;
        } catch (Exception e) {
            System.err.println("Full-page failed, falling back to viewport: " + e.getMessage());
        }

        return save(driver, testName);
    }
}
