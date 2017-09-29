import Pages.Home;
import Pages.SignIn;
import Settings.Setting;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestsVloopHomePage extends Setting {

    private String file = "football.jpg";
    private String editFl = RandomStringUtils.randomAlphabetic(6).toString() + ".jpg";
    private String video = "Video.mp4";
    private String linkYT = "https://www.youtube.com/watch?v=kXYiU_JCYtU";
    private String videoYT = "Numb (Official Video) - Linkin Park";
    private String vdTltYT = RandomStringUtils.randomAlphabetic(6).toString();
    private String folder = RandomStringUtils.randomAlphabetic(6).toString();
    private String editFld = RandomStringUtils.randomAlphabetic(6).toString();


    @BeforeMethod
    public void cleanHomePage() {
        SignIn signIn = new SignIn();
        signIn.logIn("vloopapp15@gmail.com", "12345678vloop");
        Home home = new Home();
        Setting.sleep(2);
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
        home.editFolder(folder, editFld);
        Assert.assertTrue(home.isFolderPresent(editFld));
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
        home.edit(file, editFl);
        Setting.sleep(1);
        Assert.assertTrue(home.isGamePresent(editFl));
    }

    @Test
    public void checkVdUploadingFromDrBox(){
        Home home = new Home();
        home.uploadVideoFromDropBox("kay4444@ukr.net", "Kay4444@ukr", video);
        Setting.sleep(1);
        Assert.assertTrue(home.isGamePresent(home.getGameTitle()));
    }

    @Test
    public void checkVdUploadingPC(){
        Home home = new Home();
        home.uploadVideoPc();
        Assert.assertTrue(home.isGamePresent(home.getGameTitle()));
    }

    @Test
    public void checkVdUploadingYouTube(){
        Home home = new Home();
        home.uploadYouTubeVideo(linkYT, videoYT);
        Setting.sleep(2);
        Assert.assertTrue(home.isGamePresent(videoYT));
        Assert.assertEquals(home.getVdDuration(videoYT), "00:03:07");
    }

    @Test
    public void checkVdEditing(){
        Home home = new Home();
        home.uploadYouTubeVideo(linkYT, videoYT);
        home.edit(videoYT, vdTltYT);
        Assert.assertTrue(home.isGamePresent(vdTltYT));
    }
}
