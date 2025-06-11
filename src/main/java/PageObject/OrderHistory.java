package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.framework.AbstractComponents.Utilities;

public class OrderHistory extends Utilities{
	WebDriver driver;
	public OrderHistory(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr[class='ng-star-inserted'] th")
	List<WebElement> orderList;
	
	public boolean getOrderIsPresent(String order)
	{
		return orderList.stream().anyMatch(s->s.getText().equalsIgnoreCase(order));
		
	}
	
	public String getOrderRefMatc(String order)
	{
		WebElement text = orderList.stream().filter(s->s.//findElement(By.cssSelector("th")).
				getText().equalsIgnoreCase(order))
				.findFirst().orElse(null);
		return text.getText();
	}

	
}
