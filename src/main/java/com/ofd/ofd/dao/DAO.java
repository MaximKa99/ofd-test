package com.ofd.ofd.dao;

import com.ofd.ofd.exception.AlreadyExistsUserException;
import com.ofd.ofd.exception.NoSuchUserException;
import com.ofd.ofd.model.NewUser;
import com.ofd.ofd.model.User;

public interface DAO {
    public User findUserByLogin(String login) throws NoSuchUserException;

    public User findUserById(Integer id) throws NoSuchUserException;

    public int addNewUser(NewUser newUser) throws AlreadyExistsUserException;

    public boolean checkExistingUser(NewUser newUser);
}
