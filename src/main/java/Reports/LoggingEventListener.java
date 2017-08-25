package Reports;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class LoggingEventListener implements WebDriverEventListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());
    private By findBy;
    private String originalValue;

    public void beforeNavigateTo(String url, WebDriver driver) {
        logger.info("Navigate to " + url + "");
    }

    public void afterNavigateTo(String url, WebDriver driver) {

    }

    public void beforeNavigateBack(WebDriver driver) {

    }

    public void afterNavigateBack(WebDriver driver) {

    }

    public void beforeNavigateForward(WebDriver driver) {

    }

    public void afterNavigateForward(WebDriver driver) {
        logger.info("Navigated forward " + driver.getCurrentUrl());

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver driver) {
        findBy = by;

    }

    public void afterFindBy(By by, WebElement webElement, WebDriver driver) {

    }

    public void beforeClickOn(WebElement webElement, WebDriver driver) {

    }

    public void afterClickOn(WebElement el, WebDriver driver) {
        logger.info("Element is found " + findBy + " clicked successfully");
    }

    public void beforeChangeValueOf(WebElement el, WebDriver webDriver, CharSequence[] charSequences) {
        originalValue = el.getAttribute("value");
        if(originalValue == null){
            originalValue = el.getText();
        }

    }

    public void afterChangeValueOf(WebElement el, WebDriver webDriver, CharSequence[] charSequences) {
        String newValue = el.getAttribute("value");
        if (newValue != originalValue){
            logger.info("Element " + findBy + " is changed from original value" + originalValue + " to " + newValue);
        }
    }

    public void beforeScript(String s, WebDriver driver) {

    }

    public void afterScript(String s, WebDriver driver) {
     
    }

    public void onException(Throwable throwable, WebDriver driver) {

    }
}
