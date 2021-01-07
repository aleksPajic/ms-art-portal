package com.portal.art.app.services;

import com.portal.art.app.repositories.TechniqueRepository;
import com.portal.art.app.repositories.dtos.TechniqueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechniqueService {

    @Autowired
    TechniqueRepository techniqueRepository;

    public List<TechniqueDto> getAll() {
        return techniqueRepository.findAll();
    }

    public List<TechniqueDto> getByCodes(List<String> codes) {
        return techniqueRepository.findAllByCodeIn(codes);
    }
}
