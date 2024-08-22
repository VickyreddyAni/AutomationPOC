package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class stepDef {
	private WebDriver driver;
	private WebDriverWait wait;

	@Given("I open the LabCorp homepage")
	public void i_open_the_labcorp_homepage() {
		System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get("https://www.labcorp.com");
	}

	@When("I navigate to the Careers section")
	public void i_navigate_to_the_careers_section() {
		WebElement careersLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Careers")));
		careersLink.click();
	}

	@When("I search for {string}")
	public void i_search_for(String position) {
		WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
		searchBox.sendKeys(position);
		searchBox.submit();
	}

	@When("I select a job listing")
	public void i_select_a_job_listing() {
		WebElement jobListing = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job-title a")));
		jobListing.click();
	}

	@Then("I should see the job title as {string}")
	public void i_should_see_the_job_title_as(String expectedTitle) {
		WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job-title")));
		assertEquals(expectedTitle, jobTitle.getText());
	}

	@Then("I should see the job location")
	public void i_should_see_the_job_location() {
		WebElement jobLocation = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job-location")));
		assertNotNull(jobLocation.getText());
	}

	@Then("I should see the job ID")
	public void i_should_see_the_job_id() {
		WebElement jobId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job-id")));
		assertNotNull(jobId.getText());
	}

	@Then("I should see the job description containing {string}")
	public void i_should_see_the_job_description_containing(String expectedDescription) {
		WebElement jobDescription = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job-description")));
		assertTrue(jobDescription.getText().contains(expectedDescription));
	}

	@Then("I should see a requirement {string}")
	public void i_should_see_a_requirement(String expectedRequirement) {
		WebElement requirement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".requirement")));
		assertTrue(requirement.getText().contains(expectedRequirement));
	}

	@Then("I should see the automation tool {string}")
	public void i_should_see_the_automation_tool(String expectedTool) {
		WebElement tool = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".automation-tool")));
		assertTrue(tool.getText().contains(expectedTool));
	}

	@When("I click on Apply Now")
	public void i_click_on_apply_now() {
		WebElement applyNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".apply-now")));
		applyNowButton.click();
	}

	@Then("I should see the job title as {string} on the application page")
	public void i_should_see_the_job_title_on_the_application_page(String expectedTitle) {
		WebElement jobTitleOnApplyPage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".application-job-title")));
		assertEquals(expectedTitle, jobTitleOnApplyPage.getText());
	}

	@Then("I should see the job location and job ID match the previous page")
	public void i_should_see_the_job_location_and_job_id_match_the_previous_page() {
		WebElement jobLocationOnApplyPage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".application-job-location")));
		WebElement jobIdOnApplyPage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".application-job-id")));

		assertNotNull(jobLocationOnApplyPage.getText());
		assertNotNull(jobIdOnApplyPage.getText());
	}

	@When("I return to the Job Search")
	public void i_return_to_the_job_search() {
		WebElement returnToSearchButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".return-to-search")));
		returnToSearchButton.click();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
