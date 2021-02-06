package com.portal.art.app.models;

import com.portal.art.app.repositories.dtos.ArtDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        //given
        ArtMapper artMapper = new ArtMapper();
        List<ArtDto> artDtos = new ArrayList<>();
        artDtos.add(createArtDto("1", "name1", "artist1"));
        artDtos.add(createArtDto("2", "name2", "artist2"));

        //when
        List<Art> artModels = artMapper.map(artDtos);

        //then
        assertThat(artModels).isNotNull();
        assertThat(artModels.size()).isEqualTo(2);
        assertThat(artModels.get(0).getId()).isEqualTo("1");
        assertThat(artModels.get(0).getName()).isEqualTo("name1");
        assertThat(artModels.get(0).getArtist_username()).isEqualTo("artist1");
        assertThat(artModels.get(1).getId()).isEqualTo("2");
        assertThat(artModels.get(1).getName()).isEqualTo("name2");
        assertThat(artModels.get(1).getArtist_username()).isEqualTo("artist2");
    }

    private ArtDto createArtDto(String id, String name, String artist) {
        ArtDto artDto = new ArtDto();
        artDto.setId(id);
        artDto.setName(name);
        artDto.setArtist_username(artist);
        return artDto;
    }
}
