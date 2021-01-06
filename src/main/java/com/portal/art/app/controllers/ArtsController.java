package com.portal.art.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.art.app.controllers.requests.ArtRequest;
import com.portal.art.app.services.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
