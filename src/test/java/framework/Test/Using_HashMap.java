
package framework.Test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.CartPage;
import PageObject.CheckOutPage;
import PageObject.Login_Page;
import PageObject.OrderHistory;
import PageObject.OrderSuccessful;
import PageObject.ProductCat;
import testComponents.BaseTest;

public class Using_HashMap extends BaseTest {

	OrderSuccessful os;
	/*
	 * String prod= "ZARA COAT 3"; String userName="shaikhanaamq@gmail.com"; String
	 * password="Zavi@123";
	 */
	String order = "";
	OrderHistory history;

	@Test(dataProvider = "getData")
	public void ecommerceApp(HashMap<String, String> input) throws IOException {

		Login_Page page = launchApplication();
		ProductCat cat = page.login_app(input.get("email"), input.get("pass"));
		cat.addProductToCart(input.get("prod"));

		CartPage cp = cat.goToCartPage();
		cp.getCartProductList(input.get("prod"));
		cp.matchProduct(input.get("prod"));
		CheckOutPage ck = cp.clickCheckOut();
		ck.inputCountryField("ind");
		ck.getCountryListAndClick();
		OrderSuccessful os = ck.clickSubmit();
		order = os.getOrderRef();
		history = os.clickOrdersPageFromOrderRefPage();
	}

	@DataProvider
	public Object[][] getData() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "shaikhanaamq@gmail.com");
		map.put("pass", "Zavi@123");
		map.put("prod", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "AnnieMany@gmail.com");
		map1.put("pass", "Annie@123");
		map1.put("prod", "ADIDAS ORIGINAL");

		/*
		 * return new Object[][] { {"shaikhanaamq@gmail.com", "Zavi@123",
		 * "ZARA COAT 3"}, {"AnnieMany@gmail.com", "Annie@123", "ADIDAS ORIGINAL"} };
		 */
		return new Object[][] { { map }, { map1 } };
	}
}
