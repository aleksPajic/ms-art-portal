package com.portal.art.app.services;

import com.portal.art.app.common.exceptions.ArgumentOutOfRangeException;
import com.portal.art.app.models.Art;
import com.portal.art.app.repositories.ArtRepository;
import com.portal.art.app.repositories.dtos.ArtDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ArtServiceTest {

    @Test
    void givenSearchParameters_whenSearchForArtsCalled_thenAllArtsFromRepositoryAreRetrieved() {
        //given
        String technique = "technique";
        String name = "name";
        String artist = "artist";
        List<ArtDto> artDtoModels = new ArrayList<>();
        artDtoModels.add(createArtModel("0", "name0", "artist1", List.of("technique")));
        artDtoModels.add(createArtModel("1", "test0", "art_test1", List.of("tec_test")));
        artDtoModels.add(createArtModel("2", "name2", "artist2", List.of("technique2")));

        ArtRepository artRepositoryMock = mock(ArtRepository.class);
        when(artRepositoryMock.findAll()).thenReturn(artDtoModels);
        ArtService artService = new ArtService(artRepositoryMock);

        //when
        artService.searchForArts(technique, name, artist);

        //then
        verify(artRepositoryMock, times(1)).findAll();
    }

    @Test
    void givenSearchParameters_whenSearchForArtsCalled_thenFilteredArtModelsAreReturned() {
        //given
        String technique = "technique";
        String name = "name";
        String artist = "artist";

        List<ArtDto> artDtoModels = new ArrayList<>();
        artDtoModels.add(createArtModel("0", "name0", "artist1", List.of("technique")));
        artDtoModels.add(createArtModel("1", "test0", "art_test1", List.of("tec_test")));
        artDtoModels.add(createArtModel("2", "name2", "artist2", List.of("technique2")));

        ArtRepository artRepositoryMock = mock(ArtRepository.class);
        when(artRepositoryMock.findAll()).thenReturn(artDtoModels);
        ArtService artService = new ArtService(artRepositoryMock);

        //when
        List<Art> result = artService.searchForArts(technique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo("0");
        assertThat(result.get(1).getId()).isEqualTo("2");
    }

    @Test
    void givenSearchAndPageParameters_whenGetSearchArtsForPageCalled_thenFilteredArtModelsForPageAreReturned() throws ArgumentOutOfRangeException {
        //given
        String technique = "technique";
        String name = "name";
        String artist = "artist";
        int pageNumber = 1;
        int pageSize = 1;

        List<ArtDto> artDtoModels = new ArrayList<>();
        artDtoModels.add(createArtModel("0", "name0", "artist1", List.of("technique")));
        artDtoModels.add(createArtModel("1", "test0", "art_test1", List.of("tec_test")));
        artDtoModels.add(createArtModel("2", "name2", "artist2", List.of("technique2")));

        ArtRepository artRepositoryMock = mock(ArtRepository.class);
        when(artRepositoryMock.findAll()).thenReturn(artDtoModels);
        ArtService artService = new ArtService(artRepositoryMock);

        //when
        List<Art> result = artService.getSearchArtsForPage(name, artist, technique, pageNumber, pageSize);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    private ArtDto createArtModel(String id, String name, String username, List<String> techniques) {
        ArtDto artDto = new ArtDto();
        artDto.setId(id);
        artDto.setName(name);
        artDto.setArtist_username(username);
        artDto.setTechniqueCodes(techniques);
        return artDto;
    }
}
