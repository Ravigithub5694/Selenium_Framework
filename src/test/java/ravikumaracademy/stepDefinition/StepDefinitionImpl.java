package ravikumaracademy.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ravikumaracademy.PageObjects.LandingPage;
import ravikumaracademy.PageObjects.ProductCataloge;
import ravikumaracademy.PageObjects.cartPage;
import ravikumaracademy.PageObjects.checkOutPage;
import ravikumaracademy.PageObjects.confirmationPage;
import ravikumaracademy.testComponents.Basetest;

public class StepDefinitionImpl extends Basetest{

	public LandingPage landingpage;
	public ProductCataloge productcataloge;
	public confirmationPage confirmationpage;
	@Given("I laneded on Ecomerce page")
	public void I_laneded_on_Ecomerce_page() throws IOException {
		
		launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password(.+)$")
	public void logged_in_username_and_password(String email, String password) {
		productcataloge=landingpage.loginApplication(email, password);
	}
	
	@When("^I add the product (.+) from cart$")
	public void i_add_the_product_from_cart(String product) {
		productcataloge.addToCart(product);
	}
	@When("^Checkout the product (.+) and submit the order$")
	public void checkout_the_product_and_submit_the_order(String product) throws InterruptedException {
		Thread.sleep(3000);
		cartPage cartpage=productcataloge.goToCartPage();
		Boolean match=cartpage.matchCartItems(product);
		Assert.assertTrue(match);
		cartpage.checkoutTheCart();
		checkOutPage checkoutpage=new checkOutPage(driver);
		checkoutpage.typeCountry("Ind");
		checkoutpage.selectCountry("India");
		confirmationpage=checkoutpage.submitOrder();
	}
	
	@Then("verify {string} message is displayed")
	public void verify_message_displayed_confirmationPage(String string) {
		String confirmMessage=confirmationpage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
		
	}
}
