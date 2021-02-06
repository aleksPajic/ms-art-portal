package com.portal.art.app.models;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class Art  implements PortalPageable{

    private String id;

    private String name;

    private String artist_username;

    private String description;

    private List<String> techniques;

    private String inspiration;

    private Binary image;

    public Art() {
    }

    public Art(String id, String name, String artist_username, List<String> techniques) {
        this.id = id;
        this.name = name;
        this.artist_username = artist_username;
        this.techniques = techniques;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist_username() {
        return artist_username;
    }

    public void setArtist_username(String artist_username) {
        this.artist_username = artist_username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTechniques() {
        return techniques;
    }

    public void setTechniques(List<String> techniques) {
        this.techniques = techniques;
    }

    public String getInspiration() {
        return inspiration;
    }

    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }
}
