package Setting;

import Reports.CaptureScreenShot;
import Reports.LoggingEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Setting {

    private static ChromeDriverService service;
    protected static WebDriver driver;
    private static final WebDriverEventListener eventListener = new LoggingEventListener();

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
        driver = new EventFiringWebDriver(new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome())).register(eventListener);
        driver.get("http://alpha.vloop.io/sign_in");
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        CaptureScreenShot screen = new CaptureScreenShot(driver);
        screen.takeScreenshot(result);
        driver.quit();
        service.stop();
    }

    public static void sleep(int time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
