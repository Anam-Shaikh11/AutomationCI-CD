
package framework.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.CartPage;
import PageObject.CheckOutPage;
import PageObject.Login_Page;
import PageObject.OrderHistory;
import PageObject.OrderSuccessful;
import PageObject.ProductCat;
import SeleniumFrameWork.TestComponents.BaseTest;

public class Using_Json_For_HashMap extends BaseTest {

	OrderSuccessful os;

	String fileName = "\\src\\test\\resources\\SeleniumFrameWork\\Resources\\getDataUsingJason.json";
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
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println("Method name is: " + methodName);
		//getScreenShot(methodName);
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> getData = getJsonData(fileName);
		return new Object[][] { { getData.get(0) }, { getData.get(1) } };
	}

}
