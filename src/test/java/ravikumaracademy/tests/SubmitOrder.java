package ravikumaracademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class SubmitOrder extends Basetest{

	
		@Test(dataProvider= "getData", groups= {"purchase"})
		public void submitOrder(String email,String password, String productname) throws IOException, InterruptedException {
				String countryName="India";
	ProductCataloge productcataloge=landingpage.loginApplication(email, password);
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
		return new Object[][] {{"cravi.ravikumar1@gmail.com","Ravi@5694","ZARA COAT 3"},
		{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
		
	}
	
		
}


