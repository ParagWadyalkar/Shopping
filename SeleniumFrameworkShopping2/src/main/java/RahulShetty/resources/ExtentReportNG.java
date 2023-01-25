package RahulShetty.resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public static ExtentReports getReportObject() {
		String test = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter path = new ExtentSparkReporter(test);
		path.config().setDocumentTitle("Test Company");
		path.config().setReportName("Web automation result");
		
		ExtentReports extend = new ExtentReports();
		extend.setSystemInfo("Tester","Parag W");
		extend.attachReporter(path);
		extend.flush();
		extend.createTest(test);	
		
		return extend;
			
	}
	
}
