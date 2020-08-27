package com.pfe.loginpartjwt.repositories;


import com.pfe.loginpartjwt.models.Queries;
import com.pfe.loginpartjwt.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;



public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);
    Optional<List<Users>> findByIdorganism(Long idorganism);
    Optional<List<Users>> findAllByListQueries(Queries query);

}