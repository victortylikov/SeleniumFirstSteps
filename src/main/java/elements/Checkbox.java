package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by user on 05.06.2015.
 */
public class Checkbox extends ElementBase {
    public Checkbox(SearchContext host, By locator) {
        super(host, locator);
    }

    public void click(){
        wrappedElement.click();
    }

    public void isSelected(){
        wrappedElement.isSelected();
    }

    public void isEnabled(){
        wrappedElement.isEnabled();
    }

    public void check(WebElement checkBox) {
        setCheckboxTo(true);
    }

    public void uncheck(WebElement checkBox) {
        setCheckboxTo(false);
    }

    public void setCheckboxTo(boolean wishToCheck){
        if(wrappedElement.isSelected()!=wishToCheck){
            click();
        }
    }
}
