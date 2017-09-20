import Pages.Game;
import Pages.Home;
import Pages.SignIn;
import Setting.Setting;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestVloopGamePage extends Setting {

    @BeforeMethod
    public void uploadVideo(){
        SignIn signIn = new SignIn();
        signIn.logIn("vloopapp15@gmail.com", "12345678vloop");
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_video")));
        Home home = new Home();
        home.closeToastMessage();
        //home.clean();
        //home.uploadVideoPc();
        home.navigateToVideo();
    }

    @Test
    public void checkThatVideoIsPlayed(){
        Game game = new Game();
        Assert.assertTrue(game.playVideo() > 0);
    }

    @Test
    public void checkSkipForward(){
        Game game = new Game();
        int startTime = game.getTimeSec();
        game.skipForward();
        Assert.assertTrue(game.getTimeSec() == startTime + 1);
    }

    @Test
    public void checkSkipBack(){
        Game game = new Game();
        int startTime = game.getTimeSec();
        game.skipForward();
        game.skipBack();
        Assert.assertTrue(game.getTimeSec() == startTime);
    }

    @Test
    public void checkLoadImageCanvas(){
        Game game = new Game();
        game.loadImage();
        Assert.assertTrue(game.isUploadCvsImgPresent());
    }
}
