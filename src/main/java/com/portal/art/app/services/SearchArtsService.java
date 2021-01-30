package com.portal.art.app.services;

import com.portal.art.app.models.Art;

import java.util.Collections;
import java.util.List;

public class SearchArtsService {

    public List<Art> searchArts(List<Art> arts, String technique, String name, String artist) {
        if(arts == null) {
            throw new IllegalArgumentException();
        }

        if(arts.size() > 0 && technique.isEmpty() && name.isEmpty() && artist.isEmpty()) {
            return arts;
        }
        
        return Collections.emptyList();
    }
}
