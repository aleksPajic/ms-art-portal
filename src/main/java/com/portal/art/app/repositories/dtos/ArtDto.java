package com.portal.art.app.repositories.dtos;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "art")
public class ArtDto {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "artist_username")
    private String artistUsername;

    @Field(name = "description")
    private String description;

    @Field(name = "technique_codes")
    private List<String> techniqueCodes;

    @Field(name = "inspiration")
    private String inspiration;

    @Field(name = "image")
    private Binary image;

    @Field(name = "date_created")
    private LocalDateTime dateCreated;

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

    public String getArtistUsername() {
        return artistUsername;
    }

    public void setArtistUsername(String artistUsername) {
        this.artistUsername = artistUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTechniqueCodes() {
        return techniqueCodes;
    }

    public void setTechniqueCodes(List<String> techniqueCodes) {
        this.techniqueCodes = techniqueCodes;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
