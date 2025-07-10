package com.irctc_pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelSearchPage {
    
	//Declaration 
	WebDriver driver;
    JavascriptExecutor js;
    //Defining Locators
    private By checkInDate = By.xpath("//input[@type='text' and @formcontrolname='checkInDate']");
    private By checkOutDate = By.xpath("//input[@type='text' and @formcontrolname='checkOutDate']");
    private By guestDropdown = By.xpath("//input [@type='text' and @formcontrolname='guest']");
    private By childSelect = By.xpath("//select[@formcontrolname='childs']");
    private By childAgeSelect = By.id("age0");
    private By doneButton = By.xpath("//button[@class='hvr-shutter-in-vertical introDefault']");
    private By searchButton = By.xpath("//button[@class='hvr-shutter-in-vertical']");

    public HotelSearchPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void setCheckInDate() throws InterruptedException {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	
        WebElement checkIn = wait.until(ExpectedConditions.elementToBeClickable(checkInDate));
        js.executeScript("arguments[0].click();", checkIn); // Click field
        
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        js.executeScript("arguments[0].value='" + formattedDate + "';", checkIn);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", checkIn); // Trigger change event
       
        Thread.sleep(2000);
        
    }

    public void setCheckOutDate() throws InterruptedException {
     
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	
        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(checkOutDate));	
        js.executeScript("arguments[0].click();", checkOut);
        
        LocalDate tomorrow = LocalDate.now().plusDays(2);
        String formattedDate = tomorrow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        js.executeScript("arguments[0].value='" + formattedDate + "';", checkOut);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", checkOut); // Trigger change event
        
        Thread.sleep(2000);

    }

    public void selectGuests() throws InterruptedException {
    	
        driver.findElement(guestDropdown).click();
        Select childrenDropdown = new Select(driver.findElement(childSelect));
        childrenDropdown.selectByValue("1");
        Thread.sleep(2000);

        Select ageDropdown = new Select(driver.findElement(childAgeSelect));
        ageDropdown.selectByValue("5");
        Thread.sleep(2000);

        driver.findElement(doneButton).click();
        Thread.sleep(2000);
    }

    public void clickSearch() throws Exception {
        driver.findElement(searchButton).click();
        Thread.sleep(2000);
    }
}