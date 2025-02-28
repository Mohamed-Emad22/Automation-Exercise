package utilites;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class helperClass {

    /**
     * Captures a screenshot and returns it as a Base64-encoded string.
     *
     * @param driver The WebDriver instance.
     * @return Base64-encoded screenshot string.
     * @throws IOException If an I/O error occurs.
     */
    public static String capture(WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Create the screenshots directory if it doesn't exist
        String screenshotDir = "Reports/screenshots";
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a unique filename with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String dest = screenshotDir + File.separator + "screenshot_" + timestamp + "_" + getSerialNumber() + ".png";
        File destination = new File(dest);

        // Save the screenshot to the destination file
        FileUtils.copyFile(source, destination);

        // Read the image file as bytes
        Path path = destination.toPath();
        byte[] imageBytes = Files.readAllBytes(path);

        // Encode the image bytes to base64
        String Screenshot_Image = Base64.getEncoder().encodeToString(imageBytes);

        return Screenshot_Image;
    }

    // Static counter for serial numbers
    private static int screenshotCounter = 1;

    /**
     * Generates a unique serial number for screenshot filenames.
     *
     * @return The next serial number.
     */
    private static synchronized int getSerialNumber() {
        return screenshotCounter++;
    }
}