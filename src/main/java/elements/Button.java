package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by user on 05.06.2015.
 */
public class Button extends ElementBase{

    public Button(SearchContext host, By locator){
        super(host,locator);
    }

    public void click(){
        wrappedElement.click();
    }

    public String getText(){
        return wrappedElement.getText();
    }

    public boolean isEnabled(){
        return wrappedElement.isEnabled();
    }

    public String getAttribute(String attributeName){
        return wrappedElement.getAttribute(attributeName);
    }

}
