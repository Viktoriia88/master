package Pages;

import Enums.Variables;
import Setting.Setting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn extends Setting{

    private WebElement logInBtn = driver.findElement(By.id(Variables.logInBtn.toString()));
    private WebElement emailFld = driver.findElement(By.id(Variables.emailFld.toString()));
    private WebElement passwordFld = driver.findElement(By.id(Variables.passwordFld.toString()));
    private WebElement contactUsLink = driver.findElement(By.id(Variables.contactUsLink.toString()));

    public void logIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        Setting.sleep(1);
        logInBtn.click();
    }

    public void enterEmail(String email) {
        emailFld.clear();
        emailFld.sendKeys(email);
    }

    public void enterPassword(String password){
        passwordFld.clear();
        passwordFld.sendKeys(password);
    }

    public boolean isLogInBtnEnabled(){
        return logInBtn.isEnabled();
    }

    public void clickContactUsLink(){
        contactUsLink.click();
    }

    public void signOut(){
        WebElement signOutItem = driver.findElement(By.id(Variables.signOutBtn.toString()));
        signOutItem.click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Variables.vloopLogo.toString())));
    }

}
