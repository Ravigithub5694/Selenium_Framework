package ravikumaracademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravikumaracademy.AbstractComponents.AbstractComponents;

public class ProductCataloge extends AbstractComponents{

	WebDriver driver;
	public ProductCataloge(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> Products=driver.findElements(By.cssSelector(".mb-3"))
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsby=By.cssSelector(".mb-3");
	By addtocart=By.cssSelector(".card-body button:last-of-type");
	By toastmessage=By.cssSelector("#toast-container");
	public List<WebElement> getProductList() {
		
		waitforElementToAppear(productsby);
		return products;
	}
	
	public WebElement getProductName(String productname) {
		WebElement prod=products.stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	public void addToCart(String productname) {
		WebElement prod=getProductName(productname);
		prod.findElement(addtocart).click();
		waitforElementToAppear(toastmessage);
		waitforElementToDisapper(spinner);
	}
	
	
}
