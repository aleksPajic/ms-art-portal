package com.portal.art.app.repositories;

import com.portal.art.app.repositories.dtos.TechniqueDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechniqueRepository extends MongoRepository<TechniqueDto, String> {
}
