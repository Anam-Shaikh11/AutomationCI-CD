package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameWork.framework.AbstractComponents.Utilities;

public class Login_Page extends Utilities{
	
	WebDriver driver;
	
	public Login_Page(WebDriver driver)
	{
		
		super(driver);
		this.driver=driver;
		
		//For Find by
		PageFactory.initElements(driver, this);
		
							//argument, class driver
	}
	/*WebElement userName=driver.findElement(By.id("userEmail"));//.sendKeys("shaikhanaamq@gmail.com");
	WebElement password=driver.findElement(By.id("userPassword"));//.sendKeys("Zavi@123");
	WebElement btnLogin=driver.findElement(By.id("login"));//.click();
	*/
	//Using Page Factory
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement password;

	@FindBy(id="login")
	WebElement btnLogin;
	
	
	@FindBy(css="[class*=flyInOut]")
	WebElement error;
	
	public ProductCat login_app(String email, String pass )
	{
		userName.sendKeys(email);
		password.sendKeys(pass);
		btnLogin.click();
		
		ProductCat cat=new ProductCat(driver);
		return cat;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String errorOccured()
	{
		explictiWaitInvisiblityOfElementLocated(error);
		
		return error.getText();
	}
}
