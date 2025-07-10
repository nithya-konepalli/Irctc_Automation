package com.irctc_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	WebDriver driver;

    private By cityInput = By.id("inputbox");
    private By citySelection = By.xpath("//*[@id='autofillId']/ul/li[6]");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCity(String city) throws InterruptedException {
        WebElement cityBox = driver.findElement(cityInput);
        cityBox.sendKeys(city);
        driver.findElement(citySelection).click();
        Thread.sleep(2000);
    }
}
