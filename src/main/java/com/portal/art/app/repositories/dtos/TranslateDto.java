package com.portal.art.app.repositories.dtos;

import org.springframework.data.mongodb.core.mapping.Field;

public class TranslateDto {

    @Field(name = "lang")
    private String lang;

    @Field(name = "value")
    private String value;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
