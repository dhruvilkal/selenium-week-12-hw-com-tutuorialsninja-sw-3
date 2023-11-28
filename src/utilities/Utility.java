package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utility extends BaseTest {
    /**
     * This method will click on element
     */
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    /**
     * This method will send text on element
     */
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

//************************* Alert Methods *****************************************************

    /**
     * This method will switch to alert
     */
    public void switchToAlert(){
        driver.switchTo().alert();
    }

    /**
     * This method will accept to alert
     */
    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    // 3 more method

    //*************************** Select Class Methods ***************************************//
    public void selectByValueFromDropDown(By by, int value){
        WebElement dropDown = driver.findElement(by);
        // Create the object of Select Class
        Select select = new Select(dropDown);
        select.selectByIndex(value);
    }

//*************************** Action Methods ***************************************//

    /**
     * This method will use to hover mouse on element
     */
    public void mouseHoverToElement(By by){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }
    //*********************************Order of sorted list******************************//
    public void verifyTheListIsSortedInOrder(By by){
        List actualList = new ArrayList();
        List<WebElement> list = driver.findElements(by);
        for (WebElement e : list){
            String product = e.getText();
            System.out.println(product);
            actualList.add(product);
        }
        List tempList = new ArrayList();
        tempList.addAll(actualList);

        Collections.sort(tempList);
        Assert.assertTrue(actualList.equals(tempList));

    }
    //*********** Action Class ***************//

    public void mouseHoverOnElement(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void mouseHoverOnElementAndClick(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }
    public void selectByVisibleTextFromDropdown (By by ,String text1){
        WebElement dropDown = driver.findElement(by);
        // Create the object of select class
        Select select = new Select(dropDown);
        select.selectByVisibleText(text1);
    }

}
