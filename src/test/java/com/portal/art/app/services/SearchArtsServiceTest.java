package com.portal.art.app.services;

import com.portal.art.app.models.Art;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class SearchArtsServiceTest {

    SearchArtsService searchArtsService;
    String technique;
    String name;
    String artist;

    @BeforeEach
    public void initService() {
        searchArtsService = new SearchArtsService();
    }

    @Test
    public void givenDataListNullAndSearchParameters_whenSearchArtsCalled_thenThrowIllegalArgumentException() {
        //given
        List<Art> arts = null;
        initSearchParameters("technique", "name", "artist");

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //when
            searchArtsService.searchArts(arts, technique, name, artist);
        });
    }

    @Test
    public void givenDataListEmptyAndSearchParameters_whenSearchArtsCalled_thenReturnEmptyArray() {
        //given
        initSearchParameters("technique", "name", "artist");
        List<Art> arts = createArtDataOfSize(0);

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void givenDataListFullAndSearchParametersAllEmpty_whenSearchArtsCalled_thenReturnDataListUnchanged() {
        //given
        initSearchParameters("", "", "");
        List<Art> arts = createArtDataOfSize(3);

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(arts.size());
        assertThat(result.get(0).getId()).isEqualTo(arts.get(0).getId());
        assertThat(result.get(1).getId()).isEqualTo(arts.get(1).getId());
        assertThat(result.get(2).getId()).isEqualTo(arts.get(2).getId());
    }

    @Test
    public void givenDataListFullAndSearchParametersAllNull_whenSearchArtsCalled_thenReturnDataListUnchanged() {
        //given
        initSearchParameters(null, null, null);
        List<Art> arts = createArtDataOfSize(3);

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(arts.size());
        assertThat(result.get(0).getId()).isEqualTo(arts.get(0).getId());
        assertThat(result.get(1).getId()).isEqualTo(arts.get(1).getId());
        assertThat(result.get(2).getId()).isEqualTo(arts.get(2).getId());
    }

    @Test
    public void givenDataListFullAndNameParameterValueSet_whenSearchArtsCalled_thenDataListIsFilteredByNameOnly() {

    }

    private void initSearchParameters(String technique, String name, String artist) {
        this.technique = technique;
        this.name = name;
        this.artist = artist;
    }

    private List<Art> createArtDataOfSize(int size) {
        List<Art> arts = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arts.add(new Art(
                    String.valueOf(i),
                    "name" + i,
                    "artist" + i,
                    List.of("technique" + i)
            ));
        }
        return arts;
    }
}
