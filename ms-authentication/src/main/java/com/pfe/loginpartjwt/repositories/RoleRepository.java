package com.pfe.loginpartjwt.repositories;

import com.pfe.loginpartjwt.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Roles,Long> {


    Optional<Roles> findByIntitule(String intitule);

}
