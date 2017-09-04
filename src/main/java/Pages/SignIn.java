package Pages;

import Setting.Setting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SignIn extends Setting{

    private WebElement logInBtn = driver.findElement(By.id("user_login"));
    private WebElement emailFld = driver.findElement(By.cssSelector("#user_email"));
    private WebElement passwordFld = driver.findElement(By.id("user_password"));
    private WebElement contactUsLink = driver.findElement(By.id("contact_us"));
    private WebElement signUpLink = driver.findElement(By.linkText("Sign Up"));

    public void logIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        logInBtn.click();
    }

    public void enterEmail(String email) {
        emailFld.sendKeys(email);
    }

    public void enterPassword(String password){
        passwordFld.sendKeys(password);
    }

    public boolean isLogInBtnEnabled(){
        return logInBtn.isEnabled();
    }

    public void clickContactUsLink(){
        contactUsLink.click();
    }

    public void clickSignUpLink(){
        signUpLink.click();
    }


}
