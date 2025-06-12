package framework.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.CartPage;
import PageObject.CheckOutPage;
import PageObject.Login_Page;
import PageObject.OrderSuccessful;
import PageObject.ProductCat;
import testComponents.BaseTest;

public class TestFlow extends BaseTest {

	PageObject.OrderSuccessful os;
	String prod = "ZARA COAT 3";
	String userName = "shaikhanaamq@gmail.com";
	String password = "Zavi@123";
	String order = "";
	PageObject.OrderHistory history;

	@Test // (enabled = false)
	public void ecommerceApp() throws IOException {

		PageObject.Login_Page page = launchApplication();

		// page.login_app(userName, password);

		// To optimize thepage furthur before
		// Create aobject as last line of previous class last method and return obj
		// example can create ProductCat cat=new ProductCat(driver); and return this obh
		// from page.login_app(userName, password); method
		// then the line would look like

		ProductCat cat = page.login_app(userName, password);

		// Now you can use cat without again creating object

		// ProductCat cat=new ProductCat(driver);
		// List<WebElement> productList = cat.getproductList();
		cat.addProductToCart(prod);

		CartPage cp = cat.goToCartPage();
		cp.getCartProductList(prod);
		cp.matchProduct(prod);
		CheckOutPage ck = cp.clickCheckOut();
		ck.inputCountryField("ind");
		ck.getCountryListAndClick();
		OrderSuccessful os = ck.clickSubmit();
		order = os.getOrderRef();
		history = os.clickOrdersPageFromOrderRefPage();
	}

	@Test  (dependsOnMethods = "ecommerceApp")
	public void TestOrderIsPresent() throws IOException {
		Login_Page page = launchApplication();
		ProductCat cat = page.login_app(userName, password);
		history = cat.goToOrderPage();

		boolean bol = history.getOrderIsPresent(order);
		String orderref = history.getOrderRefMatc(order);
		Assert.assertEquals(order, orderref);
		Assert.assertTrue(bol);
	}
}
