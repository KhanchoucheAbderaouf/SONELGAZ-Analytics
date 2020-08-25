package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.Roles;
import com.pfe.loginpartjwt.repositories.RoleRepository;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/roles")
public class RolesController {


    @Autowired
    RoleRepository roleRepository;



    @GetMapping("/allRoles")
    public List<Roles> allRoles(){
        return roleRepository.findAll();
    }

    @PostMapping("/addRole/{intitule}")
    public Roles addRole(@PathVariable("intitule") String intitule) throws Exception{

        if(roleRepository.findByIntitule(intitule).isPresent()){
            throw new Exception(
                    "This role exists!!"
            );
        }else {
            Roles R = new Roles();
            R.setIntitule(intitule);
            return roleRepository.save(R);
        }
    }

}
