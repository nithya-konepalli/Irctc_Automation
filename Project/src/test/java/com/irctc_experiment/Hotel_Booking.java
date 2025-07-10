package com.irctc_experiment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import com.utility.CaptureScreenShot;

public class Hotel_Booking {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		
		// Launching the Browser
	       driver=new EdgeDriver();
	       driver.get("https://hotels.irctc.co.in/");
	       driver.manage().window().maximize();
	       Thread.sleep(1000);
	       
	    // Creating a WebElement instance and Searching for City
	       
	       WebElement City=driver.findElement(By.id("inputbox"));
	       City.sendKeys("Jaipur");
	    
	   // Selecting desired city from the provided search Options    
	       WebElement cityOption = driver.findElement(By.xpath("//*[@id=\"autofillId\"]/ul/li[6]"));
	       cityOption.click();
	       Thread.sleep(1000);
	    
	    // casting the driver to JavascriptExecutor
	       JavascriptExecutor js=(JavascriptExecutor) driver;
	       
	    // Selecting the CheckIn date 
	       WebElement checkIn=driver.findElement(By.xpath("//input[@type='text' and @formcontrolname='checkInDate']")); 
	       LocalDate today = LocalDate.now();
	       String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	       js.executeScript("arguments[0].value='" + formattedDate + "';", checkIn);
	       Thread.sleep(1000);
	     
	    // Selecting the CheckOut date 
	       WebElement checkOut=driver.findElement(By.xpath("//input[@type='text' and @formcontrolname='checkOutDate']"));
	       LocalDate futureDate = LocalDate.now().plusDays(2);
	       String formatDate = futureDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));    
	       js.executeScript("arguments[0].value='" + formatDate + "';", checkOut);
	       Thread.sleep(1000);
	       
	       //Clicking on Guest
	       WebElement guest=driver.findElement(By.xpath("//input [@type='text' and @formcontrolname='guest']"));
	       guest.click();
	       
	       WebElement Children=driver.findElement(By.xpath("//select[@formcontrolname='childs']"));
	       Select select=new Select(Children);
	       select.selectByValue("1");
	       Thread.sleep(1000);
	       
	       WebElement child_age=driver.findElement(By.id("age0"));
	       Select age=new Select(child_age);
	       age.selectByValue("5");
	       Thread.sleep(2000);
	       
	       WebElement Done=driver.findElement(By.xpath("//button[@class=\"hvr-shutter-in-vertical introDefault\"]"));
	       Done.click();
	       Thread.sleep(2000);
	        
	       WebElement Search=driver.findElement(By.xpath("//button[@class=\"hvr-shutter-in-vertical\"]"));
	       Search.click();
	       Thread.sleep(2000);
	       CaptureScreenShot.captureScreenShot(driver);
	     
	      // Applying Price filters
	       WebElement minPrice=driver.findElement(By.xpath("//label[@for='night1']"));
	       minPrice.click();
	       
	      // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
	       WebElement maxPrice = driver.findElement(By.xpath("//label[@for='night2']"));
	       maxPrice.click(); 
	      
	       //Applying Place filters
	       WebElement Place1 = driver.findElement(By.xpath("//label[@for='poitype1']"));
	       js.executeScript("arguments[0].click();", Place1);
	       Thread.sleep(1000);
	       
	       WebElement Place2=driver.findElement(By.xpath("//label[@for='poitype3']"));
	       js.executeScript("arguments[0].scrollIntoView(true);", Place2);
	       Thread.sleep(2000); // Give time for UI update
	       Place2.click();
	      
	       //Applying User rating filter
	       WebElement UserRating=driver.findElement(By.xpath("//label[@for='ratings1']"));
	       Thread.sleep(1000);
	       js.executeScript("arguments[0].click();", UserRating);
	       CaptureScreenShot.captureScreenShot(driver);
	       
	       //Scrolling the WebPage to load the Hotels
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
           
	        //List Hotel Names
	        List<WebElement> hotelNames=new ArrayList<>();
	        hotelNames=driver.findElements(By.xpath("//*[@id='hotelContainer']/div[*]/a/div/div[2]/h2/a/span"));
	        
	        //List Hotel Locations
	        List<WebElement> hotelLocations=new ArrayList<>();
	        hotelLocations=driver.findElements(By.xpath("//*[@id='hotelContainer']/div[*]/a/div/div[2]/div[1]"));
	        
	        //List Hotel Prices
	        List<WebElement> hotelPrices=new ArrayList<>();
	        hotelPrices=driver.findElements(By.xpath("//div[contains(@class,'priceMain')]"));
	        
	        
	        System.out.println("Available Hotels: "+previousCount); 
	     
	        for(int i=0;i<previousCount;i++){
	        	System.out.println("Hotel Name: "+hotelNames.get(i).getText());
	        	System.out.println("Hotel Address: "+hotelLocations.get(i).getText());
	        	System.out.println("Hotel Price: "+hotelPrices.get(i).getText());
	        	System.out.println();
	        }       
	       
	       CaptureScreenShot.captureScreenShot(driver);
	       Thread.sleep(2000);
	       driver.quit();
	      
	}
}
