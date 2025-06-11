package testComponents;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports ext;
	
	public static ExtentReports getExtentReporterObject()
	{
		
		String path= System.getProperty("user.dir")+"\\reports\\html.file";
		 ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		 reporter.config().setDocumentTitle("Web Automation");
		 reporter.config().setReportName("Ecommerce Application");
		 
		
		 ext=new ExtentReports();
		ext.attachReporter(reporter);
		ext.setSystemInfo("Tester", "Anam");
		ext.createTest(path);
		return ext;
		
	}
}
