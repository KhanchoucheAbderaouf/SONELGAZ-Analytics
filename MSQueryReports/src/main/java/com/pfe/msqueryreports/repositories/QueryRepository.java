package com.pfe.msqueryreports.repositories;


import com.pfe.msqueryreports.models.Queries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface QueryRepository extends JpaRepository<Queries,Long> {

    Optional<Queries> findByTitre(String titre);




}
