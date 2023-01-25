package RahulShetty.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import RahulShetty.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver intializeDriver() throws IOException {

		Properties pro = new Properties();
		FileInputStream fil = new FileInputStream(
				System.getProperty("user.dir") + "src//main//java//Shop//resource//GlobalData.properties");

		pro.load(fil);
		String brow = System.getProperty("browser") !=null ? System.getProperty("browser") : pro.getProperty("browser");
		//String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//	pro.getProperty("browser");

		if (brow.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (brow.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D://edg//geckodriver-v0.32.0-win32//geckodriver.exe");
//			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (brow.equalsIgnoreCase("Edge")) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}

	@BeforeMethod
	public LandingPage lunchApplication() throws IOException {
		driver = intializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;

	}

	@AfterMethod
	public void teardown() {
		driver.close();
		
	}
	
	
	
}
