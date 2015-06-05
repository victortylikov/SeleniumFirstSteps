package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by user on 05.06.2015.
 */
public class ElementBase {
    protected final SearchContext host;
    protected final By locator;
    protected final WebElement wrappedElement;

    public ElementBase(SearchContext host, By locator){
        this.host=host;
        this.locator=locator;
        wrappedElement = host.findElement(locator);
    }

    //region Element methods
    public SearchContext getHost() {
        return host;
    }

    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public By getLocator() {
        return locator;
    }
    //region
}
