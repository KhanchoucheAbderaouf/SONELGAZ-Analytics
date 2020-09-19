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
    Optional<List<Users>> findByIdorganism(Long idorganism);
    Optional<List<Users>> findAllByListQueries(Queries query);

    @Query(value="UPDATE public.users SET  email=:email, idorganism=:idorganism, nom=:nom, prenom=:prenom, telephone=:telephone WHERE iduser=:iduser;",nativeQuery = true)
    Users UpdateUser(@Param("iduser") long iduser,@Param("nom") String nom,@Param("prenom") String prenom,@Param("email") String email,@Param("telephone") String telephone,@Param("idorganism") long idorganism);


}