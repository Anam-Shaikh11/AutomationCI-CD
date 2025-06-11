package testComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listen_Listeners extends BaseTest implements ITestListener {
	ExtentReports extent=ExtentReporterNG.getExtentReporterObject();
	 ExtentTest test;
	String path;
	//WebDriver driver;
	ThreadLocal<ExtentTest> th= new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
    	test=extent.createTest(result.getMethod().getMethodName());
    	th.set(test); // to set dedicated Thread id to each testcase
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        th.get().log(Status.PASS, "Test passed: ");
    }

    @Override
    public void onTestFailure(ITestResult result) {
 
	try {
		driver = (WebDriver)result.getTestClass().getRealClass().getField("driver")
				.get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		th.get().fail(result.getThrowable());// which thread id is asking info
    	
    	try {
			path=getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	th.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
        System.out.println("Test failed: " + result.getName());
        
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
    	extent.flush();
        System.out.println("Test suite finished: " + context.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // optional
    }

}
