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
        SignIn signIn = new SignIn(driver);
        signIn.logIn("vloopapp15@gmail.com", "12345678vloop");
        WebElement el = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_video")));
        Assert.assertEquals(driver.getCurrentUrl(), "http://alpha.vloop.io/hme/");
    }

    @Test
    public void checkLogInBtnEnabled(){
        SignIn signIn = new SignIn(driver);
        signIn.enterEmail("vloopapp15@gmail.com");
        Assert.assertTrue(signIn.isLogInBtnEnabled(), String.valueOf(true));
    }

    @Test
    public void checkWrongEmail(){
        SignIn signIn = new SignIn(driver);
        signIn.logIn("vloopapp@gmail.com", "12345678vloop");
        WebElement toastMsg = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".toast-message")));
        Assert.assertEquals(toastMsg.getText(), "Invalid login credentials. Please try again.");
    }
}
