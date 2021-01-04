package com.portal.art.app.controllers;

import com.portal.art.app.repositories.dtos.TechniqueDto;
import com.portal.art.app.services.TechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technique")
public class TechniqueController {

    @Autowired
    TechniqueService techniqueService;

    @GetMapping("/all")
    public List<TechniqueDto> getAll() {
        return techniqueService.getAll();
    }
}
