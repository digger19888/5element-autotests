package com.patio.ui;

import com.patio.pages.HomePage;
import com.patio.pages.SearchResultsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class HomePageTests extends BaseTest {
    @Test
    public void verifyHomePageTitle() {
        HomePage homePage = new HomePage(getDriver());
        String expectedTitle = "5 элемент - интернет-магазин электроники в Беларуси";

        assertEquals(homePage.getTitle(), expectedTitle, "Page title is incorrect");
    }

    @Test
    public void verifySearchFunctionality() {
        HomePage homePage = new HomePage(getDriver());
        String searchQuery = "iPhone 15";

        SearchResultsPage resultsPage = homePage.searchForProduct(searchQuery);
        assertTrue(resultsPage.areResultsDisplayed(), "Search results not displayed");
        assertTrue(resultsPage.getResultsCount() > 0, "No search results found");
    }
}
