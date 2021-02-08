package com.portal.art.app.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.art.app.controllers.requests.ArtRequest;
import com.portal.art.app.repositories.dtos.ArtDto;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtMapper {

    public ArtMapper() {
    }

    public ArtDto map(ArtRequest artRequest) throws IOException {
        ArtDto artDto = new ArtDto();
        artDto.setImage(new Binary(BsonBinarySubType.BINARY, artRequest.getArtImage().getBytes()));
        artDto.setName(!artRequest.getName().isEmpty() ? artRequest.getName() : null);
        artDto.setDescription(!artRequest.getDescription().isEmpty() ? artRequest.getDescription() : null);
        artDto.setInspiration(!artRequest.getInspiration().isEmpty() ? artRequest.getInspiration() : null);
        artDto.setArtist_username(artRequest.getArtistUsername());
        ObjectMapper objectMapper = new ObjectMapper();
        String[] techniqueCodes = objectMapper.readValue(artRequest.getTechniqueCodes(), String[].class);
        artDto.setTechniqueCodes(Arrays.asList(techniqueCodes));
        return artDto;
    }

    public Art map(ArtDto artDto) {
        Art art = new Art();
        art.setId(artDto.getId());
        art.setName(artDto.getName());
        art.setArtist_username(artDto.getArtist_username());
        art.setImage(artDto.getImage());
        art.setDescription(artDto.getDescription());
        art.setInspiration(artDto.getInspiration());
        art.setTechniques(artDto.getTechniqueCodes());
        return art;
    }

    public List<Art> map(List<ArtDto> artDtos) {
        List<Art> artList = new ArrayList<>();

        for (ArtDto artDto : artDtos) {
            artList.add(map(artDto));
        }

        return artList;
    }
}
