package Pages;

import Element.Element;
import Enums.Variables;
import Setting.Setting;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class Home extends Setting{

    private WebElement addFolderBtn = driver.findElement(By.id(Variables.addFldBtn.toString()));
    private WebElement saveFolderBtn = driver.findElement(By.xpath(Variables.saveFldBtn.toString()));
    private WebElement addFileBtn = driver.findElement(By.id(Variables.addFileBtn.toString()));
    private WebElement deleteBtn = driver.findElement(By.id(Variables.deleteVideoBtn.toString()));
    private WebElement saveFileBtn = driver.findElement(By.id(Variables.updateVideoBtn.toString()));
    private WebElement dropBoxUploadBtn = driver.findElement(By.id(Variables.uploadDrBsBtn.toString()));
    private WebElement pCUploadBtn = driver.findElement(By.id(Variables.uploadVideoPcBtn.toString()));
    private WebElement yUploadBtn = driver.findElement(By.id(Variables.youTubeUploadBtn.toString()));
    private WebElement previewBtn = driver.findElement(By.id(Variables.previewYouTubeBtn.toString()));
    private WebElement uploadVideoBtn = driver.findElement(By.id(Variables.addVideoBtn.toString()));
    private Element videoPlayer =  new Element(By.id("game_analyze_video"));

    public void addNewFolder(String folderTitle){
        addFolderBtn.click();
        WebElement folderTitleFld = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Variables.folderTltFld.toString())));
        folderTitleFld.sendKeys(folderTitle);
        WebElement saveFolderBtn = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(Variables.saveFldBtn.toString())));
        saveFolderBtn.click();
    }

    public boolean isFolderPresent(String folder){
        try {
            driver.findElement(By.xpath("//td/a[text()='" + folder + "']"));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void editFolder(String folder, String editFolder){
        WebElement activeItem = driver.findElement(By.xpath("//tr[contains(., '" + folder + "')]//td//a[@class='m-l-sm edit_game_desktop']"));
        activeItem.click();
        WebElement editFldItem= new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(., '" + folder + "')]//a[@class='edit_folder']")));
        editFldItem.click();
        enterEditDate(editFolder);
        saveFolderBtn.click();
    }

    public void enterEditDate(String editTitle){
        WebElement editField = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Variables.editFld.toString())));
        editField.clear();
        editField.sendKeys(editTitle);
    }

    public void deleteFolder(String folder) {
        WebElement activeItem = driver.findElement(By.xpath("//tr[contains(., '" + folder + "')]//td//a[@class='m-l-sm edit_game_desktop']"));
        activeItem.click();
        WebElement deleteFldItem = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(., '" + folder + "')]//a[@class='delete_folder']")));
        deleteFldItem.click();
        confirmDeleting();
    }

    public void uploadFileFromPc() {
        addFileBtn.click();
        WebElement fileDropZone = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("file_drop_zone")));
        fileDropZone.click();
        selectFromPc("UploadImage.exe");
        WebElement uploadBtn = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(Variables.uploadBtn.toString())));
        uploadBtn.click();
        waitUploading(20);
    }

    public boolean isGamePresent(String game){
        try {
            driver.findElement(By.xpath("//tr[contains(., '" + game + "')]"));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void waitUploading(int time){
        Setting.sleep(2);
        for (int i = 0; i < time; i++) {
            if (isUploadProgressBarPresent() == false) {
                break;
            }
            Setting.sleep(1);
        }
    }

    public void confirmDeleting(){
        WebElement confirmDelBtn = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("confirm-modal-button")));
        confirmDelBtn.click();
    }

    public boolean isUploadProgressBarPresent(){
        WebElement progressUploading = driver.findElement(By.cssSelector(Variables.progressBar.toString()));
        if (progressUploading.isDisplayed()){
            return true;
        }
        else {
            return false;
        }
    }

    public void delete(String game){
        WebElement activeItem = driver.findElement(By.xpath("//div[@name='" + game + "']"));
        activeItem.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Variables.actionItem.toString())));
        Setting.sleep(2);
        deleteBtn.click();
        confirmDeleting();
    }

    public void edit(String game, String editGame){
        WebElement activeItem = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[contains(., '" + game + "')]//td//a[@class='m-l-sm edit_game_desktop']")));
        activeItem.click();
        WebElement editFileItem = driver.findElement(By.xpath(Variables.editFlItem.toString()));
        editFileItem.click();
        enterEditDate(editGame);
        saveFileBtn.click();
        Setting.sleep(2);
    }

    public boolean isToastPresent(){
        try{
            driver.findElement(By.cssSelector(Variables.toastMsg.toString())).isDisplayed();
            return true;
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
    }

    public void closeToastMessage(){
        if(isToastPresent() == true){
            WebElement toastCloseBtn = driver.findElement(By.cssSelector(Variables.toastCleanBtn.toString()));
            toastCloseBtn.click();
            Setting.sleep(1);
        }
    }

    public void uploadVideoFromDropBox(String email, String password, String video){
        uploadVideoBtn.click();
        dropBoxUploadBtn.click();
        WebElement chDrBoxBtn = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.cssSelector(Variables.chooseDrBoxBtn.toString())));
        chDrBoxBtn.click();
        String homeWindow = driver.getWindowHandle();
        for(String drBoxWindow : driver.getWindowHandles()){
            driver.switchTo().window(drBoxWindow);
        }
        Setting.sleep(1);
        WebElement emailDrBox = driver.findElement(By.xpath(Variables.emailDropBoxFld.toString()));
        emailDrBox.sendKeys(email);
        WebElement passwordDrBox = driver.findElement(By.xpath(Variables.passwordDropBoxFld.toString()));
        passwordDrBox.sendKeys(password);
        WebElement logInDrBox = driver.findElement(By.cssSelector(Variables.lgnDropBoxBtn.toString()));
        logInDrBox.click();
        WebElement videoName = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(., '" + video + "')]")));
        videoName.click();
        WebElement chooseBtn = driver.findElement(By.id(Variables.chooseBtn.toString()));
        chooseBtn.click();
        driver.switchTo().window(homeWindow);
        Setting.sleep(1);
        WebElement uploadVdSubmitBtn = new WebDriverWait(driver, 120)
                .until(ExpectedConditions.elementToBeClickable(By.id(Variables.confirmBtn.toString())));
        uploadVdSubmitBtn.click();
        Setting.sleep(3);

    }

    public void uploadVideoPc(){
        uploadVideoBtn.click();
        pCUploadBtn.click();
        WebElement videoDropZone = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(Variables.videoDropZone.toString())));
        videoDropZone.click();
        selectFromPc("UploadVideo.exe");
        WebElement uploadVdSubmitBtn = new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.id(Variables.confirmBtn.toString())));
        uploadVdSubmitBtn.click();
        waitUploading(40);
    }

    public void selectFromPc(String path){
        Setting.sleep(1);
        try {
            Runtime.getRuntime().exec("C:\\Users\\Kay\\IdeaProjects\\testsvloop\\" + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadYouTubeVideo(String yLink){
        uploadVideoBtn.click();
        yUploadBtn.click();
        WebElement yLinkField = new WebDriverWait(driver, 50)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(Variables.youTubeLinkFld.toString())));
        yLinkField.sendKeys(yLink);
        previewBtn.click();
        WebElement publishLink = new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.id(Variables.publishBtn.toString())));
        publishLink.click();

    }

    public String getVdDuration(String video){
        WebElement duration = driver.findElement(By.xpath("//tr[contains(., '" + video + "')]/td/span/span"));
        return duration.getText();
    }

    public String getFolderTitle(){
        WebElement folderTitle = driver.findElement(By.cssSelector(Variables.folderTltCell.toString()));
        return folderTitle.getAttribute("name");
    }

    public String getGameTitle(){
        WebElement gameTitle = driver.findElement(By.xpath(Variables.gameTitleCell.toString()));
        return gameTitle.getAttribute("name");
    }

    public boolean isFolderImagePresent(){
        try{
            driver.findElement(By.xpath(Variables.folderImage.toString())).isDisplayed();
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isCheckBoxGamePresent(){
        try {
            driver.findElement(By.xpath(Variables.gameCheckBox.toString())).isDisplayed();
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public void navigateToVideo(){
        Element video = new Element(By.xpath("//tr[contains(., '" + getGameTitle() + "')]"));
        video.waitIsPresent();
        video.click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id(Variables.videoPlayer.toString())));
    }

    public void clean(){
        while (isFolderImagePresent() == true){
            String folder = getFolderTitle();
            deleteFolder(folder);
            Setting.sleep(1);
            Assert.assertFalse(isFolderPresent(folder));
        }
        while (isCheckBoxGamePresent() == true){
            String game = getGameTitle();
            delete(game);
            Setting.sleep(1);
            Assert.assertFalse(isGamePresent(game));
        }
    }
}
