package com.ofd.ofd.service;

import com.ofd.ofd.dao.DAO;
import com.ofd.ofd.model.User;
import com.ofd.ofd.model.UserDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private DAO dao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(DAO dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findUserByLogin(username);
        return new UserDetail(user, passwordEncoder);
    }
    
}
