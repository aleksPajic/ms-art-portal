package com.portal.art.app.repositories;

import com.portal.art.app.repositories.dtos.ArtDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtRepository extends MongoRepository<ArtDto, String> {

    @Query("{ 'artist_username' : ?0 }")
    List<ArtDto> findByThePersonsFirstname(String username);
}
