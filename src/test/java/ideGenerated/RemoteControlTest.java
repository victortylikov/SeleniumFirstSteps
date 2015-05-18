package ideGenerated;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoteControlTest {
    private Selenium selenium;

    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://comaqa.by/");
        selenium.start();
    }

    @Test
    public void testRemote() throws Exception {
        selenium.open("/en/");
        selenium.click("link=Events");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Our events");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Education");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=About us");
        selenium.waitForPageToLoad("30000");
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}