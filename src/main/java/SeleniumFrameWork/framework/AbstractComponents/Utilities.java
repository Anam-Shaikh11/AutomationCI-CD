package SeleniumFrameWork.framework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObject.CartPage;
import PageObject.OrderHistory;

public class Utilities {
WebDriver driver;
	public Utilities(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement btnCart;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement btnOrder;
	
	public void explictiWaitVisiblityOfElementLocated(By findBy)
	{
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void explictiWaitInvisiblityOfElementLocated(By findBy)
	{
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	

	public void explictiWaitInvisiblityOfElementLocated(WebElement element)
	{
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void explictiWaitVisiblityOfElementLocated(WebElement element)
	{
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(element));
	}
	public CartPage goToCartPage()
	{
		btnCart.click();
		CartPage cp= new CartPage(driver);
		return cp;
		
	}
	
	public OrderHistory goToOrderPage()
	{
		btnOrder.click();
		OrderHistory cp= new OrderHistory(driver);
		return cp;
		
	}
}
