package com.pfe.loginpartjwt.repositories;

import com.pfe.loginpartjwt.models.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QueryRepository extends JpaRepository<Queries,Long> {

    Optional<Queries> findByTitre(String titre);
    Optional<Queries> findFirstByTitre(String titre);
    Optional<List<Queries>> findAllByTitre(String titre);
    @Query(value = "INSERT INTO public.queries(idquery, date_creation, titre, valeur,creator, id_user) VALUES ( nextval('id_query'),current_date ,:titre,:valeur,:creator,:iduser); "
            ,nativeQuery = true)
    void saveQuery(@Param("titre") String titre,@Param("valeur") String valeur,@Param("creator") String creator,@Param("iduser")Long iduser);
}
