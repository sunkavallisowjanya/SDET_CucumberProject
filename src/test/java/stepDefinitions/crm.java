package stepDefinitions;

import java.util.List;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class crm {
	WebDriver driver;
	WebDriverWait wait;
	Alert alert;
	

	/*** Counting no.of Dashlets ***/

	@Given("^user is on the Alchemy CRM website$")
	public void user_is_on_the_Alchemy_CRM_website() throws Throwable {

		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectpath + "/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://alchemy.hguy.co/crm/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("bigbutton")).click();

	}

	@When("^user count the dashlets$")
	public void user_count_the_dashlets() {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		List<WebElement> dashletsList = driver.findElements(By.xpath("//td[@class='dashlet-title']/h3/span[2]"));

		int dashletsCount = dashletsList.size();
		System.out.println("Count of dashlets : " + dashletsCount);

	}

	@Then("^user prints all dashlet titles and close browser$")
	public void user_prints_all_dashlet_titles() {

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		List<WebElement> dashletsList = driver.findElements(By.xpath("//td[@class='dashlet-title']/h3/span[2]"));

		int dashletsCount = dashletsList.size();

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		for (int i = 1; i <= dashletsCount; i++) {

			String text = driver.findElement(By.xpath("(//td[@class='dashlet-title']/h3/span[2])[" + i + "]"))
					.getText();
			System.out.println(text);

		}

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.close();

	}

	/***** Creating Lead using parameterization ***/

	@Given("^user is on the Alchemy CRM website1$")
	public void user_is_on_the_Alchemy_CRM_website1() throws Throwable {
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectpath + "/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://alchemy.hguy.co/crm/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("bigbutton")).click();

	}

	@When("^user navigate to the lead$")
	public void user_navigate_to_the_lead() {

		Actions actions = new Actions(driver);
		WebElement salesMenu = driver.findElement(By.id("grouptab_0"));
		actions.moveToElement(salesMenu).perform();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("moduleTab_9_Leads")).click();

	}

	@Then("^user creates new lead with the following details \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\"$")
	public void user_creates_new_lead(String newSalutation, String newFirstName, String newLastName, String newPhone)
			throws Throwable {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='actionmenulink'][contains(text(),'Create Lead')])[1]")).click();

		Select salutation = new Select(driver.findElement(By.id("salutation")));
		salutation.selectByVisibleText(newSalutation);

		driver.findElement(By.id("first_name")).sendKeys(newFirstName);
		driver.findElement(By.id("last_name")).sendKeys(newLastName);
		driver.findElement(By.id("phone_mobile")).sendKeys(newPhone);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("(//input[@id='SAVE'][@value='Save'])[2]")).click();
	}

	@And("^user navigates to view leads and close browser$")
	public void user_navigates_to_view_leads() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("(//div[@class='actionmenulink'][contains(text(),'View Leads')])[1]")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.close();

	}

	/********** Schedule a meeting and invite members **********/

	@Given("^user is on the Alchemy CRM website2$")
	public void user_is_on_the_Alchemy_CRM_website2() throws Throwable {
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectpath + "/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://alchemy.hguy.co/crm/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("bigbutton")).click();

	}

	@When("^user navigates to schedule meeting$")
	public void user_navigates_to_schedule_meeting() throws Throwable {
		
		Actions actions = new Actions(driver);
		WebElement activitiesMenu=driver.findElement(By.id("grouptab_3"));
		actions.moveToElement(activitiesMenu).perform();
		driver.findElement(By.id("moduleTab_9_Meetings")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='actionmenulink'][contains(text(),'Schedule Meeting')]")).click();


	}

	@Then("^user enter details for meeting \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\"$")
	public void enter_new_meeting_details(String Subject, String StartDate, String EndDate, String Inv1, String Inv2, String Inv3) {

		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(Subject);
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
    	driver.findElement(By.xpath("//input[@id='date_start_date']")).clear();
    	driver.findElement(By.xpath("//input[@id='date_start_date']")).sendKeys(StartDate);
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
    	driver.findElement(By.xpath("//input[@id='date_end_date']")).clear();
    	driver.findElement(By.xpath("//input[@id='date_end_date']")).sendKeys(EndDate);
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
    	driver.findElement(By.xpath("//input[@name=\"search_first_name\" and @id=\"search_first_name\"]")).sendKeys(Inv1);
		driver.findElement(By.id("invitees_search")).click();
		driver.findElement(By.id("invitees_add_1")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@name=\"search_first_name\" and @id=\"search_first_name\"]")).clear();
		driver.findElement(By.xpath("//input[@id='search_first_name']")).sendKeys(Inv2);
		driver.findElement(By.id("invitees_search")).click();
		driver.findElement(By.id("invitees_add_1")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		driver.findElement(By.xpath("//input[@name=\"search_first_name\" and @id=\"search_first_name\"]")).clear();
		driver.findElement(By.xpath("//input[@id='search_first_name']")).sendKeys(Inv3);
		driver.findElement(By.id("invitees_search")).click();
		driver.findElement(By.id("invitees_add_1")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	
		driver.findElement(By.xpath("//body/div[@id='bootstrap-container']/div[@id='content']/form[@id='EditView']/div[4]/input[1]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@And("^user verifies the created meeting with \"(.*)\" and close browser$")
	public void user_verifies_the_created_meeting(String Subject) {
		driver.findElement(By.xpath("//div[@class='actionmenulink'][contains(text(),'View Meetings')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("(//a[@title='Meetings']/span[2][contains(text(),'"+newSubject+"')])[2]")).click();
		boolean isCreatedMeetingExists = driver.findElement(By.xpath("//a[contains(text(),'"+Subject+"')]")).isDisplayed();
		if(isCreatedMeetingExists) {
			System.out.println("Meeting Created");
		}
		else{
			System.out.println("Meeting not created");
		}

		driver.close();

	}

	/************** Create a Product ****************/

	@Given("^user is on the Alchemy CRM website3$")
	public void user_is_on_the_Alchemy_CRM_website3() throws Throwable {
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectpath + "/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://alchemy.hguy.co/crm/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("bigbutton")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@When("^user navigates to products$")
	public void user_navigates_to_products() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions actions = new Actions(driver);

    	
    	actions.moveToElement(driver.findElement(By.xpath("//a[text()='All']"))).build().perform();
    	driver.findElement(By.xpath("//a[text()='Products']")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.findElement(By.xpath("//div[contains(text(),'Products')]")).click();
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    	//create product
    	driver.findElement(By.xpath("//div[@class='actionmenulink'][contains(text(),'Create Product')]")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		

	}

	@Then("user enter details for new product \"(.*)\" and \"(.*)\"$")
	public void user_enter_details_for_new_product_and(String Name, String Price) {
		
		driver.findElement(By.id("name")).sendKeys(Name);
		driver.findElement(By.id("price")).sendKeys(Price);
		driver.findElement(By.xpath("(//input[@id='SAVE'][@value='Save'])[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@And("user verifies created record \"(.*)\" and close browser$")
	public void user_verifies_created_record(String newName) {
		
		//verifying
		
		driver.findElement(By.xpath("//div[@class='actionmenulink'][contains(text(),'View Products')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean isCreatedProductExists = driver.findElement(By.xpath("//a[contains(text(),'NewProduct01')]")).isDisplayed();
		if(isCreatedProductExists) {
			System.out.println("Product Created");
		}
		else{
			System.out.println("Product not created");
		}
		
		driver.close();

	}

}