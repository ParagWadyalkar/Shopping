package RahulShetty.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShetty.AbstractComponent.AbstractComponenet;

public class ProductCatalogue extends AbstractComponenet {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By  tostmessage= By.cssSelector("#toast-container");

	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductlist() {
		waitForElementToAppear(productBy);
		return products;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = getProductlist().stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(tostmessage);
		waitForElementToDisappear(spinner);
		
	}
	
	public void goToCheckout() {
		
	}
	


}
