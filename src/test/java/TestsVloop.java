import Enums.Variables;
import Pages.SignIn;
import Setting.Setting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestsVloop extends Setting{

    @Test
    public void checkLogInVloop() {
        SignIn signIn = new SignIn();
        signIn.logIn("vloopapp15@gmail.com", "12345678vloop");
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.id(Variables.addVideoBtn.toString())));
        Assert.assertEquals(driver.getCurrentUrl(), "https://portal.vloop.io/home/");
        Setting.sleep(1);
        signIn.signOut();
        Assert.assertEquals(driver.getCurrentUrl(), "https://portal.vloop.io/sign_in");
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
        signIn.logIn("vloopapp@gmail.com", "12345678vloop");
        WebElement toastMsg = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".toast-message")));
        Assert.assertEquals(toastMsg.getText(), "Invalid login credentials. Please try again.");
    }

    @Test
    public void checkContactUsLinkIsClickable(){
        SignIn signIn = new SignIn();
        signIn.clickContactUsLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://portal.vloop.io/contact_us");
    }
}
