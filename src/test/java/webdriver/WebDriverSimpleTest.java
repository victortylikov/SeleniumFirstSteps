package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WebDriverSimpleTest {
    private WebDriver driver;

    @BeforeTest
    public void configure() {
//        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
//        System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer.exe");
    }

    @BeforeMethod
    public void setup() {
        //  arrange
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get("http://comaqa.by/"); // driver.navigate().to("http://comaqa.by/");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void webElementTest() throws InterruptedException {
        // act
        WebElement eventsTab = driver.findElement(By.linkText("Events"));

        // assert
        Assert.assertTrue(eventsTab.isDisplayed(), "Events tab is not displayed");
        Assert.assertTrue(eventsTab.isEnabled(), "Events tab is not enabled");
        Assert.assertEquals(eventsTab.getText(), "Events", "text is not expected");
        Assert.assertEquals(eventsTab.getTagName(), "a", "tag is not expected");
        Assert.assertEquals(eventsTab.getLocation().toString(), "(174, 784)", "location is not expected");
        Assert.assertEquals(eventsTab.getSize().toString(), "(37, 14)", "size is not expected");
        Assert.assertEquals(eventsTab.getAttribute("href"), "http://comaqa.by/en/category/news-en/events-en/",
                "link value is not expected");
        Assert.assertEquals(eventsTab.getCssValue("color"), "rgba(102, 102, 102, 1)", "color is not expected");
    }

    @Test
    public void eventsTabTest() throws InterruptedException {
        // act
        By eventsTabLocator = By.linkText("Events");
        WebElement eventsTab = driver.findElement(eventsTabLocator);
        eventsTab.click();
        Assert.assertEquals(driver.getTitle(), "Events | COMAQA.BY – QA Automation Community Belarus", "title differs");
        Assert.assertEquals(driver.getCurrentUrl(), "http://comaqa.by/en/category/news-en/events-en/", "url differs");
        Assert.assertEquals(driver.findElement(By.cssSelector("#site-navigation li.current-menu-item")).getText(),
                driver.findElement(eventsTabLocator).getText().toUpperCase(), "Events tab is not active");
        // assert
    }

    @Test
    public void backTest() {
        String homePageTitle = driver.getTitle();
        driver.findElement(By.linkText("Events")).click();
        String eventsTitle = driver.getTitle();
        Assert.assertNotEquals(eventsTitle, homePageTitle);
        driver.navigate().back();
        Assert.assertEquals(homePageTitle, driver.getTitle());
    }

    @Test
    public void forwardTest() {
        driver.findElement(By.linkText("Events")).click();
        String eventsTitle = driver.getTitle();
        driver.navigate().back();
        String homePageTitle = driver.getTitle();
        Assert.assertNotEquals(eventsTitle, homePageTitle);
        driver.navigate().forward();
        Assert.assertEquals(eventsTitle, driver.getTitle());
    }

    @Test(expectedExceptions = StaleElementReferenceException.class)
    public void refreshTest() {
        WebElement eventsTab = driver.findElement(By.linkText("Events"));
        String homePageTitle = driver.getTitle();
        driver.navigate().refresh();
        Assert.assertEquals(homePageTitle, driver.getTitle());
        eventsTab.click();
    }
}
