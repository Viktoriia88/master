package Pages;

import Element.Element;
import Settings.Setting;
import org.openqa.selenium.By;

public class SignIn extends Setting{

    private Element logInBtn = new Element(By.id("user_login"));
    private Element emailFld = new Element(By.id("user_email"));
    private Element pswrFld = new Element(By.id("user_password"));
    private Element contactUsLink = new Element(By.id("contact_us"));
    private Element addVideoBtn = new Element(By.id("add_video"));

    public void logIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogInBtn();
        addVideoBtn.waitIsPresent();
    }

    public void enterEmail(String email) {
        emailFld.clear();
        emailFld.sendKeys(email);
    }

    public void enterPassword(String password){
        pswrFld.clear();
        pswrFld.sendKeys(password);
    }

    public void clickLogInBtn(){
        logInBtn.click();
    }

    public boolean isLogInBtnEnabled(){
        return logInBtn.isEnabled();
    }

    public void clickContactUsLink(){
        contactUsLink.click();
    }


}
