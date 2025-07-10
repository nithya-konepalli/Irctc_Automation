package com.irctc_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.irctc_baseclass.BaseClass;
import com.irctc_pages.HomePage;
import com.irctc_pages.HotelFiltersPage;
import com.irctc_pages.HotelSearchPage;
import com.utility.CaptureScreenShot;
import com.utility.ExcelWriter;

public class HotelBookingTest extends BaseClass {
    WebDriver driver;
    HomePage homePage;
    HotelSearchPage hotelSearchPage;
    HotelFiltersPage hotelFiltersPage;

    @BeforeClass
    public void setUp() {
        initializeDriver();
        driver = getDriver();
        driver.get("https://hotels.irctc.co.in/");
        homePage = new HomePage(driver);
        hotelSearchPage = new HotelSearchPage(driver);
        hotelFiltersPage = new HotelFiltersPage(driver);
    }

    @Test(priority = 1)
    public void testFilter() throws Exception {
        homePage.enterCity("Jaipur");
        hotelSearchPage.setCheckInDate();
        hotelSearchPage.setCheckOutDate();
        hotelSearchPage.selectGuests();
        hotelSearchPage.clickSearch();
        Thread.sleep(2000);
        CaptureScreenShot.captureScreenShot(driver);

        // Apply filters
        hotelFiltersPage.applyPriceFilter();
        hotelFiltersPage.applyPlaceFilters();
        hotelFiltersPage.applyUserRatingFilter();
        
        Thread.sleep(2000);
        //Capturing Screenshot
        CaptureScreenShot.captureScreenShot(driver);
        
        //Counting Hotels by scrolling the page to load the hotels
        JavascriptExecutor js=(JavascriptExecutor) driver;
        int previousCount = 0;
	    List<WebElement> Hotels;
	    while (true) {
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	            Thread.sleep(3000); // Wait for hotels to load

	            Hotels = driver.findElements(By.xpath("//*[@id=\"hotelContainer\"]/div[*]"));

	            if (Hotels.size() == previousCount) {
	                break; // Stop scrolling when no new hotels load
	            }
	            previousCount = Hotels.size();
	   }
	        
        System.out.println("Available Hotels: "+previousCount);
        
        // Collect filtered hotel details
	        List<WebElement> hotelNames=new ArrayList<>();
	        hotelNames=driver.findElements(By.xpath("//*[@id='hotelContainer']/div[*]/a/div/div[2]/h2/a/span"));
	        
	        List<WebElement> hotelLocations=new ArrayList<>();
	        hotelLocations=driver.findElements(By.xpath("//*[@id='hotelContainer']/div[*]/a/div/div[2]/div[1]"));
	        
	        List<WebElement> hotelPrices=new ArrayList<>();
	        hotelPrices=driver.findElements(By.xpath("//div[contains(@class,'priceMain')]"));

        for (int i = 0; i < previousCount; i++) {
        	System.out.println(hotelNames.get(i).getText());     	
//            hotelLocations.get(i).getText();
//            hotelPrices.get(i).getText();
        }

        // Write filtered hotel details to Excel
        ExcelWriter.writeHotelsToExcel(hotelNames, hotelLocations, hotelPrices);
    }
    BaseClass base=new BaseClass();
    @AfterClass
    public void tearDown() {
    	CaptureScreenShot.captureScreenShot(driver);
        base.tearDown();
    }
}
