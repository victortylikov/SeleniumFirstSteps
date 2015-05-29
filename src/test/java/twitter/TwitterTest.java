package twitter;

import com.sun.javafx.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TwitterTest {
    private WebDriver driver;
    private static final String POPUP_MESSAGE = "The username and password you entered did not " +
            "match our records. Please double-check and try again.";
    private static final String INVALID_CREDENTIALS_URL = "https://twitter.com/login/error";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://twitter.com/");
    }

    @AfterMethod
    public void teardown() {
        //driver.quit();
    }

    @Test
    public void negativeLoginTest() throws InterruptedException {
        WebElement loginForm = driver.findElement(By.cssSelector("#front-container form.signin"));
        loginForm.findElement(By.id("signin-email")).click();
        loginForm.findElement(By.id("signin-email")).sendKeys("shg");
        loginForm.findElement(By.id("signin-password")).click();
        loginForm.findElement(By.id("signin-password")).sendKeys("sad");
        loginForm.findElement(By.cssSelector("input[name='remember_me']")).click();
        loginForm.findElement(By.cssSelector("button.submit")).click();
        WebDriverWait waiter = new WebDriverWait(driver, 5000);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.message-text")));
        Assert.assertTrue(driver.getCurrentUrl().contains(INVALID_CREDENTIALS_URL), "Actual url: " + driver.getCurrentUrl());
        WebElement message = driver.findElement(By.cssSelector("span.message-text"));
        Assert.assertTrue(message.isDisplayed(), "Message isn't displayed");
        Assert.assertEquals(Utils.convertUnicode(message.getText()),POPUP_MESSAGE);
    }
}
