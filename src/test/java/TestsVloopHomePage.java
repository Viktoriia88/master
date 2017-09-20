import Pages.Home;
import Pages.SignIn;
import Setting.Setting;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestsVloopHomePage extends Setting {

    private String file = "football.jpg";
    private String editFile = RandomStringUtils.randomAlphabetic(6).toString() + ".jpg";
    private String video = "vloopVideo.mp4";
    private String youTubeLink = "https://www.youtube.com/watch?v=kXYiU_JCYtU";
    private String yVideo = "Numb (Official Video) - Linkin Park";
    private String yVideoTitle = RandomStringUtils.randomAlphabetic(6).toString();
    private String folder = RandomStringUtils.randomAlphabetic(6).toString();
    private String editFolder = RandomStringUtils.randomAlphabetic(6).toString();


    @BeforeMethod
    public void cleanHomePage() {
        SignIn signIn = new SignIn();
        signIn.logIn("vloopapp15@gmail.com", "12345678vloop");
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_video")));
        Home home = new Home();
        home.closeToastMessage();
        home.clean();
    }

    @Test
    public void checkNewFolderIsAdded(){
        Home home = new Home();
        home.addNewFolder(folder);
        Setting.sleep(1);
        Assert.assertTrue(home.isFolderPresent(folder));
    }

    @Test
    public void checkEditingFolder(){
        Home home = new Home();
        home.addNewFolder(folder);
        new WebDriverWait(driver, 100)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[contains(., '" + folder + "')]/td//img[@src='https://icons.vloop.io/Ellipsis+Filled-50.png']")));
        home.editFolder(folder, editFolder);
        Setting.sleep(2);
        Assert.assertTrue(home.isFolderPresent(editFolder));
    }

    @Test
    public void checkFileUploading(){
        Home home = new Home();
        home.uploadFileFromPc();
        Assert.assertTrue(home.isGamePresent(file));
    }

    @Test
    public void checkFileEditing(){
        Home home = new Home();
        home.uploadFileFromPc();
        home.edit(file, editFile);
        Setting.sleep(1);
        Assert.assertTrue(home.isGamePresent(editFile));
    }

    @Test
    public void checkVdUploadingFromDrBox(){
        Home home = new Home();
        home.uploadVideoFromDropBox("kay4444@ukr.net", "Kay4444@ukr", video);
        Setting.sleep(1);
        Assert.assertTrue(home.isGamePresent(video));
    }

    @Test
    public void checkVdUploadingPC(){
        Home home = new Home();
        home.uploadVideoPc();
        Setting.sleep(2);
        Assert.assertTrue(home.isGamePresent(video));
        Assert.assertEquals(home.getVdDuration(video), "Processing");
    }

    @Test
    public void checkVdUploadingYouTube(){
        Home home = new Home();
        home.uploadYouTubeVideo(youTubeLink);
        Setting.sleep(2);
        Assert.assertTrue(home.isGamePresent(yVideo));
        Assert.assertEquals(home.getVdDuration(yVideo), "00:03:07");
    }

    @Test
    public void checkVdEditing(){
        Home home = new Home();
        home.uploadYouTubeVideo(youTubeLink);
        home.edit(yVideo, yVideoTitle);
        Setting.sleep(1);
        Assert.assertTrue(home.isGamePresent(yVideoTitle));
    }
}
