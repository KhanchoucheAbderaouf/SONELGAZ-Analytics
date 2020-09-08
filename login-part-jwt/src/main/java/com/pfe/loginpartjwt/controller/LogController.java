package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.Exceptions.CustomException;
import com.pfe.loginpartjwt.models.AuthenticationRequest;
import com.pfe.loginpartjwt.models.AuthenticationResponse;
import com.pfe.loginpartjwt.models.MyUserDetails;
import com.pfe.loginpartjwt.models.Users;
import com.pfe.loginpartjwt.services.MyUserDetailsService;
import com.pfe.loginpartjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;




    @CrossOrigin
    @PostMapping(value="/authenticate",consumes = "application/json",produces = "application/json")
    public MyUserDetails createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws CustomException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new CustomException();
        }
        System.out.println(authenticationRequest.getUsername() + " " + authenticationRequest.getPassword());
        final MyUserDetails theUserDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        System.out.println(theUserDetails.getUsername()+" "+theUserDetails.getPassword()+" "+theUserDetails.getAuthorities()+theUserDetails);
        final String jwt = jwtTokenUtil.generateToken(theUserDetails);
        theUserDetails.setJwt(jwt);
        System.out.println(jwt);
        return theUserDetails;
    }


}
