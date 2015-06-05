package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by user on 05.06.2015.
 */
public class Link extends ElementBase{


    public Link(SearchContext host, By locator){
        super(host,locator);
    }

    public String getText(){
        return wrappedElement.getText();
    }

    public String getLinkUrl(){
        return wrappedElement.getAttribute("href");
    }

}
