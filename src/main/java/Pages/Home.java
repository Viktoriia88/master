package Pages;

import Element.Element;
import Settings.Setting;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.IOException;

public class Home extends Setting{

    private Element addFldBtn = new Element(By.id("add_folder"));
    private Element addFlBtn = new Element(By.id("add_files"));
    private Element saveFlBtn = new Element(By.id("update_video_button"));
    private Element dropBoxUplBtn = new Element(By.id("dropbox_upload_btn"));
    private Element pCUpldBtn = new Element(By.id("file_upload_btn"));
    private Element yUploadBtn = new Element(By.id("youtube_upload_btn" ));
    private Element prviewYTBtn = new Element(By.id("preview_youtube_btn"));
    private Element uplVdBtn = new Element(By.id("add_video"));
    private Element saveFldBtn = new Element(By.xpath("//button[@ng-click='saveFolder()']"));
    private Element fldTltFld = new Element(By.xpath("//input[@ng-model='folder.current.title']"));
    private Element videoPlayer =  new Element(By.id("game_analyze_video"));
    private Element editFld = new Element(By.cssSelector(".form-control.ng-not-empty"));
    private Element dropZoneFile = new Element(By.id("file_drop_zone"));
    private Element uploadBtn = new Element(By.xpath("//button[@ng-click='saveFiles()']"));
    private Element confirmDltBtn = new Element(By.id("confirm-modal-button"));
    private Element uplBar = new Element(By.cssSelector(".progress-upload"));
    private Element actItm = new Element(By.cssSelector(".select-actions"));
    private Element deleteBtn = new Element(By.id("delete_videos"));
    private Element editFlItm = new Element(By.xpath("//a[@ng-click='openEditGameModal(row)']"));
    private Element toastMsg = new Element(By.cssSelector(".toast"));
    private Element toastClsBtn = new Element(By.cssSelector(".toast-close-button"));
    private Element choseDrBoxBtn = new Element(By.cssSelector(".dropbox-dropin-btn"));
    private Element emailDrBox = new Element(By.xpath("//input[@name='login_email']"));
    private Element pswdDrBox = new Element(By.xpath("//input[@name='login_password']"));
    private Element logInDrBox = new Element(By.cssSelector(".login-button"));
    private Element chooseBtn = new Element(By.id("select-btn"));
    private Element uplVdSubmitBtn = new Element(By.id("vid_submit"));
    private Element dropZoneVideo = new Element(By.id("video_drop_zone"));
    private Element gameCkBox = new Element(By.xpath("//div[@class='icheckbox_square-green' and @aria-hidden='false']"));
    private Element linkFlYT = new Element(By.id("youtube_link"));
    private Element pblLinkYT = new Element(By.id("publish_youtube_link"));
    private Element fldTlt = new Element(By.cssSelector(".icheckbox_square-green.ng-hide"));
    private Element gameTlt = new Element(By.xpath("//div[@class='icheckbox_square-green' and @aria-hidden='false']"));
    private Element fldImg = new Element(By.xpath("//img[@src='https://s3-us-west-2.amazonaws.com/vloop-static/Open+Folder-528.png']"));
    private Element editClnFld = new Element(By.cssSelector(".form-control.ng-dirty"));
    private Element mediaControlPanel = new Element(By.id("media-controls"));
    private Element crClipDrawBtn = new Element(By.id("unhide-clip-button"));


    public void addNewFolder(String fld){
        addFldBtn.click();
        fldTltFld.waitIsPresent();
        fldTltFld.sendKeys(fld);
        saveFldBtn.isClickable();
        saveFldBtn.click();
        Element fldRcd = new Element(By.xpath("//tr[contains(., '" + fld + "')]/td//img[@src='https://icons.vloop.io/Ellipsis+Filled-50.png']"));
        fldRcd.waitIsPresent();
    }

    public boolean isFolderPresent(String folder){
        Element folderEl = new Element(By.xpath("//td/a[text()='" + folder + "']"));
        folderEl.waitIsPresent();
        return folderEl.isPresent();
    }

    public void editFolder(String folder, String editFld){
        Element activeItem = new Element(By.xpath("//tr[contains(., '" + folder + "')]//td//a[@class='m-l-sm edit_game_desktop']"));
        activeItem.click();
        Element editFldItem = new Element(By.xpath("//tr[contains(., '" + folder + "')]//a[@class='edit_folder']"));
        editFldItem.waitIsPresent();
        editFldItem.click();
        enterDate(editFld);
        saveFldBtn.isClickable();
        saveFldBtn.click();
    }

    public void enterDate(String editTlt){
        editFld.waitIsPresent();
        editFld.clear();
        editClnFld.waitIsPresent();
        editClnFld.sendKeys(editTlt);
    }

    public void deleteFolder(String folder) {
        Element activeItem = new Element(By.xpath("//tr[contains(., '" + folder + "')]//td//a[@class='m-l-sm edit_game_desktop']"));
        activeItem.click();
        Element dltFldItem = new Element(By.xpath("//tr[contains(., '" + folder + "')]//a[@class='delete_folder']"));
        dltFldItem.waitIsPresent();
        dltFldItem.click();
        confirmDeleting();
    }

