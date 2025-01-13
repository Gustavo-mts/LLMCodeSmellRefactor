package org.example.studymaterial;

public class VideoReference extends Reference {
    private boolean isAvailable;
    private String resolution;
    private String frameRate;
    private String videoFormat;
    private String title;
    private String description;

    public VideoReference(String title, String description){
        this.updateTitle(title);
        this.updateDescription(description);
    }

    public VideoReference(boolean isAvailable, String title, String description, String resolution, String frameRate, String videoFormat, String accessRights){
        this.isAvailable = isAvailable;
        this.resolution = resolution;
        this.frameRate = frameRate;
        this.videoFormat = videoFormat;
        this.updateTitle(title);
        this.updateDescription(description);
        this.updateAccessRights(accessRights);
    }

    public void editAvailability(boolean isAvailable, boolean isDownloadable){
        this.isAvailable = isAvailable;
        this.setDownloadability(isDownloadable);
    }

    public boolean handleStreamAvailability(){
        if(!isAvailable){
            return false;
        } else if(!this.checkIfDownloadable()){
            return false;
        }
        return true;

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setTitle(String s) {
        this.title = s;
    }

    @Override
    public void setDescription(String a_short_book) {
        this.description = a_short_book;
    }
}
