import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
//        System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://comaqa.by/");
        driver.findElement(By.linkText("Events")).click();
        Thread.sleep(10000);
        driver.quit();
    }
}
