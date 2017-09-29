package Settings;

import Reports.CaptureScreenShot;
import Reports.LoggingEventListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Setting {

    private static ChromeDriverService service;
    protected static WebDriver driver;
    private static final WebDriverEventListener eventListener = new LoggingEventListener();

    @BeforeSuite
    public void deleteScreenDirection(){
        try {
            FileUtils.deleteDirectory(new File("C:/Users/Kay/IdeaProjects/testsvloop/src/main/java/Reports/Screens"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
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
        driver.get("http://portal.vloop.io/sign_in");
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void takeScreenShot(ITestResult result){
        CaptureScreenShot screen = new CaptureScreenShot(driver);
        screen.takeScreenshot(result);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void stopService(){
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
