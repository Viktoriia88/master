package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignIn {

    WebDriver driver;

    public SignIn(WebDriver driver) {
        this.driver = driver;
    }

    public void logIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        WebElement logInBtn = driver.findElement(By.id("user_login"));
        logInBtn.click();
    }

    public void enterEmail(String email) {
        WebElement emailFld = driver.findElement(By.cssSelector("#user_email"));
        emailFld.sendKeys(email);
    }

    public void enterPassword(String password){
        WebElement passwordFld = driver.findElement(By.id("user_password"));
        passwordFld.sendKeys(password);
    }

    public boolean isLogInBtnEnabled(){
        WebElement logInBtn = driver.findElement(By.id("user_login"));
        return logInBtn.isEnabled();
    }


}
