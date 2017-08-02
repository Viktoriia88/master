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
        signIn.logIn();
        WebElement el = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_video")));
        Assert.assertEquals(driver.getCurrentUrl(), "http://alpha.vloop.io/home/");
    }
}
