package ravikumaracademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ravikumaracademy.PageObjects.ProductCataloge;
import ravikumaracademy.PageObjects.cartPage;
import ravikumaracademy.PageObjects.checkOutPage;
import ravikumaracademy.PageObjects.confirmationPage;
import ravikumaracademy.testComponents.Basetest;

public class ErrorValidationtest extends Basetest{

	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws IOException, InterruptedException {
		landingpage.loginApplication("cravi.ravikumar1@gmail.com", "Ravi@5");
		
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
}
	@Test(groups= {"ErrorHandling"})
	public void productErrorValidation() throws IOException, InterruptedException {
		
	String productname="ZARA COAT 3";
	
ProductCataloge productcataloge=landingpage.loginApplication("cravi.ravikumar1@gmail.com", "Ravi@5694");
	productcataloge.addToCart(productname);
	Thread.sleep(3000);
	cartPage cartpage=productcataloge.goToCartPage();
	Boolean match=cartpage.matchCartItems("ZARA COAT 33");
	Assert.assertTrue(match);
	
}
	
}

