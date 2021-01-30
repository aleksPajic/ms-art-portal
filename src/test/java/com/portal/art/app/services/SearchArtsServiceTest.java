package com.portal.art.app.services;

import com.portal.art.app.models.Art;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


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

}
