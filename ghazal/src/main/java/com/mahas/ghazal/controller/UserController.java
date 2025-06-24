package com.mahas.ghazal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahas.ghazal.command.ICommand;
import com.mahas.ghazal.command.rules.user.Login;
import com.mahas.ghazal.command.rules.user.NewUser;
import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.facade.Facade;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Facade facade;

    @Autowired
    Login login;

    @Autowired
    NewUser newUser;

    @GetMapping({"/{email}/{password}"})
    public ResponseEntity Login(@PathVariable String email, @PathVariable String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        FacadeRequest facadeRequest = new FacadeRequest();
        facadeRequest.setEntity(user);

        ICommand[] commands = new ICommand[]{login};
        facadeRequest.setCommands(commands);

        FacadeResponse facadeResponse = facade.query(facadeRequest);

        return ResponseEntity.ok(facadeResponse);
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody User user) {
        FacadeRequest facadeRequest = new FacadeRequest();
        if(user.getCpf().trim().isBlank()){
            user.setCpf(null);
        }

        facadeRequest.setEntity(user);

        ICommand[] commands = new ICommand[]{newUser};
        facadeRequest.setCommands(commands);

        FacadeResponse facadeResponse = facade.save(facadeRequest);

        return ResponseEntity.ok(facadeResponse);
    }

}
