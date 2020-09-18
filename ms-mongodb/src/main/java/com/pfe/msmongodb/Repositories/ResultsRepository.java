package com.pfe.msmongodb.Repositories;


import com.pfe.msmongodb.Models.Results;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultsRepository extends MongoRepository<Results, String> {

    Optional<Results> findByTitle(String title);
}
