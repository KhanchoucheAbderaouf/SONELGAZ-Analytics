package com.pfe.msmongodb.Repositories;

import com.pfe.msmongodb.Models.Dashboard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DashboardRepository extends MongoRepository<Dashboard, String> {

    Optional<Dashboard> findByTitle(String title);

}
