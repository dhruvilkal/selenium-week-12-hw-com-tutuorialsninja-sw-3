package desktops;
/**
 * 1.Test name verifyProductArrangeInAlphaBaticalOrder()
 *      1.1 Mouse hover on Desktops Tab.and click
 *      1.2 Click on “Show All Desktops”
 *      1.3 Select Sort By position "Name: Z to A"
 *      1.4 Verify the Product will arrange in Descending order.
 * 2. Test name verifyProductAddedToShoppingCartSuccessFully()
 *      2.1 Mouse hover on Currency Dropdown and click
 *      2.2 Mouse hover on £Pound Sterling and click
 *      2.3 Mouse hover on Desktops Tab.
 *      2.4 Click on “Show All Desktops”
 *      2.5 Select Sort By position "Name: A to Z"
 *      2.6 Select product “HP LP3065”
 *      2.7 Verify the Text "HP LP3065"
 *      2.8 Select Delivery Date "2023-11-27"
 *      2.9.Enter Qty "1” using Select class.
 *      2.10 Click on “Add to Cart” button
 *      2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
 *      2.12 Click on link “shopping cart” display into success message
 *      2.13 Verify the text "Shopping Cart"
 *      2.14 Verify the Product name "HP LP3065"
 *      2.15 Verify the Delivery Date "2023-11-27"
 *      2.16 Verify the Model "Product21"
 *      2.17 Verify the Todat "£74.73"
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class Desktops extends Utility {
    // Declare baseUrl
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    // open browser
    @Before
    public void setUP() {
        openBrowser(baseUrl);
    }

    @Test


    public void verifyProductArrangeInAlphaBalticOrder() throws InterruptedException {

        //Mouse hover on Desktops Tab.and click
        mouseHoverOnElement(By.linkText("Desktops"));

        //Click on “Show All Desktops”
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Show AllDesktops']"));

        // Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='input-sort']"),"Name (Z - A)");
        Thread.sleep(2000);

        List<WebElement> items = driver.findElements(By.xpath("//div[@class = 'caption']//a"));

        //verify descending order
        boolean isDescending = false;
        for (int i = 0; i < items.size() - 1; i++) {
            String currentElement = items.get(i).getText();
            String nextElement = items.get(i + 1).getText();
            if (currentElement.compareTo(nextElement) > 0) {
                isDescending = true;
                break;
            }
        }

        if (isDescending) {
            System.out.println("Elements are in descending order.");
        } else {
            System.out.println("Elements are not in descending order.");
        }
    }

    @Test


    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException{

        //Mouse hover on Currency Dropdown and click
        mouseHoverOnElement(By.xpath("//span[normalize-space()='Currency']"));
        mouseHoverOnElementAndClick(By.xpath("//span[normalize-space()='Currency']"));

        //Mouse hover on £Pound Sterling and click
        mouseHoverOnElement(By.xpath("//button[normalize-space()='£Pound Sterling']"));
        mouseHoverOnElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));

        //Mouse hover on Desktops Tab.and click
        mouseHoverOnElement(By.linkText("Desktops"));

        //Click on “Show All Desktops”
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Show AllDesktops']"));

        //Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='input-sort']"),"Name (A - Z)");

        // Select product “HP LP3065”
        List<WebElement> productName = driver.findElements(By.cssSelector(".product-layout"));
        for (WebElement element : productName){
            if (element.getText().equalsIgnoreCase("HP LP3065")){
                element.click();
                break;
            }
        }

        //Select product “HP LP3065”
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='HP LP3065']"));

        //Verify the Text "HP LP3065"
        String expectedText = "HP LP3065";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']"));
        Assert.assertEquals(expectedText,actualText);

        //Select Delivery Date "2023-11-27"
        String year = "2023";
        String month = "November";
        String date = "21";

        mouseHoverOnElementAndClick(By.xpath("//div[@class='input-group date']//button[@type='button']"));
        while (true){
            String monthYear = getTextFromElement(By.xpath("//th[@class = 'picker-switch']"));
            System.out.println(monthYear);
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)){
                break;
            } else {
                mouseHoverOnElementAndClick(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }
        // select the date
        List<WebElement> allDates = driver.findElements(By.xpath("//table[@class = 'table-condensed']//tr/td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)){
                dt.click();
                break;
            }
        }

        //Enter Qty "1” using Select class
        driver.findElement(By.id("input-quantity")).clear();
        sendTextToElement(By.id("input-quantity"), "1");
        clickOnElement(By.id("button-cart"));
        Thread.sleep(2000);

        //Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        String eSuccessMsg = "Success: You have added HP LP3065 to your shopping cart!\n" +
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
        String eText = "Shopping Cart  (1.00kg)";
        String aText = driver.findElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).getText();
        Assert.assertEquals(eText, aText);

        //Verify the Product name "HP LP3065"
        String eProductName = "HP LP3065";
        String aProductName = getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        Assert.assertEquals(eProductName, aProductName);

        //Verify the Delivery Date "2023-11-27"
        String eDate = "2023-11-27";
        WebElement date1 = driver.findElement(By.xpath("//input[@type= 'text']"));
        String aDate = date1.getAttribute("option[225]");
//        String aDate = getTextFromElement(By.xpath("//input[@type= 'text']"));
        Assert.assertEquals(eDate, aDate);

        //Verify the Model "Product21"
        String eModel = "Product 21";
        String aModel = getTextFromElement(By.xpath("//td[contains(text(),'Product 21')]"));
        Assert.assertEquals(eModel, aModel);

        //Verify the Total "£74.73"
        String eTotal = "£74.73";
        String aTotal = getTextFromElement(By.xpath("//tbody/tr[1]/td[6]"));
        Assert.assertEquals(eTotal, aTotal);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }



}
