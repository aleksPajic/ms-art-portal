package com.portal.art.app.services;

import com.portal.art.app.models.Art;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class SearchArtsServiceTest {

    @Test
    public void givenDataListNullAndSearchParameters_whenSearchArtsCalled_thenThrowIllegalArgumentException() {
        //given
        SearchArtsService searchArtsService = new SearchArtsService();
        List<Art> arts = null;
        String technique = "technique";
        String name = "name";
        String artist = "artist";

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //when
            searchArtsService.searchArts(arts, technique, name, artist);
        });
    }

    @Test
    public void givenDataListEmptyAndSearchParameters_whenSearchArtsCalled_thenReturnEmptyArray() {
        //given
        SearchArtsService searchArtsService = new SearchArtsService();
        List<Art> arts = new ArrayList<>();
        String technique = "technique";
        String name = "name";
        String artist = "artist";

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void givenDataListFullAndSearchParametersAllEmpty_whenSearchArtsCalled_thenReturnDataListUnchanged() {
        //given
        SearchArtsService searchArtsService = new SearchArtsService();
        List<Art> arts = new ArrayList<>();
        arts.add(new Art("1", "name1", "artist1", List.of("technique1")));
        arts.add(new Art("2", "name2", "artist2", List.of("technique2")));
        arts.add(new Art("3", "name3", "artist3", List.of("technique3")));

        String technique = "";
        String name = "";
        String artist = "";

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(arts.size());
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(1).getId()).isEqualTo("2");
        assertThat(result.get(2).getId()).isEqualTo("3");
    }

}
