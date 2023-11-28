package myaccounts;
/**
 * 1. Create the class MyAccountsTest
 *      1.1 create method with name "selectMyAccountOptions" it has one parameter name "option" of type string
 *      1.2 This method should click on the options whatever name is passed as parameter. (Hint: Handle List of Element and Select options)
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {
    // Declare baseUrl
    String baseUrl = "https://tutorialsninja.com/demo/index.php";


    @Before
    public void setUP() {
        openBrowser(baseUrl);
    }

    public void selectMyAccountOptions(String option) {
        List<WebElement> options = driver.findElements(By.xpath("//ul[@class = 'list-inline']//a"));
        for (WebElement list : options) {
            if (option.equalsIgnoreCase(list.getText())) {
                list.click();
                break;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Register");

        String expected = "Register Account";
        String actual = getTextFromElement(By.xpath("//h1[contains(text(),'Register Account')]"));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Login");

        String expected = "Returning Customer";
        String actual = getTextFromElement(By.xpath("//h2[contains(text(),'Returning Customer')]"));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void VerifyThatUserRegisterAccountSuccessfully() {

        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Register");

        sendTextToElement(By.id("input-firstname"), "Manish");
        sendTextToElement(By.id("input-lastname"), "Kakadiya");
        sendTextToElement(By.id("input-email"), "Manish@gmail.com");
        sendTextToElement(By.id("input-telephone"), "01234567891");
        sendTextToElement(By.id("input-password"), "Manish@321");
        sendTextToElement(By.id("input-confirm"), "Manish@321");
        clickOnElement(By.xpath("//label[@class = 'radio-inline']//input[@value = '1']"));
        clickOnElement(By.xpath("//input[@type = 'checkbox']"));
        clickOnElement(By.xpath("//input[@type = 'submit']"));

        String cMsg = "Your Account Has Been Created!";
        String bMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"));
        Assert.assertEquals(cMsg, bMsg);

        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Logout");

        String eLogout = "Account Logout";
        String aLogout = getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]"));
        Assert.assertEquals(eLogout, aLogout);
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

    }

    @Test
    public void VerifyThatUserLoginAndLogoutSuccessfully() {

        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Login");

        sendTextToElement(By.id("input-email"), "Manish@gmail.com");
        sendTextToElement(By.id("input-password"), "Manish@321");
        clickOnElement(By.xpath("//input[@type = 'submit']"));

        String eAccount = "My Account";
        String aAccount = getTextFromElement(By.xpath("//h2[contains(text(),'My Account')]"));
        Assert.assertEquals(eAccount, aAccount);

        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Logout");

        String eLogout = "Account Logout";
        String aLogout = getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]"));
        Assert.assertEquals(eLogout, aLogout);
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }

    @After
    public void tearDown() {

        closeBrowser();
    }



}
