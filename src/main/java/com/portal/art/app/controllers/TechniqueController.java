package com.portal.art.app.controllers;

import com.portal.art.app.repositories.dtos.TechniqueDto;
import com.portal.art.app.services.TechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/codes/all")
    public List<TechniqueDto> getByCodes(@RequestBody List<String> codes) {
        return techniqueService.getByCodes(codes);
    }
}
