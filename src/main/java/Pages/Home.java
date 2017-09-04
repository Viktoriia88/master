package Pages;

import Setting.Setting;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

public class Home extends Setting{

    private WebElement addFolderBtn = driver.findElement(By.id("add_folder"));
    private WebElement folderTitleFld = driver.findElement(By.xpath("//input[@ng-model='folder.current.title']"));
    private WebElement saveFolderBtn = driver.findElement(By.xpath("//button[@ng-click='saveFolder()']"));
    private WebElement addFileBtn = driver.findElement(By.id("add_files"));
    private WebElement fileDropZone = driver.findElement(By.id("file_drop_zone"));
    private WebElement uploadBtn = driver.findElement(By.xpath("//button[@ng-click='saveFiles()']"));

    public void addNewFolder(String folderTitle){
        addFolderBtn.click();
        Setting.sleep(1);
        folderTitleFld.sendKeys(folderTitle);
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
        WebElement editFldItem = driver.findElement(By.cssSelector(".edit_folder"));
        editFldItem.click();
        Setting.sleep(1);
        folderTitleFld.clear();
        folderTitleFld.sendKeys(editFolder);
        saveFolderBtn.click();
    }

    public boolean isEditFolderPresent(String editFolder){
        WebElement folder = driver.findElement(By.xpath("//td/a[text()='" + editFolder + "']"));
        return folder.isDisplayed();
    }

    public void deleteFolder(String folder) {
        WebElement activeItem = driver.findElement(By.xpath("//tr[contains(., '" + folder + "')]//td//a[@class='m-l-sm edit_game_desktop']"));
        activeItem.click();
        WebElement deleteFldItem = driver.findElement(By.cssSelector(".delete_folder"));
        deleteFldItem.click();
        WebElement el = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal-dialog")));
        Setting.sleep(1);
        WebElement deleteBtn = driver.findElement(By.id("confirm-modal-button"));
        deleteBtn.click();
    }

    public void selectFileFromPc() {
        addFileBtn.click();
        Setting.sleep(1);
        fileDropZone.click();
        Setting.sleep(1);
        try {
            Runtime.getRuntime().exec("C:\\Users\\Kay\\IdeaProjects\\testsvloop\\UploadImage.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Setting.sleep(1);
        uploadBtn.click();
    }

    public boolean isFilePresent(String file){
        try {
            driver.findElement(By.xpath("//tr[contains(., '" + file + "')]"));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
