package Pages;

import Element.Element;
import Settings.Setting;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;

public class Game extends Setting{

    private Element curTimeIcon = new Element(By.id("current-time"));
    private Element playPauseBtn = new Element(By.id("play-pause-icon"));
    private Element skipFrdBtn = new Element(By.id("forward-button"));
    private Element skipBackBtn = new Element(By.id("rewind-button"));
    private Element crClipDrawBtn = new Element(By.id("unhide-clip-button"));
    private Element toolBar =  new Element(By.id("fabrick-controls"));
    private Element pictureIcon = new Element(By.id("picture-icon"));
    private Element canvasImgClsBtn = new Element(By.id("canvas_bg_img_close_btn"));
    private Element saveCvsBtn = new Element(By.id("btn-cnv-prim"));
    private Element uploadImgBtn = new Element(By.id("upload-image"));
    private Element uplCvsImg = new Element(By.xpath("//img[@src='https://icons.vloop.io/picture-48.png' and @aria-hidden='false']"));
    private Element commentFld = new Element(By.cssSelector(".new-comment-ta.no-right-radius"));
    private Element commentMdlFld = new Element(By.id("comment-ta-main"));
    private Element postComment = new Element(By.id("post_comment"));
    private Element allCommentsArea = new Element(By.id("comments-all"));
    private Element inlineCkBox = new Element(By.xpath("//div[@class='form-inline']/div[@class='icheckbox_square-green']"));
    private Element dltPlayIcon = new Element(By.id("delete_play_icon"));
    private Element editPlayIcon = new Element(By.linkText("Edit"));
    private Element commentTxtArea = new Element(By.xpath("//div[@aria-hidden='false']/textarea"));
    private Element saveCommentBtn = new Element(By.linkText("Save"));
    private Element settingBtn = new Element(By.id("settings_modal_gear"));
    private Element tagTab = new Element(By.id("advanced_tagging_tab"));
    private Element fullTabNameFld = new Element(By.xpath("//input[@ng-model='customTag.name']"));
    private Element addTagBtn = new Element(By.xpath("//button[@ng-click='createCustomTag()']"));
    private Element closeVdSetUpBtn = new Element(By.id("video_setup_close_cross"));
    private Element dltTagCnfrmBtn = new Element(By.id("confirm-modal-button"));
    private Element clipsTab = new Element(By.id("activity_log_tab"));
    private Element tagBtn = new Element(By.cssSelector(".btn-primary.m-b-xs"));
    private Element commentsTxtFld = new Element(By.cssSelector(".pt_on_comment"));
    private Element crtTagBtn = new Element(By.xpath("//button[@ng-click='createTagModal()']"));
    private Element screenShotBtn = new Element(By.id("take-photo-icon"));
    private Element screenShotFld = new Element(By.id("share-screenshot"));
    private Element screenShotInput = new Element(By.cssSelector(".no_right_radius"));
    private Element boxContent = new Element(By.className(".ibox-content"));
    private Element copyLinkBtn = new Element(By.xpath("//button[@class='btn btn-white']"));
    private Element dltTagBtn = new Element(By.xpath("//a[@cm-header='Delete tag']"));

    public String getCurrentTime(){
        return curTimeIcon.getText();
    }

    public int getTimeSec(){
        String[] a = getCurrentTime().split(":");
        return Integer.parseInt(a[1]);
    }

    public void waitPlay(){
        for (int i = 0; i < 20; i++){
            if (getTimeSec() > 0){
                break;
            }
            Setting.sleep(1);
        }
    }

    public int playVideo(){
        playPauseBtn.isClickable();
        playPauseBtn.click();
        waitPlay();
        playPauseBtn.click();
        int time = getTimeSec();
        return time;
    }

    public void skipForward(){
        skipFrdBtn.isClickable();
        skipFrdBtn.click();
    }

    public void skipBack(){
        skipBackBtn.isClickable();
        skipBackBtn.click();
    }

    public void loadImage(){
        crClipDrawBtn.waitIsPresent();
        crClipDrawBtn.isClickable();
        crClipDrawBtn.click();
        toolBar.waitIsPresent();
        pictureIcon.click();
        uploadImgBtn.waitIsPresent();
        uploadImgBtn.sendKeys("C:/Users/Kay/IdeaProjects/testsvloop/football.jpg");
        canvasImgClsBtn.click();
        Setting.sleep(1);
        saveCvsBtn.click();
    }

    public boolean isUploadCvsImgPresent(){
        uplCvsImg.waitIsPresent();
        return uplCvsImg.isPresent();
    }

    public void addCommentsDate(String comment){
        commentFld.waitIsPresent();
        commentFld.click();
        commentMdlFld.waitIsPresent();
        commentMdlFld.sendKeys(comment);
        postComment.click();
        allCommentsArea.waitIsPresent();
    }

    public boolean isCommentPresent(String comment){
        Element commentRcd = new Element(By.xpath("//span[contains(., '" + comment + "')]"));
        commentRcd.waitIsPresent();
        return commentRcd.isPresent();
    }

    public void cleanCommentList(){
        try {
            if (inlineCkBox.isPresent() == true){
                inlineCkBox.click();
                dltPlayIcon.waitIsPresent();
                dltPlayIcon.click();
            }
        }
        catch (ElementNotVisibleException e){

        }
    }

    public void createComment(String comment){
        cleanCommentList();
        addCommentsDate(comment);
    }

    public void editComment(String comment){
        editPlayIcon.waitIsPresent();
        editPlayIcon.click();
        commentTxtArea.clear();
        commentTxtArea.sendKeys(comment);
        saveCommentBtn.click();
    }

    public void addTag(String tag){
        tagTab.waitIsPresent();
        tagTab.click();
        settingBtn.click();
        fullTabNameFld.waitIsPresent();
        fullTabNameFld.sendKeys(tag);
        Select select = new Select(driver.findElement(By.xpath("//select[@ng-model='customTag.parent_play_id']")));
        select.selectByIndex(2);
        addTagBtn.isClickable();
        addTagBtn.click();
        closeVdSetUpBtn.click();
    }

    public ArrayList<String> getTagsText(){
        return tagBtn.getElementsTextList();
    }

    public void deleteTag(){
        try {
            driver.get("https://portal.vloop.io/play_types");
            crtTagBtn.waitIsPresent();
            while (dltTagBtn.isPresent() == true) {
                dltTagBtn.isClickable();
                dltTagBtn.click();
                dltTagCnfrmBtn.waitIsPresent();
                dltTagCnfrmBtn.click();
            }
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public void addCommentByTag(String tag){
        Element tagBtn = new Element(By.xpath("//button[@uib-tooltip='" + tag + "']"));
        tagBtn.waitIsPresent();
        tagBtn.click();
        clipsTab.isClickable();
        clipsTab.click();
        allCommentsArea.waitIsPresent();
        Element commentRcd = new Element(By.xpath("//span[contains(., '" + tag + "')]"));
        commentRcd.waitIsPresent();
    }

    public ArrayList<String> getCommentsText(){
        return commentsTxtFld.getElementsTextList();
    }

    public String takeScreenShot(){
        screenShotBtn.isClickable();
        screenShotBtn.click();
        screenShotFld.waitIsPresent();
        copyLinkBtn.isClickable();
        copyLinkBtn.click();
        String screenStLink = screenShotFld.getAttribute("value").toString();
        return screenStLink;
    }

    public ArrayList<String> getToScreenShotValues(){
        driver.get("https://portal.vloop.io/screenshots");
        boxContent.waitIsPresent();
        return screenShotInput.getElementsValueList();
    }
}
