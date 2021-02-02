package com.portal.art.app.services;

import com.portal.art.app.models.Art;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchArtsService {

    public List<Art> searchArts(List<Art> arts, String technique, String name, String artist) {
        if (arts == null) {
            throw new IllegalArgumentException();
        }

        if ((Objects.isNull(technique) || technique.isEmpty()) && (Objects.isNull(name) || name.isEmpty()) &&
                (Objects.isNull(artist) || artist.isEmpty())) {
            return arts;
        }

        if (name != null && !name.isEmpty()) {
            return arts.stream()
                    .filter(art -> art.getName().contains(name))
                    .collect(Collectors.toList());
        }

        List<Art> result = arts;
        if (artist != null && !artist.isEmpty()) {
            result = arts.stream()
                    .filter(art -> art.getArtist_username().contains(artist))
                    .collect(Collectors.toList());
        }

        if (technique != null && !technique.isEmpty()) {
            result = result.stream()
                    .filter(art -> art.getTechniques().stream().anyMatch(x -> x.contains(technique)))
                    .collect(Collectors.toList());
        }

        return result;
    }
}
