package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.Queries;
import com.pfe.loginpartjwt.models.Rapports;
import com.pfe.loginpartjwt.models.Roles;
import com.pfe.loginpartjwt.models.Users;
import com.pfe.loginpartjwt.repositories.RoleRepository;
import com.pfe.loginpartjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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


    @GetMapping("/oneUser/{iduser}")
    public Optional<Users> findUserByUsername(@PathVariable("iduser") Long iduser){
        return userRepository.findById(iduser);
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

    @DeleteMapping("/deleteUser/{iduser}")
    public void deleteUser(@PathVariable("iduser") Long iduser) throws Exception {

            if(!(userRepository.findById(iduser).isPresent())) {
                throw new Exception(
                        "This user doesn't exists"
                );}
            else{
                    userRepository.findById(iduser).get().getListQueries().removeAll(userRepository.findById(iduser).get().getListQueries());
                    userRepository.delete(userRepository.findById(iduser).get());
                }
            }


    @PutMapping("/updateUserInfos/{iduser}")
    public Users updateUserInfos(@RequestBody Users user,@PathVariable("iduser") Long iduser) throws  Exception {
        if(!(userRepository.findById(iduser).isPresent())){
            throw new Exception(
                    "User not found ! "
            );
        }else {
            Users U = userRepository.findById(iduser).get();
            U.setTelephone(user.getTelephone());
            U.setIdorganism(user.getIdorganism());
            U.setEmail(user.getEmail());
            U.setNom(user.getNom());
            U.setPrenom(user.getPrenom());
            return userRepository.save(U);
        }
    }

    @PutMapping("/updateUserNamePass/{iduser}")
    public Users updateUserNamePass(@RequestBody Users user,@PathVariable("iduser") Long iduser) throws  Exception {
        if (!(userRepository.findById(iduser).isPresent())) {
            throw new Exception(
                    "User  not found ! "
            );
        } else {
            Users U = userRepository.findById(iduser).get();
            U.setUsername(user.getUsername());
            U.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(U);
        }
    }

    @PutMapping("/updateUserRole/{iduser}/{intitule}")
    public Users updateUserNamePass(@PathVariable("intitule") String intitule,@PathVariable("iduser") Long iduser) throws  Exception {
        if (!(userRepository.findById(iduser).isPresent())) {
            throw new Exception(
                    "User  not found !! "
            );
        } else {
            Users U = userRepository.findById(iduser).get();
            Roles roleUser  = roleRepository.findByIntitule(intitule).get();
            U.setRole(roleUser);
            return userRepository.save(U);
        }
    }


    @GetMapping("/queriesUser/{iduser}")
    public List<Queries> findUserQueries(@PathVariable("iduser") Long iduser) throws Exception {
        if(userRepository.findById(iduser).isPresent()) {
            return userRepository.findById(iduser).get().getListQueries();
        }
        else{
            throw new Exception(
                    "User not found !!"
            );
        }
    }

    @GetMapping("/rapports/{iduser}")
    public List<Rapports> findUserRapports(@PathVariable("iduser") Long iduser) throws Exception{
        if(userRepository.findById(iduser).isPresent()) {
            return userRepository.findById(iduser).get().getListRapports();
        }
        else{
            throw new Exception(
                    "User not found !!"
            );
        }
        }

    }



