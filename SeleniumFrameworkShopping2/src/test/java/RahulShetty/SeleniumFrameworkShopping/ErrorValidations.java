package RahulShetty.SeleniumFrameworkShopping;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShetty.TestComponents.Retry;
import RahulShetty.pageObjects.CartPage;
import RahulShetty.pageObjects.ProductCatalogue;
import RahulShetty.resources.BaseTest;
	
public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		landingpage.loginApplication("RahulShetty@gmail.com", "Iamkg000");
		Assert.assertEquals("Incorrect email 12or password.", landingpage.getErrorMessage());
		
	}
	@Test(retryAnalyzer=Retry.class)
	public void ProductErrorVaidation() throws IOException, InterruptedException {

		String productName = "COAT 3";
		ProductCatalogue productCatogue = landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products = productCatogue.getProductlist();

		productCatogue.addProductToCart(productName);
		CartPage cartpage = productCatogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

	}
}
