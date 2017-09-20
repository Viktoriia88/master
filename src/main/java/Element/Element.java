package Element;
import Setting.Setting;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
            new WebDriverWait(driver, 60)
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
        for (int i = 0; i < 60; i++){
            if ( isPresent()== true){
                break;
            }
            Setting.sleep(1);
        }
    }
    
    public void sendKeys(String str){
        driver.findElement(this.by).sendKeys(str);
    }

    public String getText(){
        return driver.findElement(by).getText();
    }
}
