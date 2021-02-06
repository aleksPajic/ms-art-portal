package com.portal.art.app.models;

import com.portal.art.app.repositories.dtos.ArtDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArtMapperTest {


    @Test
    public void givenArtDto_whenMapMethodCalled_ThenReturnMappedArtModel() {
        //given
        ArtMapper artMapper = new ArtMapper();
        ArtDto artDto = createArtDto("1", "name1", "artist1");

        //when
        Art artModel = artMapper.map(artDto);

        //then
        assertThat(artModel).isNotNull();
        assertThat(artModel.getId()).isEqualTo("1");
        assertThat(artModel.getName()).isEqualTo("name1");
        assertThat(artModel.getArtist_username()).isEqualTo("artist1");
    }

    @Test
    public void givenArtDtoList_whenMapMethodCalled_ThenReturnListOfMappedArtModel() {
        
    }

    private ArtDto createArtDto(String id, String name, String artist) {
        ArtDto artDto = new ArtDto();
        artDto.setId(id);
        artDto.setName(name);
        artDto.setArtist_username(artist);
        return artDto;
    }
}
