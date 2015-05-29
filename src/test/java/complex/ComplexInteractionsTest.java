package complex;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import helpers.Helper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComplexInteractionsTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://the-internet.herokuapp.com/";
    private static final String VIEW_PROFILE_REF = "View profile";
    private static final String DEFAULT_OPTION = "Please select an option";

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void hoverTest() throws InterruptedException {
        driver.findElement(By.linkText("Hovers")).click();
        List<WebElement> images = driver.findElements(By.cssSelector(".figure"));
        Assert.assertEquals(Helper.getVisibleElements(driver, By.cssSelector(".figcaption")).size(), 0);
        Actions actions = new Actions(driver);
        for(int i = 0; i < images.size();) {
            Thread.sleep(2000);
            actions.moveToElement(images.get(i)).perform();
            Assert.assertEquals(Helper.getVisibleElements(driver, By.cssSelector(".figcaption")).size(), 1);
            Assert.assertTrue(images.get(i).findElement(By.tagName("h5")).isDisplayed());
            WebElement profileReference = images.get(i).findElement(By.linkText(VIEW_PROFILE_REF));
            Assert.assertTrue(profileReference.isDisplayed());
            Assert.assertEquals(profileReference.getAttribute("href"), BASE_URL + "users/" + ++i);
        }
    }

    @Test
    public void keyStrokeTest() throws InterruptedException {
        final int firstLetter = (int) 'a';
        final int lastLetter = (int) 'z';
        driver.findElement(By.linkText("Key Presses")).click();
        Actions actions = new Actions(driver);
        for (int i = firstLetter; i <= lastLetter; i++) {
            Thread.sleep(1000);
            char currentLetter = (char)i;
            actions.sendKeys(String.valueOf(currentLetter)).perform();
            Assert.assertEquals(driver.findElement(By.id("result")).getText().toLowerCase(), "you entered: " + currentLetter);
        }
    }

    @Test
    public void contextClickTest() throws InterruptedException {
       driver.findElement(By.linkText("Context Menu")).click();
    }

    @Test
    public void dragNDropTest() throws InterruptedException {
        driver.findElement(By.linkText("Drag and Drop")).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("column-a")), 10, 10).perform();
        Thread.sleep(500);
        actions.clickAndHold().perform();
        Thread.sleep(500);
        actions.moveToElement(driver.findElement(By.id("column-b"))).perform();
        Thread.sleep(500);
        actions.release().perform();
//        actions.dragAndDrop(driver.findElement(By.id("column-a")), driver.findElement(By.id("column-b")))
//                .perform();
        Thread.sleep(5000);
    }

    public void checkboxTest() {
        driver.findElement(By.linkText("Checkboxes")).click();
    }

    @Test
    public void dropdownCustomTest() {
        driver.findElement(By.linkText("Dropdown")).click();
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Assert.assertEquals(Helper.getFirstSelectedOption(dropdown).getText(), DEFAULT_OPTION);
        Helper.selectByIndex(dropdown, 1);
        Assert.assertEquals(Helper.getFirstSelectedOption(dropdown).getText(), "Option 1");
        Helper.selectByText(dropdown, "Option 2");
        Assert.assertEquals(Helper.getFirstSelectedOption(dropdown).getText(), "Option 2");
        Helper.selectByValue(dropdown, "1");
        Assert.assertEquals(Helper.getFirstSelectedOption(dropdown).getText(), "Option 1");
    }

    @Test
    public void dropdownClassicTest() {
        driver.findElement(By.linkText("Dropdown")).click();
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select drop = new Select(dropdown);
        Assert.assertEquals(Helper.getFirstSelectedOption(dropdown).getText(), DEFAULT_OPTION);
        drop.selectByIndex(1);
        Assert.assertEquals(Helper.getFirstSelectedOption(dropdown).getText(), "Option 1");
        drop.selectByValue("2");
        Assert.assertEquals(Helper.getFirstSelectedOption(dropdown).getText(), "Option 2");
        drop.selectByVisibleText("Option 1");
        Assert.assertEquals(Helper.getFirstSelectedOption(dropdown).getText(), "Option 1");
    }
}
