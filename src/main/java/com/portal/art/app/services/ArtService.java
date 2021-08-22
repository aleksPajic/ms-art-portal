package com.portal.art.app.services;

import com.portal.art.app.controllers.requests.ArtRequest;
import com.portal.art.app.models.ArtMapper;
import com.portal.art.app.repositories.ArtRepository;
import com.portal.art.app.repositories.dtos.ArtDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ArtService {

    final ArtRepository artRepository;

    private final ArtMapper artMapper;

    public ArtService(ArtRepository artRepository) {
        this.artMapper = new ArtMapper();
        this.artRepository = artRepository;
    }

    public void createArt(ArtRequest artRequest) throws IOException {
        ArtDto artDto = artMapper.map(artRequest);
        artRepository.save(artDto);
    }

    public List<ArtDto> getArtsForPage(int pageNumber, int pageSize) {
        Sort sort = Sort.by("date_created").descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return this.artRepository.findAll(pageable).getContent();
    }

    public List<ArtDto> getAllArtsForUser(String username) {
        return this.artRepository.findByThePersonsFirstname(username);
    }
}
