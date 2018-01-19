package de.codecentric.springbootsample;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.AssertJUnit;

public class SeleniumIntegrationTest {
	
	    @Test
	    public void testExecution() throws IOException {
	        System.setProperty("webdriver.chrome.driver", "chromedriver");

	        // Add options to Google Chrome. The window-size is important for responsive sites
	        ChromeOptions options = new ChromeOptions();
	       
	        //options.addArguments("'headless', 'sudo'");
	        options.addArguments("headless");
	        options.addArguments("sudo");
	        options.addArguments("window-size=1200x600");

	        WebDriver driver = new ChromeDriver(options);
	        driver.get("http://localhost:8090/");
	        
	        // a guarantee that the test was really executed
	        //assertTrue(driver.findElement(By.id("q")).isDisplayed());
	        System.out.println("check--" + driver.getCurrentUrl());
	        //System.out.println(driver.getTitle());
	        //assertEquals(driver.findElement(By.tagName("title")), "Spring Boot Sample App");
	       String Expectedtitle = "http://localhost:8090/";
	      //it will fetch the actual title
	       
	      String Actualtitle = driver.getCurrentUrl();
	      System.out.println("Before Assetion " + Expectedtitle + Actualtitle);
	      //it will compare actual title and expected title
	      assertEquals(Expectedtitle, Actualtitle);
	      //print out the result
	      System.out.println("After Assertion " + Expectedtitle + Actualtitle + " Title matched ");
	        driver.quit();
	    }
	
}
