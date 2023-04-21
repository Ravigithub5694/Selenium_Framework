package ravikumaracademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravikumaracademy.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents {

	WebDriver driver;
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartItems;

	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	By carts=By.cssSelector(".cartSection h3");
	
	public List<WebElement> getCartItems() {
		waitforElementToAppear(carts);
		return cartItems;
	}
	public Boolean matchCartItems(String productname) {
	
		Boolean match=cartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	public void checkoutTheCart() throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,520)");
		Thread.sleep(3000);
		//waitForElementToClick(checkout);
		checkout.click();
		
	}
}
