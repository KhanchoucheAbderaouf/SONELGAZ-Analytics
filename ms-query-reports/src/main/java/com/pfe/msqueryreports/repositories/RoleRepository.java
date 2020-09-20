package com.pfe.msqueryreports.repositories;


import com.pfe.msqueryreports.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Roles,Long> {


    Optional<Roles> findByIntitule(String intitule);

}
