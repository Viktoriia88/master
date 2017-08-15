package Setting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class Setting {

    private static ChromeDriverService service;
    protected WebDriver driver;

    @BeforeMethod
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\Users\\Kay\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @BeforeMethod
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        driver.get("http://alpha.vloop.io/sign_in");
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @AfterMethod
    public static void serviceStop(){
        service.stop();
    }

}
