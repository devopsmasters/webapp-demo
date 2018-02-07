package de.codecentric.springbootsample;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumIntegration {
	
	    @Test
	    public void testExecution() throws IOException {
	        
	    	DesiredCapabilities caps = DesiredCapabilities.chrome();
	        caps.setCapability("platform", "linux");
	        //caps.setCapability("version", "64.0.3282.119");
	        
	        WebDriver driver = new RemoteWebDriver(caps);
	    	
	        driver.get("http://localhost:8181/springdemo");
	        //driver.get("https://google.com");
	       
	      String Actualtitle = driver.getTitle();
	      System.out.println("Before Assetion " + Actualtitle);
	      //it will compare actual title and expected title
	      //assertEquals(Expectedtitle, Actualtitle);
	      driver.quit();
	    }
	
}

