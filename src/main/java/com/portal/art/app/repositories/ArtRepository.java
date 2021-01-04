package com.portal.art.app.repositories;

import com.portal.art.app.repositories.dtos.ArtDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtRepository extends MongoRepository<ArtDto, String> {
}
