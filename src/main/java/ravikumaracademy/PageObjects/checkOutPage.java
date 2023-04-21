package ravikumaracademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravikumaracademy.AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents{

	WebDriver driver;
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(css="a[class*='submit']")
	WebElement submit;
	
	@FindBy(css="span.ng-star-inserted")
	List<WebElement> countries;
	
	By results=By.cssSelector(".ta-results");
	
	public void typeCountry(String countryname) {
		Actions a=new Actions(driver);
		a.sendKeys(country, countryname).build().perform();
	}
	
	public void selectCountry(String countryName) {
		waitforElementToAppear(results);
		for(WebElement country:countries) {
			if(	country.getText().equals(countryName)) {
				country.click();
				break;
			}
		}
		
	}
	
	public confirmationPage submitOrder() {
		submit.click();
		confirmationPage confirmationpage=new confirmationPage(driver);
		return confirmationpage;
	}
}
