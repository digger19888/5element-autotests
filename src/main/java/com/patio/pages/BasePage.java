package com.patio.pages;

import com.patio.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WaitUtils wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public abstract boolean isPageLoaded();

    protected WebElement findElement(By locator) {
        return wait.forElementVisible(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return wait.forElementsVisible(locator);
    }
}