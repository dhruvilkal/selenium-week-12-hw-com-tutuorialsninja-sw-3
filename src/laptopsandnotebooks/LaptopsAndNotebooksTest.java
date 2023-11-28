package laptopsandnotebooks;
/**
 * 1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
 *      1.1 Mouse hover on Laptops & Notebooks Tab.and click
 *      1.2 Click on “Show All Laptops & Notebooks”
 *      1.3 Select Sort By "Price (High > Low)"
 *      1.4 Verify the Product price will arrange in High to Low order.
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    // Declare baseUrl
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    // open browser
    @Before
    public void setUP() {
        openBrowser(baseUrl);
    }

    @Test
    //Method name verifyProductsPriceDisplayHighToLowSuccessfully

    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException{

        // Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverOnElement(By.linkText("Laptops & Notebooks"));
        Thread.sleep(2000);

        // Click on “Show All Laptops & Notebooks”
        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));

        //Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropdown(By.id("input-sort"), "Price (High > Low)");

        //Verify the Product price will arrange in High to Low order.
        List<WebElement> price = driver.findElements(By.xpath("//span[@class = 'price-tax']"));
        List<Double> prices = new ArrayList<>();
        for (WebElement element : price) {
            String priceText = element.getText().replaceAll("[E,x,T,a,x,:,$]", "").replace(",", "");
            Double priceValue = Double.parseDouble(priceText);
            prices.add(priceValue);
        }

        Boolean isSorted = true;
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            System.out.println("Prices are in high to low order.");
        } else {
            System.out.println("Prices are not sorted.");
        }

    }

    @Test
    //Method name verifyThatUserPlaceOrderSuccessfully
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException{

        //Mouse hover on £Pound Sterling and click
        mouseHoverOnElement(By.xpath("//span[normalize-space()='Currency']"));
        mouseHoverOnElementAndClick(By.xpath("//span[normalize-space()='Currency']"));
        mouseHoverOnElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));
        Thread.sleep(2000);

        // Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverOnElement(By.linkText("Laptops & Notebooks"));
        Thread.sleep(2000);

        // Click on “Show All Laptops & Notebooks”
        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));
        Thread.sleep(2000);

        //Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropdown(By.id("input-sort"), "Price (High > Low)");

        //4 Select Product “MacBook
        clickOnElement(By.linkText("MacBook"));

        //Verify the text “MacBook”
        String eMacbook = "MacBook";
        String aMacbook = getTextFromElement(By.xpath("//h1[contains(text(),'MacBook')]"));
        Assert.assertEquals(eMacbook, aMacbook);

        //Click on ‘Add To Cart’ button
        clickOnElement(By.id("button-cart"));
        Thread.sleep(2000);

        // Verify the message “Success: You have added MacBook to your shopping cart!”
        String eSuccessMsg = "Success: You have added MacBook to your shopping cart!\n" +
                "×";
        String aSuccessMsg = driver.findElement(By.xpath("//div[@class = 'alert alert-success alert-dismissible']")).getText();
        Assert.assertEquals(eSuccessMsg, aSuccessMsg);

        //Click on link “shopping cart” display into success message
        try {
            clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        } catch (ElementClickInterceptedException e) {
            clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        }

        //Verify the text "Shopping Cart"
        String eText = "Shopping Cart  (0.00kg)";
        String aText = driver.findElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).getText();
        Assert.assertEquals(eText, aText);

        //Verify the Product name "MacBook"
        driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]")).clear();
        sendTextToElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]"), "2");
        clickOnElement(By.xpath("//tr[1]//button[@data-original-title = 'Update']"));


        //Verify the Total £737.45
        String eSuccessMsg1 = "£737.45";
        String aSuccessMsg1 = driver.findElement(By.xpath("//td[text() = '£737.45']")).getText();
        Assert.assertEquals(eSuccessMsg1, aSuccessMsg1);

        //Click on “Checkout” button
        clickOnElement(By.xpath("//a[contains(text(),'Checkout')]"));

        //Verify the text “Checkout”
        String eCheckout = "Checkout";
        String aCheckout = getTextFromElement(By.xpath("//h1[contains(text(),'Checkout')]"));
        Assert.assertEquals(eCheckout, aCheckout);

        //Verify the Text “New Customer”
        String eNewCustomer = "New Customer";
        String aNewCustomer = getTextFromElement(By.xpath("//h2[contains(text(),'New Customer')]"));
        Assert.assertEquals(eCheckout, aCheckout);

        //Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value = 'guest']"));

        //Click on “Continue” tab
        clickOnElement(By.id("button-account"));

        //Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"), "Manish");
        sendTextToElement(By.id("input-payment-lastname"), "Kakadiya");
        sendTextToElement(By.id("input-payment-email"), "manish@gmail.com");
        sendTextToElement(By.id("input-payment-telephone"), "01234567891");
        sendTextToElement(By.id("input-payment-password"), "Manish@123");
        sendTextToElement(By.id("Password Confirm"), "Manish@123");
        sendTextToElement(By.id("input-payment-address-1"), "ac");
        sendTextToElement(By.id("input-payment-city"), "London");
        sendTextToElement(By.id("input-payment-postcode"), "V23 365");
        selectByVisibleTextFromDropdown(By.id("input-payment-zone"), "London");
        clickOnElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[4]/div[1]/input[1]"));

        //Click on “Continue” Button
        clickOnElement(By.id("button-guest"));

        //Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@class = 'form-control']"), "Successful");

        //Check the Terms & Conditions check box
        clickOnElement(By.xpath("//input[@type='checkbox']"));

        //Click on “Continue” button
        clickOnElement(By.id("button-payment-method"));

        // Verify the message “Warning: Payment method required!”
        String eWarning = "Warning: Payment method required!\n" +
                "×";
        String aWarning = driver.findElement(By.xpath("//div[text()='Warning: Payment method required!']")).getText();
        Assert.assertEquals(eWarning, aWarning);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
