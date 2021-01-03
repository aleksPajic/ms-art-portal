package com.portal.art.app.services;

import com.portal.art.app.repositories.ArtRepository;
import com.portal.art.app.repositories.dtos.ArtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtService {

    @Autowired
    ArtRepository artRepository;

    public ArtDto createArt(ArtDto artDto) {
        return artRepository.save(artDto);
    }
}
