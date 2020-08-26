package com.pfe.loginpartjwt.repositories;

import com.pfe.loginpartjwt.models.Queries;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;



public interface QueryRepository extends JpaRepository<Queries,Long> {

    Optional<Queries> findByTitre(String titre);




}
