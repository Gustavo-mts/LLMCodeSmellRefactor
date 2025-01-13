package org.example.studymaterial;

public class TextReference extends Reference{
    private int wordCount;
    private String format;
    private String title;
    private String description;

    public TextReference(String title, String language, int wordCount, String format, String accessRights) {
        this.wordCount = wordCount;
        this.format = format;
        this.updateTitle(title);
        this.updateLanguage(language);
        this.updateAccessRights(accessRights);
    }

    public void editAccess(String accessRights, String format, int wordCount) {
        this.updateAccessRights(accessRights);
        this.format = format;
        this.wordCount = wordCount;
    }

    public boolean handleTextAccess(){
        if(fetchAccessRights() != "Public"){
            return false;
        } else if (this.format != "pdf"){
            return false;
        } else if (this.wordCount == 0){
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
