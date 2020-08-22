package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.Roles;
import com.pfe.loginpartjwt.repositories.RoleRepository;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {


    @Autowired
    RoleRepository roleRepository;



    @GetMapping("/allRoles")
    public List<Roles> allRoles(){
        return roleRepository.findAll();
    }

    @PostMapping("/addRole")
    public Roles addRole(@RequestBody Roles role) throws Exception{

        if(roleRepository.findByIntitule(role.getIntitule()).isPresent()){
            throw new Exception(
                    "This role exists!!"
            );
        }else {
            Roles R = new Roles();
            R.setIntitule(role.getIntitule());
            R.setIdrole(roleRepository.findAll().get(roleRepository.findAll().size()-1).getIdrole() + 1);
            return roleRepository.save(R);
        }
    }

}
