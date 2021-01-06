package com.portal.art.app.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.art.app.controllers.requests.ArtRequest;
import com.portal.art.app.models.ArtMapper;
import com.portal.art.app.repositories.ArtRepository;
import com.portal.art.app.repositories.dtos.ArtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ArtService {

    @Autowired
    ArtRepository artRepository;

    private ArtMapper artMapper;

    public ArtService() {
        this.artMapper = new ArtMapper();
    }

    public ArtDto createArt(ArtRequest artRequest) throws IOException {
        ArtDto artDto = artMapper.map(artRequest);
        return artRepository.save(artDto);
    }

}
