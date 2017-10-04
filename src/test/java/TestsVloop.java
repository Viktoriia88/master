import Element.Element;
import Pages.SignIn;
import Settings.Setting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class TestsVloop extends Setting{

    @Test
    public void checkLogIn() {
        SignIn signIn = new SignIn();
        signIn.logIn("vloopapp15@gmail.com", "12345678vloop");
        Assert.assertEquals(driver.getCurrentUrl(), "https://portal.vloop.io/home/");
    }

    @Test
    public void checkLogInBtnEnabled(){
        SignIn signIn = new SignIn();
        signIn.enterEmail("vloopapp15@gmail.com");
        Assert.assertFalse(signIn.isLogInBtnEnabled());
    }

    @Test
    public void checkWrongEmail(){
        SignIn signIn = new SignIn();
        signIn.enterEmail("vloopapp@gmail.com");
        signIn.enterPassword("12345678vloop");
        signIn.clickLogInBtn();
        Element toastMsg = new Element(By.cssSelector(".toast-message"));
        toastMsg.waitIsPresent();
        Assert.assertEquals(toastMsg.getText(), "Invalid login credentials. Please try again.");
    }

    @Test
    public void checkContactUsLinkIsClickable(){
        SignIn signIn = new SignIn();
        signIn.clickContactUsLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://portal.vloop.io/contact_us");
    }
}
