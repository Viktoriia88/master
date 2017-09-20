package Pages;

import Element.Element;
import Setting.Setting;
import org.openqa.selenium.By;

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

    public String getCurrentTime(){
        return curTimeIcon.getText();
    }

    public int getTimeSec(){
        String[] a = getCurrentTime().split(":");
        return Integer.parseInt(a[1]);
    }

    public void waitPlay(){
        for (int i = 0; i < 10; i++){
            if (getTimeSec() > 0){
                break;
            }
            Setting.sleep(1);
        }
    }

    public int playVideo(){
        int timeStart = getTimeSec();
        playPauseBtn.isClickable();
        playPauseBtn.click();
        waitPlay();
        playPauseBtn.click();
        int timeEnd = getTimeSec();
        int time = timeEnd - timeStart;
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
}
