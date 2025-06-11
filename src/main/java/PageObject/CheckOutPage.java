package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.framework.AbstractComponents.Utilities;

public class CheckOutPage extends Utilities{

	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement inputCountry;
	
	
	@FindBy(xpath="//*[@class='ta-results list-group ng-star-inserted']//span")
	List<WebElement> countryList;
	
	
	@FindBy(css=".action__submit ")
	WebElement btnSubmit;
	
	
	public void inputCountryField(String Country)
	{
		explictiWaitVisiblityOfElementLocated(inputCountry);
		inputCountry.sendKeys(Country);
	}
	
	public void getCountryListAndClick()
	{
		countryList.stream().filter(s->s.getText().trim().equals("India")).findFirst().
		  ifPresent(s->s.click());
	}
	
	public OrderSuccessful clickSubmit()
	{
		btnSubmit.click();
		OrderSuccessful ord=new OrderSuccessful(driver);
		return ord;
	}
	
	
}
