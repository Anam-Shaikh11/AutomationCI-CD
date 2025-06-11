package testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReTryAnalyzer implements IRetryAnalyzer {
	int count=0;
			int maxtry=1;
	@Override
	public boolean retry(ITestResult arg0) {
		
		if(count<maxtry)
		{
			count++;
		return true;
		}
		return false;
	}

}
