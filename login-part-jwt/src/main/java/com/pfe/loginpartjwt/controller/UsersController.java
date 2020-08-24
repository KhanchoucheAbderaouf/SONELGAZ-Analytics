package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.Queries;
import com.pfe.loginpartjwt.models.Roles;
import com.pfe.loginpartjwt.models.Users;
import com.pfe.loginpartjwt.repositories.RoleRepository;
import com.pfe.loginpartjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/oneUser/{username}")
    public Optional<Users> findUserByUsername(@PathVariable("username") String username){
        return userRepository.findByUsername(username);
    }

    @GetMapping("/allUsers")
    public List<Users> findAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/addUser/{intitule}")
    public Users addUser(@RequestBody Users user,@PathVariable("intitule") String intitule) throws Exception{
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
                throw new Exception(
                        "There is an account with that username : " + user.getUsername());
            }
        if(!(roleRepository.findByIntitule(intitule).isPresent())){
            throw new Exception(
                    "There is no role with name : " + intitule);
        }
        Users U = user;
        U.setDate_creation(new Date());
        U.setActive(true);
        Roles roleUser  = roleRepository.findByIntitule(intitule).get();
        U.setRole(roleUser);
        U.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(U);
    }

    @DeleteMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable("username") String username) throws Exception {

            if(!(userRepository.findByUsername(username).isPresent())) {
                throw new Exception(
                        "This user : " + username + " doesn't exists"
                );}
            else{
                    userRepository.delete(userRepository.findByUsername(username).get());
                    return "success";
                }
            }


    @PutMapping("/updateUserInfos/{username}")
    public Users updateUserInfos(@RequestBody Users user,@PathVariable("username") String username) throws  Exception {
        if(!(userRepository.findByUsername(username).isPresent())){
            throw new Exception(
                    "User " + username + " not found : "
            );
        }else {
            Users U = userRepository.findByUsername(username).get();
            U.setTelephone(user.getTelephone());
            U.setIdorganism(user.getIdorganism());
            U.setEmail(user.getEmail());
            U.setNom(user.getNom());
            U.setPrenom(user.getPrenom());
            return userRepository.save(U);
        }
    }

    @PutMapping("/updateUserNamePass/{username}")
    public Users updateUserNamePass(@RequestBody Users user,@PathVariable("username") String username) throws  Exception {
        if (!(userRepository.findByUsername(username).isPresent())) {
            throw new Exception(
                    "User " + username + " not found : "
            );
        } else {
            Users U = userRepository.findByUsername(username).get();
            U.setUsername(user.getUsername());
            U.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(U);
        }
    }

    @PutMapping("/updateUserRole/{username}/{intitule}")
    public Users updateUserNamePass(@PathVariable("intitule") String intitule,@PathVariable("username") String username) throws  Exception {
        if (!(userRepository.findByUsername(username).isPresent())) {
            throw new Exception(
                    "User " + username + " not found !! "
            );
        } else {
            Users U = userRepository.findByUsername(username).get();
            Roles roleUser  = roleRepository.findByIntitule(intitule).get();
            U.setRole(roleUser);
            return userRepository.save(U);
        }
    }


    @GetMapping("/queriesUser/{username}")
    public List<Queries> findUserQueries(@PathVariable("username") String username) throws Exception {
        if(userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username).get().getListqueries();
        }
        else{
            throw new Exception(
                    "Users " + username + " not found !!"
            );
        }
    }


}
