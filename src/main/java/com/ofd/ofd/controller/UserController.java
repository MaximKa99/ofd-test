package com.ofd.ofd.controller;

import com.ofd.ofd.model.User;
import com.ofd.ofd.service.Manager;
import com.ofd.ofd.view.ViewUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private Manager manager;

    @Autowired
    public UserController(Manager manager) {
        this.manager = manager;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewUser> getUserById(@PathVariable int id) {
        User user = manager.findUserById(id);
        ViewUser viewUser = new ViewUser();
        viewUser.setLogin(user.getLogin());
        viewUser.setBalance(user.getBalance());
        return new ResponseEntity<ViewUser>(viewUser, HttpStatus.OK);
    }
}
