package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class loginStepDefinition {


    public WebDriver driver;
    @Given("user is on the Luma webpage")
    public void user_is_on_the_luma_webpage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://magento.softwaretestingboard.com/");
    }
    @When("user navigates to the signin page")
    public void user_navigates_to_the_signin_page() {
        driver.findElement(By.linkText("Sign In")).click();
        Assert.assertEquals(driver.findElement(By.className("page-title")).getText(),"Customer Login");
        Assert.assertEquals(driver.getTitle(), "Customer Login");

    }
    @When("logs in without entering the values")
    public void logs_in_without_entering_the_values() {
        driver.findElement(By.id("send2")).click();

    }
    @Then("login failed")
    public void login_failed() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(),"Customer Login");

    }


    @Then("username and password required error message pops up")
    public void username_and_password_required_error_message_pops_up() {
        String error_msg = driver.findElement(By.className("message-error")).getText();
        System.out.println(error_msg);
        Assert.assertEquals(error_msg,"A login and a password are required.");
        driver.quit();
    }

    @When("enters invalid username and password")
    public void enters_invalid_username_and_password() {
        driver.findElement(By.id("email")).sendKeys("abc11@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Hello388f");
        driver.findElement(By.id("send2")).click();
    }
    @Then("error message pops up")
    public void error_message_pops_up() throws InterruptedException {
        Thread.sleep(2000);
        String msg = driver.findElement(By.className("message-error")).getText();
        System.out.println(msg);
        Assert.assertEquals(msg,
                "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
        driver.quit();

    }

    @When("enter valid login details")
    public void enter_valid_login_details() {
        driver.findElement(By.id("email")).sendKeys("abc1@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Hello0988f");
        driver.findElement(By.id("send2")).click();

    }
    @Then("user logged in successfully")
    public void user_logged_in_successfully() throws InterruptedException {
        Thread.sleep(2000);
        String welcomeMsg = driver.findElement(By.className("logged-in")).getText();
        String welcomeName = welcomeMsg.split(",")[1].trim();
        System.out.println(welcomeName);
        Assert.assertEquals(welcomeName, "Nivedita Joshi!");
        driver.quit();
    }





}
