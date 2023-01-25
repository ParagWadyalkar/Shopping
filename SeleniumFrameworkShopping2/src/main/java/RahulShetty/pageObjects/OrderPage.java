package RahulShetty.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShetty.AbstractComponent.AbstractComponenet;

public class OrderPage extends AbstractComponenet {
	public WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tbody td:nth-child(3)")
	List<WebElement> productlist;

	public boolean VerifyOrderDisplay(String productName) {
		boolean match = productlist.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}
}
