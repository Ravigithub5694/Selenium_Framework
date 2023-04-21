package ravikumaracademy.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import ravikumaracademy.PageObjects.LandingPage;

public class Basetest {

	
	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {
		//properties class
		Properties prop= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\ravikumaracademy\\resorces\\globaldata.properties");
		prop.load(fis);
		//getting the browser from maven commands
		String browser=System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");
		//prop.getProperty("browser");
		if(browser.contains("chrome")) {
			
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browser.contains("headless")){
			options.addArguments("headless");// to run in chrome headless mode
			}
		driver=new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
		
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}


	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver=initializeDriver();
		landingpage=new LandingPage(driver);
		landingpage.GoTo();
		return landingpage;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File fil=new File(System.getProperty("user.dir")+"//reports"+testcasename+".png");
		FileUtils.copyFile(source, fil);
		return System.getProperty("user.dir")+"//reports"+testcasename+".png";
	}

	//

}
