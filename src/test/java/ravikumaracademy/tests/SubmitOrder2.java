package ravikumaracademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ravikumaracademy.PageObjects.LandingPage;
import ravikumaracademy.PageObjects.OrderPage;
import ravikumaracademy.PageObjects.ProductCataloge;
import ravikumaracademy.PageObjects.cartPage;
import ravikumaracademy.PageObjects.checkOutPage;
import ravikumaracademy.PageObjects.confirmationPage;
import ravikumaracademy.testComponents.Basetest;

public class SubmitOrder2 extends Basetest{

	
		@Test(dataProvider= "getData", groups= {"purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
				String countryName="India";
	ProductCataloge productcataloge=landingpage.loginApplication(input.get("email"), input.get("password"));
		productcataloge.addToCart(input.get("product"));
		Thread.sleep(3000);
		cartPage cartpage=productcataloge.goToCartPage();
		Boolean match=cartpage.matchCartItems(input.get("product"));
		Assert.assertTrue(match);
		cartpage.checkoutTheCart();
		checkOutPage checkoutpage=new checkOutPage(driver);
		checkoutpage.typeCountry("Ind");
		checkoutpage.selectCountry(countryName);
		confirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmMessage=confirmationpage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
		//to verify if zara coat is present in the order history page
		@Test(dependsOnMethods= {"submitOrder"})
		public void orderHistoryPage() throws IOException, InterruptedException {
			String productname="ZARA COAT 3";
			ProductCataloge productcataloge=landingpage.loginApplication("cravi.ravikumar1@gmail.com", "Ravi@5694");
			OrderPage orderpage=productcataloge.goToOrderPage();
			Assert.assertTrue(orderpage.verifyOrderDisplay(productname));
			
		}
		
	@DataProvider
	public Object[][] getData() {
		
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("email", "cravi.ravikumar1@gmail.com");
		map.put("password", "Ravi@5694");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
		
	}
		
}


