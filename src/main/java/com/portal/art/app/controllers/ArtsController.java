package com.portal.art.app.controllers;

import com.portal.art.app.controllers.requests.ArtRequest;
import com.portal.art.app.repositories.dtos.ArtDto;
import com.portal.art.app.services.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<ArtDto> getArtsForPage(@RequestParam("username") String username, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return this.artService.getArtsForPage(username, pageNumber, pageSize);
    }

    @GetMapping(path = "/user")
    public List<ArtDto> getArtsForPage(@RequestParam("user") String user) {
        return this.artService.getAllArtsForUser(user);
    }

    @DeleteMapping(path = "/delete")
    public HttpStatus removeArt(@RequestParam("art") String art) {
        artService.removeArt(art);
        return HttpStatus.OK;
    }
}
