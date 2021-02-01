package com.portal.art.app.services;

import com.portal.art.app.models.Art;

import java.util.ArrayList;
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

        if (name != null && !name.isEmpty()) {
            List<Art> result = new ArrayList<>();
            for (Art item : arts) {
                if (item.getName().contains(name)) {
                    result.add(item);
                }
            }
            return result;
        }

        if (artist != null && !artist.isEmpty()) {
            List<Art> result = new ArrayList<>();
            for (Art item : arts) {
                if (item.getArtist_username().contains(artist)) {
                    result.add(item);
                }
            }
            return result;
        }
        
        return Collections.emptyList();
    }
}
