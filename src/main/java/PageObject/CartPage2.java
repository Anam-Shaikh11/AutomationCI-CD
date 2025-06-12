package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.framework.AbstractComponents.Utilities;

public class CartPage2 extends Utilities{

	
	WebDriver driver;
	public CartPage2(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart")
	List<WebElement> productList;
	
	@FindBy(xpath="//div[@class='subtotal cf ng-star-inserted']//button")
	WebElement btnCheckout;
	

	public WebElement getCartProductList(String prod)
	{
		WebElement addedItem = productList.stream().filter(s->s.findElement(By.
				  cssSelector("[class='cartSection'] h3")).getText().equals(prod)).findFirst().
				  orElse(null);
		System.out.println(addedItem.findElement(By.cssSelector("h3")).getText());
		return addedItem;
	}
	
	
	public boolean matchProduct(String prod)
	{
		return productList.stream().anyMatch(s->s.findElement(By.
				  cssSelector("[class='cartSection'] h3")).getText().equals(prod));
				
	}
	
	public CheckOutPage clickCheckOut()
	{
		
		btnCheckout.click();
		CheckOutPage ck=new CheckOutPage(driver);
		return ck;
	}
	
	
}
