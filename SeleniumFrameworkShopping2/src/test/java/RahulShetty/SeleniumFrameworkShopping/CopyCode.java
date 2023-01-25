package RahulShetty.SeleniumFrameworkShopping;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RahulShetty.pageObjects.CartPage;
import RahulShetty.pageObjects.LandingPage;
import RahulShetty.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CopyCode {
	static WebDriver driver;
//center
	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
//s
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");

//		LandingPage landingPage = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");

		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream().filter(product->

		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		//ng-animating

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);

		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		String productname="iphone 13 pro";
//		driver.get("https://rahulshettyacademy.com/client");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
//		driver.findElement(By.id("login")).click();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ADIDAS ORIGINAL")).findFirst().orElse(null);
//		WebElement prod1 =products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
//		
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		Thread.sleep(2000);
//		prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//		
//		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
//		//[class=\"cart\"] h3
////		List<WebElement> cartproduct = driver.findElements(By.cssSelector(".cartSection h3"));
////		Boolean b = cartproduct.stream().anyMatch(ct->ct.getText().equalsIgnoreCase(productname));
////		Assert.assertTrue(b);
//		Thread.sleep(2000);
//		Actions country = new Actions(driver);
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,1000)");
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".totalRow button")));
//		
//		driver.findElement(By.xpath("//div[@class=\"subtotal cf ng-star-inserted\"]/ul/li[3]/button")).click();
//	//	driver.findElement(By.cssSelector(".totalRow button")).click();
//		
//		
//		country.sendKeys(driver.findElement(By.cssSelector("[placeholder*=\"Select Country\"]")), "india").build().perform();	
////		driver.findElement(By.cssSelector("[class*=\"btnn\"]")).click();
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class*=\"ta-results\"]")));
//		
//		driver.findElement(By.cssSelector("[class*='ta-item']:nth-of-type(2)")).click();
//		driver.findElement(By.cssSelector("[class*=\"actions\"]")).click();

//		String productName = "ZARA COAT 3";
//		WebDriverManager.chromedriver().setup();
//
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
//
//		landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
//
//		ProductCatalogue productCatogue = new ProductCatalogue(driver);
//		List<WebElement> products = productCatogue.getProductlist();
//
//		productCatogue.addProductToCart(productName);
//		productCatogue.goToCartPage();
//		
//		CartPage cartpage = new CartPage(driver);
//		Boolean match = cartpage.verifyProductDisplay(productName);
//		Assert.assertTrue(match);

//		driver.findElement(By.cssSelector(".totalRow button")).click();
//
//		Actions a = new Actions(driver);
//
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//		driver.findElement(By.cssSelector(".action__submit")).click();
//
//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//
//		driver.close();

	}

}
