package framework.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameWork.TestComponents.BaseTest;
import SeleniumFrameWork.TestComponents.ReTryAnalyzer;

public class ErrorValidation extends BaseTest {

	@Test(groups = { "error_handling" },retryAnalyzer = ReTryAnalyzer.class)
	public void ecommerceApp() throws IOException {

		String prod = "ZARA COAT 3";
		String userName = "shaikhanaamq@gmail.com";
		String password = "Zavi23";

		PageObject.Login_Page page = launchApplication();

		page.login_app(userName, password);
		Assert.assertEquals("Incorrect email or password.", page.errorOccured());

		teardown();

	}
}
