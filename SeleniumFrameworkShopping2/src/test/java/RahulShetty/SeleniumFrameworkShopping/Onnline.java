package RahulShetty.SeleniumFrameworkShopping;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShetty.pageObjects.CartPage;
import RahulShetty.pageObjects.CheckOutPage;
import RahulShetty.pageObjects.LandingPage;
import RahulShetty.pageObjects.OrderPage;
import RahulShetty.pageObjects.ProductCatalogue;
import RahulShetty.pageObjects.confirmationPage;
import RahulShetty.resources.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
	
public class Onnline extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups="Purchase")
	public void submitOrder(HashMap<String,String> op) throws IOException, InterruptedException {

		ProductCatalogue productCatogue = landingpage.loginApplication(op.get("user"), op.get("pass"));
		List<WebElement> products = productCatogue.getProductlist();

		productCatogue.addProductToCart(productName);
		CartPage cartpage = productCatogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(op.get("productName"));
		Assert.assertTrue(match);
		
		CheckOutPage check = cartpage.goToCheckout();
		check.SelectCountry("india");
		check.scrollPage();
		Thread.sleep(2000);
		confirmationPage confirmation = check.submitOrder();

		String confirmMessage = confirmation.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistory() {								
		ProductCatalogue productCatogue = landingpage.loginApplication("shetty@gmail.com", "Iamking@000");
		OrderPage order = productCatogue.goToOrderPage();
	
		Assert.assertTrue(order.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//RahulShetty//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
	
	// This screenShot works when user get error
	public String getScreenShot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File  file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		//or
		//return file;
		
	}
	

}
