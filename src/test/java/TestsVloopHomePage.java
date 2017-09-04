import Pages.Home;
import Pages.SignIn;
import Setting.Setting;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestsVloopHomePage extends Setting {

    public String folder = RandomStringUtils.randomAlphabetic(6).toString();
    public String editFolder = RandomStringUtils.randomAlphabetic(6).toString();

    @BeforeMethod
    public void logIn(){
        SignIn signIn = new SignIn();
        signIn.logIn("vloopapp15@gmail.com", "12345678vloop");
        WebElement el = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.id("add_video")));
    }

    @Test
    public void checkNewFolderIsAdded(){
        Home home = new Home();
        home.addNewFolder(folder);
        Setting.sleep(1);
        Assert.assertTrue(home.isFolderPresent(folder), String.valueOf(true));
    }

    @Test
    public void checkEditingFolder(){
        Home home = new Home();
        home.addNewFolder(folder);
        WebElement el = (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[contains(., '" + folder + "')]/td//img[@src='https://icons.vloop.io/Ellipsis+Filled-50.png']")));
        home.editFolder(folder, editFolder);
        Setting.sleep(2);
        Assert.assertTrue(home.isEditFolderPresent(editFolder));
    }

    @Test
    public void checkDeletingFolder(){
        Home home = new Home();
        home.addNewFolder(folder);
        WebElement el = (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[contains(., '" + folder + "')]/td//img[@src='https://icons.vloop.io/Ellipsis+Filled-50.png']")));
        home.deleteFolder(folder);
        Setting.sleep(1);
        Assert.assertFalse(home.isFolderPresent(folder));
    }

    @Test
    public void checkFileUploading(){
        Home home = new Home();
        home.selectFileFromPc();
        Setting.sleep(1);
        Assert.assertTrue(home.isFilePresent("football.jpg"));
    }
}
