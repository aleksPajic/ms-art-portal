package com.portal.art.app.repositories.dtos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "technique")
public class TechniqueDto {

    @Id
    private String id;

    @Field(name = "code")
    private String code;

    @Field(name = "translations")
    private List<TranslateDto> translations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TranslateDto> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslateDto> translations) {
        this.translations = translations;
    }
}
