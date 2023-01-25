package RahulShetty.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RahulShetty.AbstractComponent.AbstractComponenet;

public class LandingPage extends AbstractComponenet {
	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}

	@FindBy(id="userEmail")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement password;

	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='ngx-toastr']")
	WebElement error;
	
	public ProductCatalogue loginApplication(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		submit.click();
		ProductCatalogue productCatogue = new ProductCatalogue(driver);
		return productCatogue;
	
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(error);
		return error.getText();
		
	}	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}


}
