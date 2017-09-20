package Enums;

import org.openqa.selenium.By;

public enum  Variables {
    //Sign Page
    logInBtn("user_login"),
    emailFld("user_email"),
    passwordFld("user_password"),
    contactUsLink("contact_us"),
    signOutBtn("signout_nav"),
    vloopLogo(".signin-logo"),

    //Home Page
    addFldBtn("add_folder"),
    addFileBtn("add_files"),
    deleteVideoBtn("delete_videos"),
    updateVideoBtn("update_video_button"),
    uploadDrBsBtn("dropbox_upload_btn"),
    uploadVideoPcBtn("file_upload_btn"),
    youTubeUploadBtn("youtube_upload_btn"),
    previewYouTubeBtn("preview_youtube_btn"),
    addVideoBtn("add_video"),
    toastMsg(".toast"),
    chooseDrBoxBtn(".dropbox-dropin-btn"),
    chooseBtn("select-btn"),
    confirmBtn("vid_submit"),
    videoDropZone("video_drop_zone"),
    youTubeLinkFld("youtube_link"),
    publishBtn("publish_youtube_link"),
    folderTltFld("//input[@ng-model='folder.current.title']"),
    editFld(".form-control.ng-not-empty"),
    saveFldBtn("//button[@ng-click='saveFolder()']"),
    uploadBtn("//button[@ng-click='saveFiles()']"),
    progressBar(".progress-upload"),
    actionItem(".select-actions"),
    editFlItem("//a[@ng-click='openEditGameModal(row)']"),
    toastCleanBtn(".toast-close-button"),
    emailDropBoxFld("//input[@name='login_email']"),
    passwordDropBoxFld("//input[@name='login_password']"),
    lgnDropBoxBtn(".login-button"),
    folderTltCell(".icheckbox_square-green.ng-hide"),
    gameTitleCell("//div[@class='icheckbox_square-green' and @aria-hidden='false']"),
    folderImage("//img[@src='https://s3-us-west-2.amazonaws.com/vloop-static/Open+Folder-528.png']"),
    gameCheckBox("//div[@class='icheckbox_square-green' and @aria-hidden='false']"),

    //Game Page
    currentTimeIcon("current-time"),
    playPauseBtn("play-pause-icon"),
    videoPlayer("game_analyze_video"),
    skipForwardBtn("forward-button"),
    skipBackBtn("rewind-button"),
    //createClipDrawBtn("unhide-clip-button"),
    toolCanvasBar("fabrick-controls"),
    pictureIcon("picture-icon"),
    //uploadImageBtn("upload-image"),
    imgCloseBtn("canvas_bg_img_close_btn"),
    saveCanvasBtn("btn-cnv-prim"),
    uploadCanvasImg("//img[@src='https://icons.vloop.io/picture-48.png']");



    private String text;

    Variables(String text) {
        this.text = text;
    }

        public String toString()
    {

        return this.text;
    }
}
