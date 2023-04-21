package ravikumaracademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalome2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("cravi.ravikumar1@gmail.com");
		driver.findElement(By.cssSelector("input#userPassword")).sendKeys("Ravi@5694");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(
			s->s.findElement(By.tagName("b")).getText()
			.contains(productname)).findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		//confirming the toast message
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartitems=driver.findElements(By.cssSelector(".cartSection h3"));
		//validating the cart items macth the selecetd item
		Boolean match=cartitems.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		Thread.sleep(3000);
		//to scroll down
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,520)");
		
		driver.findElement(By.cssSelector("li.totalRow button")).click();
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List<WebElement> countries=driver.findElements(By.cssSelector("button.ng-star-inserted"));
		//applying stream and selecting India
		for(int i=0;i<countries.size();i++) {
			
			if(countries.get(i).getText().equals("India")) {
				countries.get(i).click();
				break;
			}
		}
		driver.findElement(By.linkText("Place Order")).click();
	//	driver.findElement(By.cssSelector(".action_submit")).click();
		String confirmmessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
}
