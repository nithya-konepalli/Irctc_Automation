package com.irctc_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HotelFiltersPage {
	
	//Declaration
    WebDriver driver;
    JavascriptExecutor js;
    //Defining Locators
    private By minPriceFilter = By.xpath("//label[@for='night1']");
    private By maxPriceFilter = By.xpath("//label[@for='night2']");
    private By place1Filter = By.xpath("//label[@for='poitype1']");
    private By place2Filter = By.xpath("//label[@for='poitype3']");
    private By userRatingFilter = By.xpath("//label[@for='ratings1']");

    public HotelFiltersPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void applyPriceFilter() throws InterruptedException {
    	
        driver.findElement(minPriceFilter).click();
        Thread.sleep(2000);
        driver.findElement(maxPriceFilter).click();
        Thread.sleep(2000);
    }

    public void applyPlaceFilters() throws InterruptedException {
    	
        js.executeScript("arguments[0].click();", driver.findElement(place1Filter));
        Thread.sleep(2000);
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(place2Filter));
        Thread.sleep(2000);
        driver.findElement(place2Filter).click();
    }

    public void applyUserRatingFilter() throws InterruptedException {
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", driver.findElement(userRatingFilter));
    }
}

