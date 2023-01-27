package RahulShetty.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShetty.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver intializeDriver() throws IOException {

		Properties pro = new Properties();
		FileInputStream fil = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/RahulShetty/resources/GlobalData.properties");

		pro.load(fil);
		String brow = pro.getProperty("browser");

		if (brow.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "D:\\edg\\chromedriver_win32\\chromedriver.exe");
			option.addArguments("headless");	
			if(brow.contains("headless")) {
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,900));
			}
		} else if (brow.equalsIgnoreCase("fireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (brow.equalsIgnoreCase("Edge")) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}

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
