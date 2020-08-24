package com.pfe.loginpartjwt.repositories;


import com.pfe.loginpartjwt.models.Queries;
import com.pfe.loginpartjwt.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);
    Optional<Users> findByIdorganism(Long idorganism);

   /* @Query(value = "select distinct q from public.users u,public.queries q ,public.tab_ref t where t.thecreator_iduser=:iduser and q.idquery=t.idquery"
                ,nativeQuery = true)
    List<Queries> getListQueries(@Param("iduser") Long iduser);*/

}