package com.portal.art.app.services;

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

        ArtRepository artRepositoryMock = mock(ArtRepository.class);
        when(artRepositoryMock.findAll()).thenReturn(dtos);
        ArtService artService = new ArtService(artRepositoryMock);

        //when
        artService.searchForArts(tehnique, name, artist);

        //then
        verify(artRepositoryMock, times(1)).findAll();
    }
}
