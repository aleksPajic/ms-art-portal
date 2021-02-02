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
        //given
        initSearchParameters(null, "test", null);
        List<Art> arts = new ArrayList<>();
        arts.add(new Art(String.valueOf(0), "name0", "artist0", List.of("technique0")));
        arts.add(new Art(String.valueOf(1), "test1", "artist2", List.of("technique1")));
        arts.add(new Art(String.valueOf(2), "name1", "artist1", List.of("technique2")));
        arts.add(new Art(String.valueOf(3), "name test", "artist5", List.of("technique3")));

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(1).getId()).isEqualTo("3");
    }

    @Test
    public void givenDataListFullAndArtistParameterValueSet_whenSearchArtsCalled_thenDataListIsFilteredByArtistOnly() {
        //given
        initSearchParameters(null, null, "artistTest");
        List<Art> arts = new ArrayList<>();
        arts.add(new Art(String.valueOf(0), "name0", "artistTest0", List.of("technique0")));
        arts.add(new Art(String.valueOf(1), "name1", "artist2", List.of("technique1")));
        arts.add(new Art(String.valueOf(2), "name2", "artist1", List.of("technique2")));
        arts.add(new Art(String.valueOf(3), "name3", "artistTest_test3", List.of("technique3")));

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo("0");
        assertThat(result.get(0).getArtist_username()).isEqualTo("artistTest0");
        assertThat(result.get(1).getId()).isEqualTo("3");
        assertThat(result.get(1).getArtist_username()).isEqualTo("artistTest_test3");
    }

    @Test
    public void givenDataListFullAndTechniqueParameterValueSet_whenSearchArtsCalled_thenDataListIsFilteredByTechniqueOnly() {
        //given
        initSearchParameters("acrylics", null, null);
        List<Art> arts = new ArrayList<>();
        arts.add(new Art(String.valueOf(0), "name0", "artist0", List.of("technique0")));
        arts.add(new Art(String.valueOf(1), "name1", "artist1", List.of("acrylics", "technique2")));
        arts.add(new Art(String.valueOf(2), "name2", "artist2", List.of("technique2", "acrylics")));
        arts.add(new Art(String.valueOf(3), "name3", "artist3", List.of("technique3")));

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTechniques()).contains("acrylics");
        assertThat(result.get(1).getId()).isEqualTo("2");
        assertThat(result.get(1).getTechniques()).contains("acrylics");
    }

    @Test
    public void givenDataListFullAndNameParameterValueNull_whenSearchArtsCalled_thenDataListIsFilteredByTechniqueAndArtist() {
        //given
        initSearchParameters("acrylics", null, "artistTest");
        List<Art> arts = new ArrayList<>();
        arts.add(new Art(String.valueOf(0), "name0", "artistTest0", List.of("technique0")));
        arts.add(new Art(String.valueOf(1), "name test 1", "artistTest2", List.of("acrylics", "technique2")));
        arts.add(new Art(String.valueOf(2), "nameTest 2", "artist2", List.of("technique2", "acrylics")));
        arts.add(new Art(String.valueOf(3), "name3", "artistTest_test3", List.of("technique3")));

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTechniques()).contains("acrylics");
        assertThat(result.get(0).getArtist_username()).contains("artistTest2");
    }

    @Test
    public void givenDataListFullAndArtistParameterValueNull_whenSearchArtsCalled_thenDataListIsFilteredByTechniqueAndName() {
        //given
        initSearchParameters("acrylics", "test", null);
        List<Art> arts = new ArrayList<>();
        arts.add(new Art(String.valueOf(0), "name0", "artistTest0", List.of("technique0")));
        arts.add(new Art(String.valueOf(1), "name test 1", "artistTest2", List.of("acrylics", "technique2")));
        arts.add(new Art(String.valueOf(2), "name_test 2", "artist2", List.of("technique2", "acrylics")));
        arts.add(new Art(String.valueOf(3), "name3", "artistTest_test3", List.of("technique3")));

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTechniques()).contains("acrylics");
        assertThat(result.get(0).getName()).isEqualTo("name test 1");
        assertThat(result.get(1).getId()).isEqualTo("2");
        assertThat(result.get(1).getTechniques()).contains("acrylics");
        assertThat(result.get(1).getName()).isEqualTo("name_test 2");
    }

    @Test
    public void givenDataListFullAndTechniqueParameterValueNull_whenSearchArtsCalled_thenDataListIsFilteredByArtistAndName() {
        //given
        initSearchParameters(null, "test", "artistTest");
        List<Art> arts = new ArrayList<>();
        arts.add(new Art(String.valueOf(0), "name0", "artistTest0", List.of("technique0")));
        arts.add(new Art(String.valueOf(1), "name test 1", "artistTest2", List.of("acrylics", "technique2")));
        arts.add(new Art(String.valueOf(2), "name_test 2", "artist2", List.of("technique2", "acrylics")));
        arts.add(new Art(String.valueOf(3), "name3", "artistTest_test3", List.of("technique3")));

        //when
        List<Art> result = searchArtsService.searchArts(arts, technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getArtist_username()).isEqualTo("artistTest2");
        assertThat(result.get(0).getName()).isEqualTo("name test 1");
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
