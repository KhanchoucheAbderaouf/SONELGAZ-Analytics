package com.pfe.loginpartjwt.repositories;

import com.pfe.loginpartjwt.models.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface QueryRepository extends JpaRepository<Queries,Long> {

    Optional<Queries> findByTitre(String titre);




}
