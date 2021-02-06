package com.portal.art.app.services;

import com.portal.art.app.models.Art;
import com.portal.art.app.models.ArtMapper;
import com.portal.art.app.repositories.ArtRepository;
import com.portal.art.app.repositories.dtos.ArtDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ArtServiceTest {

    @Test
    void givenSearchParameters_whenSearchForArtsCalled_thenAllArtsFromRepositoryAreRetrieved() {
        //given
        String tehnique = "technique";
        String name = "name";
        String artist = "artist";
        List<ArtDto> dtos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dtos.add(new ArtDto());
        }

        List<Art> artModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            artModels.add(new Art());
        }

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
        for (int i = 0; i < 10; i++) {
            dtos.add(new ArtDto());
        }

        List<Art> artModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            artModels.add(new Art());
        }

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
}
