package com.ofd.ofd.service;

import com.ofd.ofd.model.NewUser;
import com.ofd.ofd.model.User;

public interface Manager {
    
    public User addNewUser(NewUser newUser);

    public User findUserByLogin(String login);

    public User findUserById(Integer id);

    public User login(String login, String password);

    
}
