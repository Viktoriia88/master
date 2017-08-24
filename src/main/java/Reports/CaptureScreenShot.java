package Reports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CaptureScreenShot {
    
    WebDriver driver;

    public CaptureScreenShot(WebDriver driver){
        this.driver = driver;
    }

    public void  takeScreenshot(ITestResult result){
        System.out.println("Test result " + result);
        if (result.getStatus() == ITestResult.FAILURE){
            File tmpFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Date currentDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.ENGLISH);
            String fileName = "screenshot_" + result.getName() + "_" + formatter.format(currentDate);
            String filePath = "G:/Screen/" + fileName + ".png";
            try {
                FileUtils.copyFile(tmpFile, new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //add screenshot of failed test to ReportNG
            final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
            System.setProperty(ESCAPE_PROPERTY, "false");
            Reporter.log("<p><a href=" + filePath + ">" + fileName.toString() + "</a></p>");
        }
    }
}
