package framework.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndFlow {

	@Test
	public void TestEndTotEnd {

		String prod = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.id("userEmail")).sendKeys("shaikhanaamq@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Zavi@123");
		driver.findElement(By.id("login")).click();

		List<WebElement> itemlist = driver.findElements(By.cssSelector("[class='card-body']"));// partial element to
																								// find multiple element
		WebElement text = itemlist.stream(). // Convert to stream
				filter(product -> product.findElement(By.cssSelector("b")). // In the first elemnt find the text
						getText().equals(prod))
				.// if text found
				findFirst().orElse(null);// select first if not select null

		text.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-star-inserted"))); // OR
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		System.out.println(driver.findElement(By.id("toast-container")).getText());

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart"));
		WebElement addedItem = cartItems.stream()
				.filter(s -> s.findElement(By.cssSelector("[class='cartSection'] h3")).getText().equals(prod))
				.findFirst().orElse(null);
		System.out.println(addedItem.findElement(By.cssSelector("h3")).getText());

		// If only need to vrify
		boolean match = cartItems.stream()
				.anyMatch(s -> s.findElement(By.cssSelector("[class='cartSection'] h3")).getText().equals(prod));
		Assert.assertTrue(match);

		driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']//button")).click();

		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> country = driver
				.findElements(By.xpath("//*[@class='ta-results list-group ng-star-inserted']//span"));

		country.stream().filter(s -> s.getText().trim().equals("India")).findFirst().ifPresent(s -> s.click());
		driver.findElement(By.cssSelector(".action__submit ")).click();

	}
}
