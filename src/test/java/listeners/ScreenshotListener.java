package listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;

/**
 * JUnit 5 extension for capturing screenshots on test failures
 * and attaching them to Allure report
 */
public class ScreenshotListener implements AfterTestExecutionCallback, AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        context.getExecutionException().ifPresent(throwable -> {
            captureFailureArtifacts(context);
        });
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        context.getExecutionException().ifPresent(throwable -> {
            captureFailureArtifacts(context);
        });
    }

    private void captureFailureArtifacts(ExtensionContext context) {
        String testName = context.getDisplayName();
        System.out.println("\n=== Test Failed: " + testName + " ===");
        
        attachScreenshot(testName);
        attachPageSource(testName);
        attachBrowserLogs(testName);
    }

    private void attachScreenshot(String testName) {
        try {
            if (!WebDriverRunner.hasWebDriverStarted()) {
                System.out.println("WebDriver not started, skipping screenshot");
                return;
            }
            
            WebDriver driver = WebDriverRunner.getWebDriver();
            if (driver instanceof org.openqa.selenium.TakesScreenshot) {
                byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            
                if (screenshot != null && screenshot.length > 0) {
                    Allure.addAttachment(
                        "Screenshot: " + testName,
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        "png"
                    );
                    System.out.println("✓ Screenshot attached");
                }
            }
        } catch (Exception e) {
            System.err.println("✗ Failed to capture screenshot: " + e.getMessage());
        }
    }

    private void attachPageSource(String testName) {
        try {
            if (!WebDriverRunner.hasWebDriverStarted()) {
                return;
            }
            
            WebDriver driver = WebDriverRunner.getWebDriver();
            String pageSource = driver.getPageSource();
            
            if (pageSource != null && !pageSource.isEmpty()) {
                Allure.addAttachment(
                    "Page Source: " + testName,
                    "text/html",
                    new ByteArrayInputStream(pageSource.getBytes()),
                    "html"
                );
                System.out.println("✓ Page source attached");
            }
        } catch (Exception e) {
            System.err.println("✗ Failed to capture page source: " + e.getMessage());
        }
    }

    private void attachBrowserLogs(String testName) {
        try {
            if (!WebDriverRunner.hasWebDriverStarted()) {
                return;
            }
            
            WebDriver driver = WebDriverRunner.getWebDriver();
            if (driver instanceof RemoteWebDriver) {
                RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
                LogEntries logEntries = remoteDriver.manage().logs().get(LogType.BROWSER);
                
                if (logEntries != null && !logEntries.getAll().isEmpty()) {
                    StringBuilder logs = new StringBuilder();
                    logEntries.forEach(entry -> {
                        logs.append("[").append(entry.getLevel()).append("] ")
                            .append(entry.getTimestamp()).append(" - ")
                            .append(entry.getMessage()).append("\n");
                    });
                    
                    Allure.addAttachment(
                        "Browser Logs: " + testName,
                        "text/plain",
                        new ByteArrayInputStream(logs.toString().getBytes()),
                        "txt"
                    );
                    System.out.println("✓ Browser logs attached (" + logEntries.getAll().size() + " entries)");
                }
            }
        } catch (Exception e) {
            System.err.println("✗ Failed to capture browser logs: " + e.getMessage());
        }
    }

    @Attachment(value = "{0}", type = "image/png")
    private byte[] createScreenshotAttachment(String name, byte[] screenshot) {
        return screenshot;
    }
}
