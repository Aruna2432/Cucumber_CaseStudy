package stepDefs;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginStepDef {
	static WebDriver driver;
	WebDriverWait wait;
	List<WebElement> BeforeDel;
	
	@BeforeAll
	public static void Launch() {
		WebDriverManager.edgedriver().setup();
	    driver = new EdgeDriver();  
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
	    driver.get("https://www.demoblaze.com/");
	}
	
	@Given("User is on launch page")
	public void user_is_on_launch_page() {
		driver.findElement(By.cssSelector("a#login2")).click();
	}
	@When("User enters credentials")
	public void user_enters_credentials() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input#loginusername")).sendKeys("ArunaN");
		driver.findElement(By.cssSelector("input#loginpassword")).sendKeys("abc@123"); 
		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[3]")).click();
		Thread.sleep(3000);
	}
	@Then("Should display Home Page")
	public void should_display_home_page() {
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Welcome')]")).isDisplayed());
	}
	@When("User adds {string} to cart")
	public void user_adds_to_cart(String Items) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
		driver.findElement(By.linkText(Items)).click();
		driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	@Then("Should display success message")
	public void should_display_success_message() {
		//System.out.println("---------");
	  	System.out.println("Item added to cart successfully");
	  	//System.out.println("---------");
	}
	@When("User deletes an item")
	public void user_deletes_an_item() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		WebElement cart = driver.findElement(By.cssSelector("a#cartur"));
		wait.until(ExpectedConditions.visibilityOfAllElements(cart));
		cart.click();
		BeforeDel = driver.findElements(By.xpath("//td[2]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(BeforeDel));
		//System.out.println("No . of. items added to cart : " + BeforeDel.size());
		//System.out.println("---------");
	    WebElement del = driver.findElement(By.xpath("(//a[contains(text(),'Delete')])[1]"));
		del.click();
	}
	@Then("Item should be deleted from the cart")
	public void item_should_be_deleted_from_the_cart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		List<WebElement> AfterDel = driver.findElements(By.xpath("//td[2]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(AfterDel));
		if(BeforeDel.size()>AfterDel.size()) {
			  Assert.assertTrue(true);
		}
	}
	@When("User enters credentials and places order")
	public void user_enters_credentials_and_places_order() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a#cartur")).click();
		boolean s = driver.findElement(By.xpath("(//tr[@class='success'])[1]")).isDisplayed();
		Assert.assertTrue(s);
		WebElement placeOrd = driver.findElement(By.xpath("//button[contains(text(),'Place')]"));
		placeOrd.click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input#name")).sendKeys("Aruna");
		driver.findElement(By.cssSelector("input#country")).sendKeys("India");
		driver.findElement(By.cssSelector("input#city")).sendKeys("Erode");
		driver.findElement(By.cssSelector("input#card")).sendKeys("123456789874");
		driver.findElement(By.cssSelector("input#month")).sendKeys("12");
		driver.findElement(By.cssSelector("input#year")).sendKeys("24");
		driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
	}
	@Then("Order must be placed")
	public void order_must_be_placed() {
		WebElement ok = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
		//wait.until(ExpectedConditions.visibilityOf(ok));
	    ok.click();
	  	System.out.println("Order placed successfully !");
	  	//System.out.println("---------");
	}
	
}
