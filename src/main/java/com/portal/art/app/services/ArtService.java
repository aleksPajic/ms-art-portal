package com.portal.art.app.services;

import com.portal.art.app.common.exceptions.ArgumentOutOfRangeException;
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

    private final ArtRepository artRepository;

    private final ArtMapper artMapper;

    @Autowired
    public ArtService(ArtRepository artRepository) {
        this.artRepository = artRepository;
        this.artMapper = new ArtMapper();
    }

    public void createArt(ArtRequest artRequest) throws IOException {
        ArtDto artDto = artMapper.map(artRequest);
        artRepository.save(artDto);
    }

    public List<ArtDto> getArtsForPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return this.artRepository.findAll(pageable).getContent();
    }

    public List<Art> searchForArts(String technique, String name, String artist) {
        SearchArtsService searchArtsService = new SearchArtsService();
        List<ArtDto> artDtos = this.artRepository.findAll();
        List<Art> artList = artMapper.map(artDtos);
        return searchArtsService.searchArts(artList, technique, name, artist);
    }

    public List<Art> getSearchArtsForPage(String name, String artist, String technique, int pageNumber, int pageSize) throws ArgumentOutOfRangeException {
        PaginationService<Art> paginationService = new PaginationService<>();
        List<Art> filteredArtsList = searchForArts(technique, name, artist);
        return paginationService.getPageData(filteredArtsList, pageNumber, pageSize);
    }
}
