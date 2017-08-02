package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignIn {

    WebDriver driver;

    public SignIn(WebDriver driver) {
        this.driver = driver;
    }

    public void logIn() {
        WebElement emailFld = driver.findElement(By.cssSelector("#user_email"));
        emailFld.sendKeys("vloopapp15@gmail.com");
        WebElement passwordFld = driver.findElement(By.id("user_password"));
        passwordFld.sendKeys("12345678vloop");
        WebElement logInBtn = driver.findElement(By.id("user_login"));
        logInBtn.click();
        }
}
