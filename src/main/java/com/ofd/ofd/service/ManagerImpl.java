package com.ofd.ofd.service;

import com.ofd.ofd.dao.DAO;
import com.ofd.ofd.exception.AlreadyExistsUserException;
import com.ofd.ofd.exception.WrongPasswordException;
import com.ofd.ofd.model.LoginForm;
import com.ofd.ofd.model.NewUser;
import com.ofd.ofd.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerImpl implements Manager {
    private DAO dao;

    @Autowired
    public ManagerImpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    public int addNewUser(NewUser newUser) {
        if (!dao.checkExistingUser(newUser)) {
            return dao.addNewUser(newUser);
        } else {
            throw new AlreadyExistsUserException();
        }
    }

    @Override
    public User findUserByLogin(String login) {
        User user = dao.findUserByLogin(login);
        return user;
    }

    @Override
    public User findUserById(Integer id) {
        User user = dao.findUserById(id);
        return user;
    }

    @Override
    public void login(String login, String password) {
        User user = dao.findUserByLogin(login);
        if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException();
        }
    }
}
