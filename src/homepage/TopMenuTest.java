package homepage;
/**
 * 1. verifyUserShouldNavigateToDesktopsPageSuccessfully()
 *          1.1 Mouse hover on “Desktops” Tab and click
 *          1.2 call selectMenu method and pass the menu = “Show All Desktops”
 *          1.3 Verify the text ‘Desktops’
 * 2. verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully()
 *          2.1 Mouse hover on “Laptops & Notebooks” Tab and click
 *          2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
 *          2.3 Verify the text ‘Laptops & Notebooks’
 * 3. verifyUserShouldNavigateToComponentsPageSuccessfully()
 *          3.1 Mouse hover on “Components” Tab and click
 *          3.2 call selectMenu method and pass the menu = “Show All Components”
 *          3.3 Verify the text ‘Components’
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    // Declare baseUrl
    String baseUrl = "https://tutorialsninja.com/demo/index.php";


    @Before
    public void setUP() {
        openBrowser(baseUrl);
    }

    // Create method with name "selectMenu" and parameter
    public void selectMenu(String menu) {

        if (menu == "Desktops") {
            mouseHoverOnElement(By.linkText("Desktops"));
            mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Show AllDesktops']"));
            //Get title
            String title = driver.getTitle();
            //validate page navigation by assert title
            Assert.assertEquals("title not matched: ", "Desktops", title);

        } else if (menu == "Laptops & Notebooks") {
            mouseHoverOnElement(By.linkText("Laptops & Notebooks"));
            mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));
            //Get title
            String title = driver.getTitle();
            //validate page navigation by assert title
            Assert.assertEquals("Laptops & Notebooks", title);
        } else if (menu == "Components") {
            mouseHoverOnElement(By.linkText("Components"));
            mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Show AllComponents']"));
            //Get title
            String title = driver.getTitle();
            //validate page navigation by assert title
            Assert.assertEquals("Components", title);
        } else {
            System.out.println("Wrong parameter pass");
        }


    }

    @Test
    //Method name verifyUserShouldNavigateToDesktopsPageSuccessfully
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        selectMenu("Desktops");
    }

    @Test
    // Method name verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        selectMenu("Laptops & Notebooks");
    }

    @Test
    // Method name verifyUserShouldNavigateToComponentsPageSuccessfully
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        selectMenu("Components");
    }

    @After
    public void tearDown() {
        closeBrowser();

    }


}
