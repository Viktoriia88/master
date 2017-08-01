import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestsVloop {

    @Test
    public void checkLogInVloop(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kay\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://alpha.vloop.io/");
        WebElement emailFld = driver.findElement(By.cssSelector("#user_email".toString()));
        emailFld.sendKeys("vloopapp15@gmail.com");
        WebElement passwordFld = driver.findElement(By.id("user_password"));
        passwordFld.sendKeys("12345678vloop");
        WebElement logInBtn = driver.findElement(By.id("user_login"));
        logInBtn.click();
        WebElement el = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_video")));
        Assert.assertEquals(driver.getCurrentUrl(), "http://alpha.vloop.io/home/");
        driver.quit();
    }
}
