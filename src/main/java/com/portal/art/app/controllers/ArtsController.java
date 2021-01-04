package com.portal.art.app.controllers;

import com.portal.art.app.repositories.dtos.ArtDto;
import com.portal.art.app.services.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/art")
public class ArtsController {

    @Autowired
    ArtService artService;

    @PutMapping("/create")
    public ArtDto create(@RequestBody ArtDto artDto) {
        return artService.createArt(artDto);
    }
}
