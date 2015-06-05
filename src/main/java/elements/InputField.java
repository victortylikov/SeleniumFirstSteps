package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by user on 05.06.2015.
 */
public class InputField extends ElementBase{

    public InputField(SearchContext host, By locator){
    super(host,locator);
    }

    public String getText(){
        return wrappedElement.getAttribute("value");
    }

    public boolean isEnabled(){
        return wrappedElement.isEnabled();
    }

    public void clear(){
        wrappedElement.clear();
    }

    public void appendText(String value){
        wrappedElement.sendKeys(value);
    }

    public void setText(String value){
        clear();
        wrappedElement.sendKeys(value);
    }
}
