package com.ofd.ofd.controller;

import com.ofd.ofd.model.NewUser;
import com.ofd.ofd.service.JwtProvider;
import com.ofd.ofd.service.Manager;
import com.ofd.ofd.view.ViewAuthRequest;
import com.ofd.ofd.view.ViewAuthResponse;
import com.ofd.ofd.view.ViewLoginForm;
import com.ofd.ofd.view.ViewNewUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private Manager manager;
    private JwtProvider provider;

    @Autowired
    public AuthController(Manager manager, JwtProvider provider) {
        this.manager = manager;
        this.provider = provider;
    }

    @PostMapping("/login")
    public ViewAuthResponse auth(@RequestBody ViewAuthRequest authRequest) {
        manager.login(authRequest.getLogin(), authRequest.getPassword());
        String token = provider.generateToken(authRequest.getLogin());
        String id = manager.findUserByLogin(authRequest.getLogin()).getId();
        return new ViewAuthResponse(token, id);
    }

    @PostMapping("/register")
    public ResponseEntity<ViewNewUser> registNewUser(@RequestBody ViewLoginForm viewLoginForm) {
        NewUser newUser = new NewUser();
        newUser.setLogin(viewLoginForm.getLogin());
        newUser.setPassword(viewLoginForm.getPassword());
        int id = manager.addNewUser(newUser);
        return new ResponseEntity<>(new ViewNewUser(id), HttpStatus.OK);
    }
}