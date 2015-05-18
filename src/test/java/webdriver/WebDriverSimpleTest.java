package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebDriverSimpleTest {
    private WebDriver driver;

    @BeforeTest
    public void configure() {
//        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
//        System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer.exe");
    }

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void WebElementTest() throws InterruptedException {
        driver.get("http://comaqa.by/"); // driver.navigate().to("http://comaqa.by/");

        WebElement eventsTab = driver.findElement(By.linkText("Events"));

        Assert.assertTrue(eventsTab.isDisplayed(), "Events tab is not displayed");
        Assert.assertTrue(eventsTab.isEnabled(), "Events tab is not enabled");
        Assert.assertEquals(eventsTab.getText(), "Events", "text is not expected");
        Assert.assertEquals(eventsTab.getTagName(), "a", "tag is not expected");
        Assert.assertEquals(eventsTab.getLocation().toString(), "(174, 784)", "location is not expected");
        Assert.assertEquals(eventsTab.getSize().toString(), "(37, 14)", "size is not expected");
        Assert.assertEquals(eventsTab.getAttribute("href"), "http://comaqa.by/en/category/news-en/events-en/",
                "link value is not expected");
        Assert.assertEquals(eventsTab.getCssValue("color"), "rgba(102, 102, 102, 1)", "color is not expected");
        eventsTab.click();

        Thread.sleep(10000);
    }
}
