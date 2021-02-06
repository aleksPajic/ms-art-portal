package com.portal.art.app.services;

import com.portal.art.app.controllers.requests.ArtRequest;
import com.portal.art.app.models.Art;
import com.portal.art.app.models.ArtMapper;
import com.portal.art.app.repositories.ArtRepository;
import com.portal.art.app.repositories.dtos.ArtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ArtService {

    private ArtRepository artRepository;

    private ArtMapper artMapper;

    public ArtService() {
        this.artMapper = new ArtMapper();
    }

    @Autowired
    public ArtService(ArtRepository artRepository) {
        this.artRepository = artRepository;
    }

    public ArtDto createArt(ArtRequest artRequest) throws IOException {
        ArtDto artDto = artMapper.map(artRequest);
        return artRepository.save(artDto);
    }

    public List<ArtDto> getArtsForPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return this.artRepository.findAll(pageable).getContent();
    }

    public List<Art> searchForArts(String technique, String name, String artist) {
        this.artRepository.findAll();
        return null;
    }

}
