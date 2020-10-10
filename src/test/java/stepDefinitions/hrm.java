package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class hrm {
	WebDriver driver;
	WebDriverWait wait;
	Alert alert;

	/*************** Creating a Job Vacancy *****************/

	@Given("user login to the OrangeHRM Page$")
	public void user_login_to_the_OrangeHRM_Page() {

		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectpath+"/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://alchemy.hguy.co/orangehrm");
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		driver.findElement(By.id("btnLogin")).click();

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

	}

	@When("^user Navigate to vacancies menu from Recruitment page$")
	public void user_Navigate_to_vacancies_menu_from_Recruitment_page() throws Exception {

		driver.findElement(By.xpath("//b[contains(text(),'Recruitment')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Then("user adds a Job Vacancy$")
	public void user_adds_a_Job_Vacancy() throws Exception {

		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		Select jobTitle = new Select(driver.findElement(By.xpath("//select[@id='addJobVacancy_jobTitle']")));
		jobTitle.selectByVisibleText("Java Developer");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='addJobVacancy_name']")).sendKeys("JobPosting010");
		driver.findElement(By.xpath("//input[@id='addJobVacancy_hiringManager']")).sendKeys("Test Employee");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();	
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

	}

	@And("user verifies the added vacancy and close browser$")
	public void user_verifies_the_added_vacancy() throws Exception {

		driver.findElement(By.xpath("//b[contains(text(),'Recruitment')]")).click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Select jobposting = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
		jobposting.selectByVisibleText("Java Developer");
		driver.findElement(By.id("btnSrch")).click();
		Boolean present=driver.findElement(By.xpath("//a[text()='Senior Java Architect-Test']")).isDisplayed();
		Assert.assertTrue(present);

		driver.close();

	}

	/************** Adding a candidate for recruitment  ***************/


	@Given("user login to the OrangeHRM Page1$")
	public void user_login_to_the_OrangeHRM_Page1() {

		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectpath+"/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://alchemy.hguy.co/orangehrm");
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		driver.findElement(By.id("btnLogin")).click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}


	@When("^user Navigate to Recruitment page$")
	public void user_Navigate_to_Recruitment_page() throws InterruptedException {

		driver.findElement(By.id("menu_recruitment_viewRecruitmentModule")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@id=\"menu_recruitment_viewCandidates\"]")).click();
	}

	@Then("^user adds a candidate$")
	public void user_adds_a_candidate() throws Exception {

		driver.findElement(By.id("menu_recruitment_viewRecruitmentModule")).click();
		driver.findElement(By.xpath("//b[text()='Recruitment']")).click();

		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.xpath("//input[@id='addCandidate_firstName']")).sendKeys("Sowjanya");
		driver.findElement(By.xpath("//input[@id='addCandidate_lastName']")).sendKeys("Sunkavalli");
		driver.findElement(By.xpath("//input[@id='addCandidate_email']")).sendKeys("test@gmail.com");
		driver.findElement(By.xpath("//input[@id='addCandidate_resume']")).sendKeys("C:\\Users\\SowjanyaSunkavalli\\Cucumber_project\\cucumber_test\\src\\test\\resources\\file\\cucumber.txt");
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		Thread.sleep(3000);
	}

	@And("^user verifies added candidate and close browser$")
	public void user_verifies_added_candidate() throws Exception {
		
		driver.findElement(By.id("menu_recruitment_viewRecruitmentModule")).click();
		Thread.sleep(3000);

		WebElement name = driver.findElement(By.xpath("//input[@id='candidateSearch_candidateName']"));
		name.sendKeys("Sowjanya Sunkavalli");
		name.sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		driver.findElement(By.id("btnSrch")).click();
		driver.getPageSource().contains("Sowjanya Sunkavalli");
		
		driver.close();
		
	}

	/***************  Add multiple employees  ******************/

	@Given("user login to the OrangeHRM Page2$")
	public void user_login_to_the_OrangeHRM_Page2() {

		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectpath+"/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://alchemy.hguy.co/orangehrm");
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		driver.findElement(By.id("btnLogin")).click();

		String hrmTitle=driver.getTitle();
		assertEquals("OrangeHRM", hrmTitle);

	}



	@When("^user navigates to PIM page$")
	public void user_Navigate_to_PIM_page() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		Thread.sleep(5000);

	}


	@Then("user adds a employee with details \"(.*)\", \"(.*)\"$")
	public void user_adds_a_employee_with_details(String FirstName, String LastName) throws Exception {

		driver.findElement(By.id("btnAdd")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@id='chkLogin']")).click();
		Thread.sleep(3000);

	}

	@And("user enters further details \"(.*)\", \"(.*)\"$")
	public void user_enters_further_details(String UserName, String Status) throws Exception {

		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(UserName);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		Select state = new Select(driver.findElement(By.xpath("//select[@id='status']")));
		state.selectByVisibleText(Status);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		Thread.sleep(3000);

	}

	@Then("user verifies the created employee \"(.*)\", \"(.*)\" and close browser$")
	public void user_verifies_the_created_employee(String FirstName, String LastName) throws Exception {

		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).clear();
		driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys(FirstName+" "+LastName);
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
		Thread.sleep(3000);
		
		driver.getPageSource().contains("UserL01");
		/*
		 * List<WebElement> empList =
		 * driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr/td[3]/a"));
		 * for(WebElement emp: empList) { if ((emp.getText().equals(newFName))){
		 * System.out.println("Employee Found"); break; } else {
		 * System.out.println("Created Employee not Exist"); } }
		 */

		driver.close();
		

	}

	/******************  Creating multiple vacancies  *******************/

	@Given("user login to the OrangeHRM Page3$")
	public void user_login_to_the_OrangeHRM_Page3() {

		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectpath+"/src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://alchemy.hguy.co/orangehrm");
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		driver.findElement(By.id("btnLogin")).click();

		String hrmTitle=driver.getTitle();
		assertEquals("OrangeHRM", hrmTitle);

	}

	@When("^user Navigate to vacancies menu from Recruitment page2$")
	public void user_Navigate_to_vacancies_menu_from_Recruitment_page2() throws Exception {

		driver.findElement(By.id("menu_recruitment_viewRecruitmentModule")).click();
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
	}



	@Then("^user adds a candidate with details \"(.*)\", \"(.*)\", \"(.*)\"$")
	public void  user_adds_a_candidate_with_details(String NewVName, String NewHManager, String newJTitle) throws Exception {


		driver.findElement(By.id("btnAdd")).click();
		Thread.sleep(5000);
		Select jobTitle = new Select(driver.findElement(By.xpath("//select[@id='addJobVacancy_jobTitle']")));
		jobTitle.selectByVisibleText(newJTitle);
		driver.findElement(By.id("addJobVacancy_name")).sendKeys(NewVName);
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(NewHManager);
		driver.findElement(By.id("btnSave")).click();	
		Thread.sleep(3000);
	}

	@And("^user verifies added candidate with details \"(.*)\", \"(.*)\", \"(.*)\" and close browser$")
	public void user_verifies_added_candidate_with_details(String NewVName, String NewHManager, String newJTitle) throws Exception {


		driver.findElement(By.id("menu_recruitment_viewRecruitmentModule")).click();
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
		Thread.sleep(5000);
		Select vacancySearchHiringMan = new Select(driver.findElement(By.id("vacancySearch_hiringManager")));
		vacancySearchHiringMan.selectByVisibleText(NewHManager);
		driver.findElement(By.id("btnSrch")).click();

		

		driver.close();
		
	}




}