package com.portal.art.app.services;

import com.portal.art.app.models.Art;

import java.util.*;
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

        if (name == null && technique != null && !technique.isEmpty() && artist != null && !artist.isEmpty()) {
            return arts.stream().filter((art) -> {
                boolean artistExist = art.getArtist_username().contains(artist);
                boolean techniqueExists = art.getTechniques()
                        .stream()
                        .anyMatch(x -> x.contains(technique));
                return artistExist && techniqueExists;
            }).collect(Collectors.toList());
        }

        if (name != null && !name.isEmpty()) {
            return arts.stream()
                    .filter(art -> art.getName().contains(name))
                    .collect(Collectors.toList());
        }

        if (artist != null && !artist.isEmpty()) {
            return arts.stream()
                    .filter(art -> art.getArtist_username().contains(artist))
                    .collect(Collectors.toList());
        }

        return arts.stream()
                .filter(art -> art.getTechniques().stream().anyMatch(x -> x.contains(technique)))
                .collect(Collectors.toList());
    }
}
