package com.portal.art.app.repositories;

import com.portal.art.app.repositories.dtos.ArtDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtRepository extends MongoRepository<ArtDto, String> {

    @Query("{ 'artist_username' : ?0 }")
    List<ArtDto> findByThePersonsFirstname(String username);

    @Query("{  'artist_username': { $not : { $eq: ?0 }} }")
    Page<ArtDto> findArtsNotInUsername(String username, Pageable pageable);
}
