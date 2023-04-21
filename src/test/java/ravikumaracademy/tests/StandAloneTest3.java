package ravikumaracademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest3 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("cravi.ravikumar1@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Ravi@5694");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> Products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=Products.stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		//waiting for toast message
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait for  disappearing the animated
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		//to check if the items are present n the cart page
		boolean match=cartProducts.stream().anyMatch(s->s.getText().equals(productname));
		Assert.assertTrue(match);
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
		//scrolling down to checkout button
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,520)");
		//clicking the checkout button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement
				(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List<WebElement> countries=driver.findElements(By.cssSelector("span.ng-star-inserted"));
		
		for(WebElement country:countries) {
			if(	country.getText().equals("India")) {
				country.click();
				break;
			}
		}
		driver.findElement(By.cssSelector("a[class*='submit']")).click();	
		
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}

}
