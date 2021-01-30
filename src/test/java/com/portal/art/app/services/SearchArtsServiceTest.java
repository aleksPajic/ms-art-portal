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

    }

}
