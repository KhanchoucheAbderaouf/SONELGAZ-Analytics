package com.pfe.loginpartjwt.repositories;


import com.pfe.loginpartjwt.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUsername(String username);


}