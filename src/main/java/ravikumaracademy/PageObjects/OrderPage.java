package ravikumaracademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravikumaracademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;

	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	By carts=By.cssSelector(".cartSection h3");
	
	public Boolean verifyOrderDisplay(String productname) {
	
		Boolean match=productNames.stream().anyMatch(item->item.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	
}
