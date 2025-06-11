package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.framework.AbstractComponents.Utilities;

public class OrderSuccessful extends Utilities{

	WebDriver driver;
	public OrderSuccessful(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr[class='ng-star-inserted'] td[class='em-spacer-1']")
	WebElement orderref;
	
	@FindBy(css="label[routerlink='/dashboard/myorders']")
	WebElement OrdersPageLink;
	
	
	public String getOrderRef()
	{
		return orderref.getText();
	}
	
	public OrderHistory clickOrdersPageFromOrderRefPage()
	{
		OrdersPageLink.click();
		OrderHistory his=new OrderHistory(driver);
		return his;
	}
	

}
