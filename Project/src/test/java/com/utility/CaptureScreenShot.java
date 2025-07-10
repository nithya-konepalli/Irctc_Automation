package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenShot {
	public static void captureScreenShot(WebDriver driver) {	
		try {
			   File screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
		
			   File destinationPath=new File("C:\\Users\\2403633\\eclipse-workspace\\Project\\src\\test\\resource\\ScreenShot_"+ timestamp() +"_irctc.png");
			   FileUtils.copyFile(screenshot,destinationPath);
			   System.out.println("Screenshot saved Successfully..");
		}
		catch(IOException e) {
			   System.out.println("Failed to save the Screenshot"+e.getMessage());
		}
	}
	public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}
