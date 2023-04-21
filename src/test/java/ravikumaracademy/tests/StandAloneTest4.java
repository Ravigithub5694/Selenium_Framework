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
import ravikumaracademy.PageObjects.LandingPage;
import ravikumaracademy.PageObjects.ProductCataloge;
import ravikumaracademy.PageObjects.cartPage;
import ravikumaracademy.PageObjects.checkOutPage;
import ravikumaracademy.PageObjects.confirmationPage;

public class StandAloneTest4 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productname="ZARA COAT 3";
		String countryName="India";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LandingPage landingpage=new LandingPage(driver);
		landingpage.GoTo();
		ProductCataloge productcataloge=landingpage.loginApplication("cravi.ravikumar1@gmail.com", "Ravi@5694");
		productcataloge.addToCart(productname);
		Thread.sleep(3000);
		cartPage cartpage=productcataloge.goToCartPage();
		Boolean match=cartpage.matchCartItems(productname);
		Assert.assertTrue(match);
		cartpage.checkoutTheCart();
		checkOutPage checkoutpage=new checkOutPage(driver);
		checkoutpage.typeCountry("Ind");
		checkoutpage.selectCountry(countryName);
		confirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmMessage=confirmationpage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	
	}

}
