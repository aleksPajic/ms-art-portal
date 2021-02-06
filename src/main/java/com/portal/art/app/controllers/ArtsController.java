package com.portal.art.app.controllers;

import com.portal.art.app.common.exceptions.ArgumentOutOfRangeException;
import com.portal.art.app.controllers.requests.ArtRequest;
import com.portal.art.app.controllers.requests.SearchPagedRequest;
import com.portal.art.app.models.Art;
import com.portal.art.app.repositories.dtos.ArtDto;
import com.portal.art.app.services.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/art")
public class ArtsController {

    @Autowired
    ArtService artService;

    @RequestMapping(
            path = "/create",
            method = RequestMethod.POST
    )
    public HttpStatus create(@ModelAttribute ArtRequest artRequest) {
        try {
            artService.createArt(artRequest);
        } catch (IOException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(path = "/page")
    public List<ArtDto> getArtsForPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return this.artService.getArtsForPage(pageNumber, pageSize);
    }

    @GetMapping(path = "/search")
    public List<Art> searchForArts(@RequestParam("technique") String technique, @RequestParam("name") String name,
                                   @RequestParam("artist") String artist) {
        return this.artService.searchForArts(technique, name, artist);
    }

    @GetMapping(path = "/search/page")
    public List<Art> getArtsForPage(@RequestBody() SearchPagedRequest searchPagedRequest) {
        try {
            return this.artService.getSearchArtsForPage(
                    searchPagedRequest.getName(),
                    searchPagedRequest.getArtist(),
                    searchPagedRequest.getTechnique(),
                    searchPagedRequest.getPageNumber(),
                    searchPagedRequest.getPageSize()
            );
        } catch (ArgumentOutOfRangeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
