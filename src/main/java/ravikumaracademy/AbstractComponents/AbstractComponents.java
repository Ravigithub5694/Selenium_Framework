package ravikumaracademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ravikumaracademy.PageObjects.OrderPage;
import ravikumaracademy.PageObjects.cartPage;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait w;
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	//driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
	
	public cartPage goToCartPage() {
		cartHeader.click();
		cartPage cartpage=new cartPage(driver);
		return cartpage;
	}
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
	public void waitforElementToAppear(By findBY) {
		
	WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
	w.until(ExpectedConditions.visibilityOfElementLocated(findBY));
	
	}
	public void waitforElementToAppear(WebElement findBY) {
		
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(findBY));
		
		}
	public void waitforElementToDisapper(WebElement ele) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));	
		w.until(ExpectedConditions.invisibilityOf(ele));
	}
}
