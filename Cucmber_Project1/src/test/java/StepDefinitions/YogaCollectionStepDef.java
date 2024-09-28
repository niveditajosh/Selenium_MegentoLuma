package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class YogaCollectionStepDef {

    WebDriver driver;
    int cartCount = 0;

    @Given("the user is on the {string} page" )
    public void the_user_is_on_the_page(String PageName) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/" );
       // driver.findElement(By.linkText("Sign In" )).click();
        //driver.findElement(By.id("email" )).sendKeys("abc1@gmail.com" );
        //driver.findElement(By.id("pass" )).sendKeys("Hello0988f" );
        //driver.findElement(By.id("send2" )).click();
        driver.findElement(By.xpath("//span[contains(text(),\"" + PageName + "\")]" )).click();
        Assert.assertEquals(driver.findElement(By.id("page-title-heading" )).getText(), "New Luma Yoga Collection" );


    }

    @When("the user selects the desired product by its name" )
    public void the_user_selects_the_desired_product_by_its_name() {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item" ));


        WebElement product = products.stream().filter(p ->
                        p.findElement(By.cssSelector(".product-item-link" )).getText()
                                .equalsIgnoreCase("Fiona Fitness Short" ))
                .findFirst().orElse(null);

        assert product != null;
        product.click();
    }

    @When("the user selects the {string}, {string}" )
    public void the_user_selects_the(String string, String string2) {
        List<WebElement> sizes = driver.findElements(By.xpath("//div[@option-type='0']" ));

        WebElement size = sizes.stream().filter(s -> s.getText().equals("29" )).findFirst().orElse(null);
        assert size != null;
        size.click();

        List<WebElement> colours = driver.findElements(By.xpath("//div[@option-type='1']" ));

        WebElement colour = colours.stream().filter(c ->
                c.getAttribute("aria-label" ).toString().equalsIgnoreCase("Black" )).
                findFirst().orElse(null);

        assert colour != null;
        colour.click();

    }

    @When("the user adds the product to the cart" )
    public void the_user_adds_the_product_to_the_cart() throws InterruptedException {
        driver.findElement(By.id("product-addtocart-button")).click();

        //verify added msg
        Thread.sleep(6000);

    }

    @Then("the product should be added to the cart successfully" )
    public void the_product_should_be_added_to_the_cart_successfully() {
        cartCount = Integer.parseInt(driver.findElement(By.className("counter-number")).getText());
        String SuccessMsg = driver.findElement(By.xpath("//div[contains(@class, 'message-success')]/div")).getText();
        System.out.println(SuccessMsg);
        String productName = (SuccessMsg.split("added")[1].trim()).split("to")[0].trim();
        Assert.assertEquals(productName, "Fiona Fitness Short");
        cartCount++;

    }

    @When("the user tries to add the product to the cart without selecting size, colour")
    public void the_user_tries_to_add_the_product_to_the_cart_without_selecting_size_colour() {
        driver.findElement(By.id("product-addtocart-button")).click();

    }

    @Then("the product should not be added to the cart" )
    public void the_product_should_not_be_added_to_the_cart() {
        int newCartCount = Integer.parseInt(driver.findElement(By.className("counter-number")).getText());
        Assert.assertEquals(newCartCount,cartCount);

    }

    @Then("error message should be prompted" )
    public void error_message_should_be_prompted() {

        WebElement SuccessMsg = driver.findElement(By.xpath("//div[contains(@class, 'message-success')]/div"));
        Assert.assertFalse(SuccessMsg.isDisplayed(),"Error: Element is added to cart without selecting siz and colour");
    }

}
