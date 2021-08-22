package com.portal.art.app.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.art.app.controllers.requests.ArtRequest;
import com.portal.art.app.repositories.dtos.ArtDto;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ArtMapper {

    public ArtMapper() {
    }

    public ArtDto map(ArtRequest artRequest) throws IOException {
        ArtDto artDto = new ArtDto();
        artDto.setDateCreated(LocalDateTime.now());
        artDto.setImage(new Binary(BsonBinarySubType.BINARY, artRequest.getArtImage().getBytes()));
        artDto.setName(!artRequest.getName().isEmpty() ? artRequest.getName() : null);
        artDto.setDescription(!artRequest.getDescription().isEmpty() ? artRequest.getDescription() : null);
        artDto.setInspiration(!artRequest.getInspiration().isEmpty() ? artRequest.getInspiration() : null);
        artDto.setArtistUsername(artRequest.getArtistUsername());
        ObjectMapper objectMapper = new ObjectMapper();
        String[] techniqueCodes = objectMapper.readValue(artRequest.getTechniqueCodes(), String[].class);
        artDto.setTechniqueCodes(Arrays.asList(techniqueCodes));
        return artDto;
    }
}
