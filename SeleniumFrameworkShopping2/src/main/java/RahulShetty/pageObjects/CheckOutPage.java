package RahulShetty.pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShetty.AbstractComponent.AbstractComponenet;

public class CheckOutPage extends AbstractComponenet{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	By result = By.cssSelector(".ta-results");
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;

	@FindBy(xpath="//div[@class=\"payment__shipping\"]/div[2]/div/div[2]/a")
	WebElement submit;

	public void SelectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitForElementToAppear(result);
		SelectCountry.click();
	}
	
	public confirmationPage submitOrder() {
		scrollPage();
		submit.click();
		return new confirmationPage(driver);
	}
	
	
	
	
	
	
}
