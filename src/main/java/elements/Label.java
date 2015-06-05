package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

/**
 * Created by user on 05.06.2015.
 */
public class Label extends ElementBase {


    public Label(SearchContext host, By locator) {
        super(host, locator);
    }

    public String getText(){
        return wrappedElement.getText();
    }
}
