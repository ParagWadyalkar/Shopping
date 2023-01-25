package RahulShetty.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RahulShetty.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extend = ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
	//This is called test entry
	//So, here we are running parallel at the same time submit our test also executed parallelly. So, this is overridden the test variable 
	//what already error validation holding so another test came and here that test also executed this method
		test = extend.createTest(result.getMethod().getMethodName());
		extendTest.set(test);// it will assign one unique id (ErrorValidationTest)->
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extendTest.get().log(Status.PASS, "Test Passed");
		
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
		
		extendTest.get().fail(result.getThrowable());
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//screenShot, attach to the report
		try {
			String filepath = getScreenshot(result.getMethod().getMethodName(), driver);
			extendTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extend.flush();
		
	}

}
