package com.patio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By SEARCH_INPUT = By.id("search-input");
    private static final By SEARCH_BUTTON = By.cssSelector(".search-btn");
    private static final By ACCEPT_COOKIES_BTN = By.id("cookie-agree");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return wait.forElementVisible(SEARCH_INPUT).isDisplayed();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void acceptCookies() {
        try {
            findElement(ACCEPT_COOKIES_BTN).click();
        } catch (Exception e) {
//            log.warn("Cookie acceptance button not found");
        }
    }

    public SearchResultsPage searchForProduct(String query) {
        findElement(SEARCH_INPUT).sendKeys(query);
        findElement(SEARCH_BUTTON).click();
        return new SearchResultsPage(driver);
    }
}
