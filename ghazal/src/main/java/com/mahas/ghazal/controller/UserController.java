package com.mahas.ghazal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.facade.Facade;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private Facade facade;

    @GetMapping({"/{email}", "/{email}/{password}"})
    public ResponseEntity getUser(@PathVariable String email, @PathVariable(required = false) String password){
        User user = new User();
        user.setEmail(email);

        if(password != null){
            user.setPassword(password);
        }

        List<DomainEntity> users = facade.query(user);
        return ResponseEntity.ok(users);
    }

}
