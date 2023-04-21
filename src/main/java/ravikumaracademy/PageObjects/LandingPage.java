package ravikumaracademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravikumaracademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(css="#userPassword")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCataloge loginApplication(String Email, String passwordel) {
		email.sendKeys(Email);
		password.sendKeys(passwordel);
		login.click();
		ProductCataloge productcataloge=new ProductCataloge(driver);
		return productcataloge;
		
	}
	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage() {
		waitforElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
