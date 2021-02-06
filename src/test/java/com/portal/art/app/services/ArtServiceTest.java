package com.portal.art.app.services;

import com.portal.art.app.common.exceptions.ArgumentOutOfRangeException;
import com.portal.art.app.models.Art;
import com.portal.art.app.models.ArtMapper;
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
        String tehnique = "technique";
        String name = "name";
        String artist = "artist";
        List<ArtDto> dtos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            dtos.add(new ArtDto());
        }

        List<Art> artModels = new ArrayList<>();
        artModels.add(createArtModel("0", "name0", "artist1", List.of("technique")));
        artModels.add(createArtModel("1", "test0", "art_test1", List.of("tec_test")));
        artModels.add(createArtModel("2", "name2", "artist2", List.of("technique2")));

        ArtRepository artRepositoryMock = mock(ArtRepository.class);
        when(artRepositoryMock.findAll()).thenReturn(dtos);
        ArtMapper artMapperMock = mock(ArtMapper.class);
        when(artMapperMock.map(dtos)).thenReturn(artModels);
        ArtService artService = new ArtService(artRepositoryMock, artMapperMock);

        //when
        artService.searchForArts(tehnique, name, artist);

        //then
        verify(artRepositoryMock, times(1)).findAll();
    }

    @Test
    void givenSearchParameters_whenSearchForArtsCalled_thenAllArtDtosAreMappedToArtModel() {
        //given
        String tehnique = "technique";
        String name = "name";
        String artist = "artist";
        List<ArtDto> dtos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            dtos.add(new ArtDto());
        }

        List<Art> artModels = new ArrayList<>();
        artModels.add(createArtModel("0", "name0", "artist1", List.of("technique")));
        artModels.add(createArtModel("1", "test0", "art_test1", List.of("tec_test")));
        artModels.add(createArtModel("2", "name2", "artist2", List.of("technique2")));

        ArtRepository artRepositoryMock = mock(ArtRepository.class);
        when(artRepositoryMock.findAll()).thenReturn(dtos);
        ArtMapper artMapperMock = mock(ArtMapper.class);
        when(artMapperMock.map(dtos)).thenReturn(artModels);
        ArtService artService = new ArtService(artRepositoryMock, artMapperMock);

        //when
        artService.searchForArts(tehnique, name, artist);

        //then
        verify(artMapperMock, times(1)).map(eq(dtos));
    }

    @Test
    void givenSearchParameters_whenSearchForArtsCalled_thenFilteredArtModelsAreReturned() {
        //given
        String tehnique = "technique";
        String name = "name";
        String artist = "artist";

        List<ArtDto> dtos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            dtos.add(new ArtDto());
        }

        List<Art> artModels = new ArrayList<>();
        artModels.add(createArtModel("0", "name0", "artist1", List.of("technique")));
        artModels.add(createArtModel("1", "test0", "art_test1", List.of("tec_test")));
        artModels.add(createArtModel("2", "name2", "artist2", List.of("technique2")));

        ArtRepository artRepositoryMock = mock(ArtRepository.class);
        when(artRepositoryMock.findAll()).thenReturn(dtos);
        ArtMapper artMapperMock = mock(ArtMapper.class);
        when(artMapperMock.map(dtos)).thenReturn(artModels);
        ArtService artService = new ArtService(artRepositoryMock, artMapperMock);

        //when
        List<Art> result = artService.searchForArts(tehnique, name, artist);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo("0");
        assertThat(result.get(1).getId()).isEqualTo("2");
    }

    @Test
    void givenSearchAndPageParameters_whenGetSearchArtsForPageCalled_thenFilteredArtModelsForPageAreReturned() throws ArgumentOutOfRangeException {
        //given
        String tehnique = "technique";
        String name = "name";
        String artist = "artist";
        int pageNumber = 1;
        int pageSize = 1;

        List<ArtDto> dtos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            dtos.add(new ArtDto());
        }

        List<Art> artModels = new ArrayList<>();
        artModels.add(createArtModel("0", "name0", "artist1", List.of("technique")));
        artModels.add(createArtModel("1", "test0", "art_test1", List.of("tec_test")));
        artModels.add(createArtModel("2", "name2", "artist2", List.of("technique2")));

        ArtRepository artRepositoryMock = mock(ArtRepository.class);
        when(artRepositoryMock.findAll()).thenReturn(dtos);
        ArtMapper artMapperMock = mock(ArtMapper.class);
        when(artMapperMock.map(dtos)).thenReturn(artModels);
        ArtService artService = new ArtService(artRepositoryMock, artMapperMock);

        //when
        List<Art> result = artService.getSearchArtsForPage(name, artist, tehnique, pageNumber, pageSize);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    private Art createArtModel(String id, String name, String username, List<String> techniques) {
        return new Art(id, name, username, techniques);
    }
}
