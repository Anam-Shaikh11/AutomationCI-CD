package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	public WebDriver driver;

	
	//This method prepares driver for furthur use
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		/*
		 * FileInputStream fis = new FileInputStream(
		 * "D:\\Anaam notes\\Selenium\\FMSession1\\src\\main\\resources\\SeleniumFrameWork\\Resources\\GlobalData.properties"
		 * );
		 */
		//To get the path of the directory and make it work in any system
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")+"\\src\\main\\java\\resourcses\\GlobalData.properties");
		prop.load(fis);
		
		
		//to take value from cmd/terminal while running maven command or else if not given then properties file
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		
		
		//String browser = prop.getProperty("browser");

		if (browser.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions op= new ChromeOptions();
			if (browser.contains("headless"))
			 {
			 op.addArguments("--headless=new");
			 op.addArguments("--window-size=1920,1080");
			 }
			 driver= new ChromeDriver(op);
			
			 
			
		}
		else if (browser.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		return driver;
	}
	
	
	public PageObject.Login_Page launchApplication() throws IOException
	{
		// take the value of prepared driver
		 driver=initializeDriver();
		 PageObject.Login_Page page=new PageObject.Login_Page(driver);
		 page.goTo();
		 return page;
	}
	
	public List<HashMap<String,String>> getJsonData(String filename) throws IOException {

		// sending file object not just filr mname that is why new File

		
		  String jsonString = FileUtils.readFileToString( new
		  File(System.getProperty("user.dir") +filename
		  
		  ), StandardCharsets.UTF_8 );
		 
		// Download maven xml content from mvn repository for Jackson datanbind
		// String to Hashmap\
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> hashmap_Data = mapper.readValue(
			    jsonString,
			    new TypeReference<List<HashMap<String, String>>>() {}
		);
		return hashmap_Data;

	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		File sc = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//Copy file to or give dest name
		String path = System.getProperty("user.dir")+"\\src\\test\\"+testCaseName+".png";
		File fs=new File(path);
		FileUtils.copyFile(sc, fs);
		return path;
	}
	
	@AfterMethod
	public void teardown()
	{
	if (driver != null) {
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Error while quitting driver: " + e.getMessage());
        }
    }
}
}
