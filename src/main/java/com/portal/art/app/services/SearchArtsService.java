package com.portal.art.app.services;

import com.portal.art.app.models.Art;

import java.util.*;
import java.util.stream.Collectors;

public class SearchArtsService {

    public List<Art> searchArts(List<Art> arts, String technique, String name, String artist) {
        if(arts == null) {
            throw new IllegalArgumentException();
        }

        if((Objects.isNull(technique) || technique.isEmpty()) && (Objects.isNull(name) || name.isEmpty()) &&
                (Objects.isNull(artist) || artist.isEmpty())) {
            return arts;
        }

        if (name == null && technique != null && !technique.isEmpty() && artist != null && !artist.isEmpty()) {
            List<Art> result = arts.stream().filter((art) -> {
                boolean artistExist = art.getArtist_username().contains(artist);
                boolean techniqueExists = art.getTechniques()
                        .stream()
                        .anyMatch(x -> x.contains(technique));
                return artistExist && techniqueExists;
            }).collect(Collectors.toList());
            return result;
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

        if (technique != null && !technique.isEmpty()) {
            List<Art> result = new ArrayList<>();
            for (Art item : arts) {
                Optional<String> techniqueOptional = item.getTechniques()
                        .stream()
                        .filter(x -> x.contains(technique))
                        .findAny();
                if (techniqueOptional.isPresent()) {
                    result.add(item);
                }
            }
            return result;
        }

        return Collections.emptyList();
    }
}
