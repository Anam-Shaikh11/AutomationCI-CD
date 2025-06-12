package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.framework.AbstractComponents.Utilities;

public class ProductCat2 extends Utilities {
WebDriver driver;
	public ProductCat2(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	 @FindBy(css=".card-body")
	 List<WebElement> itemlist;
	 
	 
	 
	 By product=By.cssSelector("[class='card-body']");
	 By addToCart=By.cssSelector(".card-body button:last-of-type");
	 By spinner=By.cssSelector("ng-star-inserted");
	 public List<WebElement> getproductList()
	 {
		 
		 //returning the element list we took from findby method
		 explictiWaitVisiblityOfElementLocated(product);
		 return itemlist;
	 }
	 
	 public WebElement getProductByName(String ProdName)
	 {
		 return getproductList().stream().   // Convert to stream
					filter(product->product.findElement(By.cssSelector("b")). // In the first elemnt find the text
					getText().equals(ProdName)).// if text found
					findFirst().orElse(null);// select first if not select null
	 }
	 
	 public void addProductToCart(String ProdName)
	 {
		 getProductByName(ProdName).findElement(addToCart).isDisplayed();
		 System.out.println(getProductByName(ProdName).findElement(addToCart).isDisplayed());
		 getProductByName(ProdName).findElement(addToCart).click();
		 explictiWaitInvisiblityOfElementLocated(spinner);
	 }
}
