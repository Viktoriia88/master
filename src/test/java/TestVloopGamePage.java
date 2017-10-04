import Pages.Game;
import Pages.Home;
import Pages.SignIn;
import Settings.Setting;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestVloopGamePage extends Setting {

    private String comment = RandomStringUtils.randomAlphabetic(5).toString();
    private String editComment = RandomStringUtils.randomAlphabetic(5).toString();
    private String tag = RandomStringUtils.randomAlphabetic(5).toString();
    private String video = "Video.mp4";

    @BeforeMethod
    public void uploadVideo(){
        SignIn signIn = new SignIn();
        signIn.logIn("vloopapp15@gmail.com", "12345678vloop");
        Home home = new Home();
        sleep(1);
        home.selectVideo(video);
        sleep(1);
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

    @Test
    public void checkCommentAdding(){
        Game game = new Game();
        game.createComment(comment);
        Assert.assertTrue(game.isCommentPresent(comment));
    }

    @Test
    public void checkCommentEditing(){
        Game game = new Game();
        game.createComment(comment);
        game.editComment(editComment);
        Assert.assertTrue(game.isCommentPresent(editComment));
    }

    @Test
    public void checkTagAdding(){
        Game game = new Game();
        game.addTag(tag);
        sleep(2);
        Assert.assertTrue(game.getTagsText().contains(tag));
        game.deleteTag();
    }

    @Test
    public void checkAddingCommentByTag(){
        Game game = new Game();
        game.cleanCommentList();
        game.addTag(tag);
        game.addCommentByTag(tag);
        Assert.assertTrue(game.getCommentsText().contains(tag));
        game.deleteTag();
    }

    @Test
    public void checkScreenShot(){
        Game game = new Game();
        String linkScreenShot = game.takeScreenShot();
        Assert.assertTrue(game.getToScreenShotValues().contains(linkScreenShot));
    }
}
