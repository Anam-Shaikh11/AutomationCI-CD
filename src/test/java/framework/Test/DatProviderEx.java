	package framework.Test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test

import testComponents.BaseTest;.BaseTest;.BaseTest;.BaseTest;BaseTest;;



public class DatProviderEx extends BaseTest {

	PageObject.OrderSuccessful os;
	/*
	 * String prod= "ZARA COAT 3"; String userName="shaikhanaamq@gmail.com"; String
	 * password="Zavi@123";
	 */
	String order = "683d9db481a20695305a2981";
	PageObject.OrderHistory history;

	@Test(dataProvider = "getData")
	public void ecommerceApp(String userName, String password, String prod) throws IOException {

		PageObject.Login_Page page = launchApplication();
		PageObject.ProductCat cat = page.login_app(userName, password);
		cat.addProductToCart(prod);

		PageObject.CartPage cp = cat.goToCartPage();
		cp.getCartProductList(prod);
		cp.matchProduct(prod);
		PageObject.CheckOutPage ck = cp.clickCheckOut();
		ck.inputCountryField("ind");
		ck.getCountryListAndClick();
		PageObject.OrderSuccessful os = ck.clickSubmit();
		order = os.getOrderRef();
		history = os.clickOrdersPageFromOrderRefPage();
	}

	/*
	 * @Test//(dependsOnMethods = "ecommerceApp") public void TestOrderIsPresent()
	 * throws IOException { Login_Page page=launchApplication(); ProductCat
	 * cat=page.login_app(userName, password); history=cat.goToOrderPage();
	 * 
	 * boolean bol = history.getOrderIsPresent(order); String orderref =
	 * history.getOrderRefMatc(order); Assert.assertEquals(order, orderref);
	 * Assert.assertTrue(bol); }
	 */

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "shaikhanaamq@gmail.com", "Zavi@123", "ZARA COAT 3" },
				{ "AnnieMany@gmail.com", "Annie@123", "ADIDAS ORIGINAL" } };
	}
}