    public void uploadFileFromPc() {
        addFlBtn.click();
        dropZoneFile.waitIsPresent();
        dropZoneFile.click();
        selectFromPc("UploadImage.exe");
        uploadBtn.isClickable();
        uploadBtn.click();
        waitUploading(20);
    }

    public boolean isGamePresent(String game){
        Element gameEl= new Element(By.xpath("//tr[contains(., '" + game + "')]"));
        gameEl.waitIsPresent();
        sleep(2);
        return gameEl.isPresent();
    }

    public void waitUploading(int time){
        sleep(1);
        for (int i = 0; i < time; i++) {
            if (uplBar.isPresent() == false) {
                break;
            }
            sleep(1);
        }
    }

    public void confirmDeleting(){
        confirmDltBtn.waitIsPresent();
        confirmDltBtn.isClickable();
        confirmDltBtn.click();
    }

    public void delete(String game){
        Element activeItem = new Element(By.xpath("//div[@name='" + game + "']"));
        activeItem.click();
        actItm.waitIsPresent();
        deleteBtn.waitIsPresent();
        deleteBtn.isClickable();
        deleteBtn.click();
        confirmDeleting();
    }

    public void edit(String game, String editGame){
        Element activeItem = new Element(By.xpath("//tr[contains(., '" + game + "')]//td//a[@class='m-l-sm edit_game_desktop']"));
        activeItem.click();
        editFlItm.click();
        enterDate(editGame);
        saveFlBtn.click();
    }

    public void uploadVideoFromDropBox(String email, String password, String video){
        uplVdBtn.click();
        dropBoxUplBtn.click();
        choseDrBoxBtn.waitIsPresent();
        choseDrBoxBtn.click();
        String homeWindow = driver.getWindowHandle();
        for(String drBoxWindow : driver.getWindowHandles())
        {
            driver.switchTo().window(drBoxWindow);
        }
        sleep(1);
        emailDrBox.sendKeys(email);
        pswdDrBox.sendKeys(password);
        logInDrBox.click();
        Element vdName = new Element(By.xpath("//li[contains(., '" + video + "')]"));
        vdName.waitIsPresent();
        vdName.click();
        chooseBtn.click();
        driver.switchTo().window(homeWindow);
        sleep(1);
        uplVdSubmitBtn.isClickable();
        uplVdSubmitBtn.click();
    }

    public void uploadVideoPc(){
        uplVdBtn.isClickable();
        uplVdBtn.click();
        pCUpldBtn.isClickable();
        pCUpldBtn.click();
        dropZoneVideo.waitIsPresent();
        dropZoneVideo.click();
        selectFromPc("UploadVideo.exe");
        uplVdSubmitBtn.waitIsPresent();
        uplVdSubmitBtn.click();
        waitUploading(80);
    }

    public void selectFromPc(String path){
        sleep(1);
        try {
            Runtime.getRuntime().exec("C:\\Users\\Kay\\IdeaProjects\\testsvloop\\" + path);
        } catch (IOException e) {
        }
    }

    public void uploadYouTubeVideo(String yLink, String videoYT){
        uplVdBtn.click();
        yUploadBtn.click();
        linkFlYT.waitIsPresent();
        linkFlYT.sendKeys(yLink);
        prviewYTBtn.click();
        pblLinkYT.isClickable();
        pblLinkYT.click();
        Element vdNameYT = new Element(By.xpath("//a[contains(., '" + videoYT + "')]"));
        vdNameYT.waitIsPresent();
    }

    public String getVdDuration(String video){
        Element duration = new Element(By.xpath("//tr[contains(., '" + video + "')]/td/span/span"));
        return duration.getText();
    }

    public String getFolderTitle(){
        return fldTlt.getAttribute("name");
    }

    public String getGameTitle(){
        return gameTlt.getAttribute("name");
    }

    public boolean isFolderImagePresent(){
        try{
            fldImg.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isCheckBoxGamePresent(){
        try {
            gameCkBox.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public void navigateToVideo(){
        Element video = new Element(By.xpath("//a[contains(., '" + getGameTitle() + "')]"));
        video.waitIsPresent();
        video.click();
        videoPlayer.waitIsPresent();
        mediaControlPanel.waitIsPresent();
        crClipDrawBtn.isClickable();
    }

    public void clean(){
        while (isFolderImagePresent() == true){
            String folder = getFolderTitle();
            deleteFolder(folder);
            sleep(1);
        }
        while (isCheckBoxGamePresent() == true){
            String game = getGameTitle();
            delete(game);
            sleep(1);
        }
    }

    public void selectVideo(String video) {
        try {
            Element videoTitle = new Element(By.xpath("//a[contains(., '" + video + "')]"));
            Element videoTitleNew = new Element(By.xpath("//a[contains(., 'Video(2).mp4')]"));

            if (videoTitle.isPresent() == true || videoTitleNew.isPresent() == true) {
                sleep(1);
                navigateToVideo();
            } else
                {
                uploadVideoPc();
                sleep(1);
                navigateToVideo();
                }
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
}
