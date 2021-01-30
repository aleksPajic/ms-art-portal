package com.portal.art.app.services;

import com.portal.art.app.models.Art;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SearchArtsService {

    public List<Art> searchArts(List<Art> arts, String technique, String name, String artist) {
        if(arts == null) {
            throw new IllegalArgumentException();
        }

        if((Objects.isNull(technique) || technique.isEmpty()) && (Objects.isNull(name) || name.isEmpty()) &&
                (Objects.isNull(artist) || artist.isEmpty())) {
            return arts;
        }
        
        return Collections.emptyList();
    }
}
