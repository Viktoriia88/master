package Element;
import Settings.Setting;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Element extends Setting{

    protected By by;

    public Element(By by) {
       this.by = by;
    }

    public void click(){
        driver.findElement(by).click();
    }

    public boolean isClickable(){
        try {
            new WebDriverWait(driver, 100)
                    .until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean isPresent(){
        try {
            driver.findElement(this.by).isDisplayed();
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
    
    public void waitIsPresent(){
        Setting.sleep(1);
        for (int i = 0; i < 120; i++){
            if (isPresent()== true){
                break;
            }
            Setting.sleep(1);
        }
    }
    
    public void sendKeys(String string){
        driver.findElement(by).sendKeys(string);
    }

    public String getText(){
        return driver.findElement(by).getText();
    }

    public void clear(){
        driver.findElement(by).clear();
    }

    public boolean isDisplayed(){
        return driver.findElement(by).isDisplayed();
    }

    public String getAttribute(String attribute){
        return driver.findElement(by).getAttribute(attribute);
    }

    public boolean isEnabled(){
        return driver.findElement(by).isEnabled();
    }

    public ArrayList<String> getElementsTextList(){
        List<WebElement> list = driver.findElements(by);
        ArrayList<String> listTxt = new ArrayList<String>();
        for (WebElement el : list){
            listTxt.add(el.getText());
        }
        return listTxt;
    }

    public ArrayList<String> getElementsValueList(){
        List<WebElement> list = driver.findElements(by);
        ArrayList<String> listValues= new ArrayList<String>();
        for (WebElement el : list){
            listValues.add(el.getAttribute("value").toString());
        }
        return listValues;
    }


}
