package com.portal.art.app.controllers.requests;

import org.springframework.web.multipart.MultipartFile;

public class ArtRequest {

    private MultipartFile artImage;
    private String name;
    private String inspiration;
    private String artistUsername;
    private String description;
    private String techniqueCodes;


    public MultipartFile getArtImage() {
        return artImage;
    }

    public void setArtImage(MultipartFile artImage) {
        this.artImage = artImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInspiration() {
        return inspiration;
    }

    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechniqueCodes() {
        return techniqueCodes;
    }

    public void setTechniqueCodes(String techniqueCodes) {
        this.techniqueCodes = techniqueCodes;
    }

    public String getArtistUsername() {
        return artistUsername;
    }

    public void setArtistUsername(String artistUsername) {
        this.artistUsername = artistUsername;
    }
}
