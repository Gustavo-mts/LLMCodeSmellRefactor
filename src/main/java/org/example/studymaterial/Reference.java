package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    // Substituindo o getter e setter por um método específico
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    public String fetchTitle() {
        return title;
    }

    // Substituindo o getter e setter por um método específico
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    public String fetchDescription() {
        return description;
    }

    // Substituindo o getter e setter por um método específico
    public void updateLink(String newLink) {
        this.link = newLink;
    }

    public String fetchLink() {
        return link;
    }

    // Substituindo o getter e setter por um método específico
    public void updateAccessRights(String newAccessRights) {
        this.accessRights = newAccessRights;
    }

    public String fetchAccessRights() {
        return accessRights;
    }

    // Substituindo o getter e setter por um método específico
    public void updateLicense(String newLicense) {
        this.license = newLicense;
    }

    public String fetchLicense() {
        return license;
    }

    // Substituindo o getter e setter por um método específico
    public void setDownloadability(boolean downloadable) {
        this.isDownloadable = downloadable;
    }

    public boolean checkIfDownloadable() {
        return isDownloadable;
    }

    // Substituindo o getter e setter por um método específico
    public void updateRating(int newRating) {
        this.rating = newRating;
    }

    public int fetchRating() {
        return rating;
    }

    // Substituindo o getter e setter por um método específico
    public void updateLanguage(String newLanguage) {
        this.language = newLanguage;
    }

    public String fetchLanguage() {
        return language;
    }

    // Substituindo o getter e setter por um método específico
    public void updateViewCount(int newViewCount) {
        this.viewCount = newViewCount;
    }

    public int fetchViewCount() {
        return viewCount;
    }

    // Substituindo o getter e setter por um método específico
    public void updateDownloadCount(int newDownloadCount) {
        this.downloadCount = newDownloadCount;
    }

    public int fetchDownloadCount() {
        return downloadCount;
    }

    // Substituindo o getter e setter por um método específico
    public void updateShareCount(int newShareCount) {
        this.shareCount = newShareCount;
    }

    public int fetchShareCount() {
        return shareCount;
    }

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract void setTitle(String s);

    public abstract void setDescription(String a_short_book);
}
