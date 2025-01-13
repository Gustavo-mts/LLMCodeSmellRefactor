package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    // Nested class for AudioMetadata
    public static class AudioMetadata {
        private String title;
        private String description;
        private String link;
        private String accessRights;
        private String license;
        private String language;
        private int rating;
        private int viewCount;
        private int shareCount;
        private boolean isDownloadable;

        // Constructor
        public AudioMetadata(String title, String description, String link, String accessRights, String license,
                             String language, int rating, int viewCount, int shareCount, boolean isDownloadable) {
            this.title = title;
            this.description = description;
            this.link = link;
            this.accessRights = accessRights;
            this.license = license;
            this.language = language;
            this.rating = rating;
            this.viewCount = viewCount;
            this.shareCount = shareCount;
            this.isDownloadable = isDownloadable;
        }

        // Getters for all fields
        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getLink() {
            return link;
        }

        public String getAccessRights() {
            return accessRights;
        }

        public String getLicense() {
            return license;
        }

        public String getLanguage() {
            return language;
        }

        public int getRating() {
            return rating;
        }

        public int getViewCount() {
            return viewCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public boolean isDownloadable() {
            return isDownloadable;
        }
    }

    // Method to edit audio information using AudioMetadata
    public void editAudio(AudioQuality audioQuality, AudioMetadata audioMetadata) {
        // Editing basic audio information
        editBasic(audioMetadata.getTitle(), audioMetadata.getDescription(), audioMetadata.getLink());

        // Setting access rights and license
        this.setAccessRights(audioMetadata.getAccessRights());
        this.setLicense(audioMetadata.getLicense());

        // Setting audio quality
        this.setAudioQuality(audioQuality);

        // Editing video-related attributes (e.g., rating, views, shares)
        editVideoAttributes(audioMetadata.getRating(), audioMetadata.getLanguage(), audioMetadata.getViewCount(),
                audioMetadata.getShareCount(), audioMetadata.isDownloadable());
    }

    // Method to handle additional video attributes
    private void editVideoAttributes(int rating, String language, int viewCount, int shareCount, boolean isDownloadable) {
        this.setRating(rating);
        this.setShareCount(shareCount);
        this.setViewCount(viewCount);
        this.setDownloadable(isDownloadable);
        this.setLanguage(language);
    }

    // Method to edit basic audio information
    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }

    // Adapter method to convert a list of properties and integers into an AudioMetadata object
    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        // Ensure the lists have the correct number of elements
        if (properties.size() < 6 || intProperties.size() < 3) {
            throw new IllegalArgumentException("Insufficient properties or intProperties provided.");
        }

        // Create the AudioMetadata object using values from the lists
        AudioMetadata metadata = new AudioMetadata(
                properties.get(0),  // title
                properties.get(1),  // description
                properties.get(2),  // link
                properties.get(3),  // accessRights
                properties.get(4),  // license
                properties.get(5),  // language
                intProperties.get(0), // rating
                intProperties.get(1), // viewCount
                intProperties.get(2), // shareCount
                isDownloadable     // isDownloadable
        );

        // Call the editAudio method with the audioQuality and metadata
        editAudio(audioQuality, metadata);
    }
}
