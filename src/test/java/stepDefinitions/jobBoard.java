package stepDefinitions;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import io.cucumber.java.en.*;

public class jobBoard {

	WebDriver driver; 

	/******* Creating a user ********/

	@Given("^Open a browser$")
	public void Open_Browser() {

		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectpath+"/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://alchemy.hguy.co/jobs/wp-admin");
		driver.manage().window().maximize();
	}

	@Then("^Login to jobs page$")
	public void Login_page() {	

		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("wp-submit")).click();
	}

	@And("^Locate the left hand menu and Click on Menu Item$")
	public void click_on_left_menu() {

		Actions action = new Actions(driver);
		WebElement menu = driver.findElement(By.cssSelector("a.menu-icon-users > div:nth-child(3)"));
		action.moveToElement(menu).build().perform();
		menu.click();

	}

	@And("^Click the Add New button$")
	public void locate_the_Add_New_button() {
		driver.findElement(By.cssSelector(".page-title-action")).click();
		//driver.findElement(By.xpath("//a[@class = 'page-title-action'] and [@text = 'Add New']")).click();
	}

	@Then("^Fill in the details and submit the form$")
	public void form_details() {

		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("JobBoardUser009");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("jobboard009@email.com");
		driver.findElement(By.xpath("//button[contains(text(),'Show password')]")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='pass1']")).clear();
		driver.findElement(By.xpath("//input[@id='pass1']")).sendKeys("TestPwd02@1234$*#123#");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='createusersub']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@And("^Verify user was created and close browser$")
	public void verify_user() {

		driver.findElement(By.xpath("//input[@id='user-search-input']")).sendKeys("jobboard009@email.com");
		driver.findElement(By.xpath("//input[@id='search-submit']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.getPageSource().contains("jobboard009@email.com");

		driver.close();

	}


	/****** searching and Applying for JOb********/


	@Given("^Open a alchemy browser$")
	public void Open_alchemy_site() {

		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectpath+"/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://alchemy.hguy.co/jobs/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


	}
	@Then("^Navigate to Job site$")
	public void navigate_job_site() {
		driver.findElement(By.xpath("//*[@id = 'menu-item-24']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


	}
	@And("^Find the Keywords search input field and change job$")
	public void keywords_search() {

		driver.findElement(By.xpath("//input[@name=\"search_keywords\" and @placeholder=\"Keywords\"]")).sendKeys("Selenium");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='search_submit']//input")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@Then("^Find the filter to show only full time jobs$")
	public void filter_fulltime_jobs() {

		driver.findElement(By.xpath("//input[@id='job_type_freelance']")).click();
		driver.findElement(By.xpath("//input[@id='job_type_internship']")).click();
		driver.findElement(By.xpath("//input[@id='job_type_part-time']")).click();
		driver.findElement(By.xpath("//input[@id='job_type_temporary']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='search_submit']//input")).click();

	}
	@And("^Find the title of job and print and close browser$")
	public void title_of_job() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("(//ul[@class='job_listings']/li/a/div[1]/h3)[1]")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String title = driver.findElement(By.xpath("//h1[@class='entry-title']")).getText();
		System.out.println("Job Title is "+ title);

		driver.findElement(By.xpath("//input[@class='application_button button']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.close();


	}

	/******* Posting a job with parameters ******/


	@Given("Open a browser1")
	public void open_a_broswer1() {
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectpath+"/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://alchemy.hguy.co/jobs/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}



	@Then("^Navigate to post a job page$")
	public void navigate_job_page() {

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//a[@href=\"https://alchemy.hguy.co/jobs/post-a-job/\"]")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@class='button']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("root");
		driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();


	}

	@And("^Enter \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void enter_and(String job, String location, String company) throws Throwable {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='job_title']")).sendKeys(job);
		driver.findElement(By.xpath("//input[@id='job_location']")).sendKeys(location);
		driver.findElement(By.xpath("//input[@id='company_name']")).clear();
		driver.findElement(By.xpath("//input[@id='company_name']")).sendKeys(company);
		Select selection = new Select(driver.findElement(By.xpath("//select[@id='job_type']")));
		selection.selectByValue("2");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		// Switch

		driver.switchTo().frame("job_description_ifr");

		//add description
		driver.findElement(By.xpath("/html/body")).sendKeys("New job posting");

		driver.switchTo().defaultContent();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


	}

	@And("^Click on submit$")
	public void submit_the_form() throws Throwable {

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//body/div[@id='page']/div[@id='content']/div[1]/div[1]/main[1]/article[1]/div[1]/form[1]/p[1]/input[4]")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='job_preview_submit_button']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);



	}

	@Then("^navigate to Job Page$")
	public void gotojobpage() {

		driver.findElement(By.xpath("//a[@href=\"https://alchemy.hguy.co/jobs/post-a-job/\"]")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@SuppressWarnings("deprecation")
	@Then("Confirm {string} Lisiting as shown and close browser")
	public void jobconfirm(String job) {
		/*
		 * driver.findElement(By.cssSelector("#search_keywords")).sendKeys(job);
		 * driver.findElement(By.
		 * cssSelector("#post-7 > div > div > form > div.search_jobs > div.search_submit > input[type=submit]"
		 * )).click();
		 * 
		 * driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); String
		 * jobSave = driver.findElement(By.xpath(
		 * "/html/body/div[2]/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")).
		 * getText(); junit.framework.Assert.assertEquals(job, jobSave);
		 */
		driver.close();
	}




	/********** posting with example table **********/


	@Given("Open a browser2")
	public void open_a_broswer2() {
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectpath+"/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://alchemy.hguy.co/jobs/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}


	@Then("^Navigate to post a job page2$")
	public void go_to_the_Jobs_page() throws Throwable {
		driver.findElement(By.xpath("//a[@href=\"https://alchemy.hguy.co/jobs/post-a-job/\"]")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("root");
		driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();

	}

	@And("^Enter Example \"(.*)\", \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void enter_Example_and(String email, String job, String location, String company) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='job_title']")).sendKeys(job);
		driver.findElement(By.xpath("//input[@id='job_location']")).sendKeys(location);
		driver.findElement(By.xpath("//input[@id='company_name']")).clear();
		driver.findElement(By.xpath("//input[@id='company_name']")).sendKeys(company);

		driver.findElement(By.xpath("//input[@id='application']")).sendKeys(email);
		Select selection = new Select(driver.findElement(By.id("job_type")));
		selection.selectByValue("2");


		// Switch

		driver.switchTo().frame("job_description_ifr");

		//add description
		driver.findElement(By.xpath("/html/body")).sendKeys("New job posting");

		driver.switchTo().defaultContent();



	}

	@And("^Click on submit2$")
	public void submit_the_form2() throws Throwable {

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='submit_job']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='job_preview_submit_button']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@Then("^navigate to Job Page2$")
	public void gotojobpage1() {

		driver.findElement(By.xpath("//*[@id = 'menu-item-24']/a")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@And("^Confirm Lisiting and close browser$")
	public void verification( ) {

		driver.close();
	}
}


