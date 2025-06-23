package com.patio.listeners;

import com.patio.drivers.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Allure.step("Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachScreenshot();
        attachPageSource();
        Allure.step("Test passed: " + result.getName(), Status.PASSED);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot();
        attachPageSource();
        Allure.step("Test failed: " + result.getName(), Status.FAILED);
        Allure.addAttachment("Exception", result.getThrowable().getMessage());
    }

    private void attachScreenshot() {
        if (WebDriverManager.getDriver() instanceof TakesScreenshot) {
            Allure.addAttachment("Screenshot", "image/png"
            );
        }
    }

    private void attachPageSource() {
        Allure.addAttachment("Page source", "text/html", WebDriverManager.getDriver().getPageSource(), "html");
    }
}
