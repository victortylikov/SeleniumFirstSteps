package internet;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AuthenticationTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://the-internet.herokuapp.com/login";

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        driver.get(BASE_URL);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @DataProvider
    public Object[][] getCreds() {
        return new Object[][] {
                {"user1", "pass1", true},
                {"user2", "pass2", true},
                {"user3", "pass3", true},
        };
    }

    @Test(dataProvider = "getCreds")
    public void loginTest(String userName, String pass, boolean isValid) {
        if(isValid) {
            Assert.assertTrue(true);
        } else {

        }
    }
}
