package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserMapper userMapper;
    private HashService hashService;

    @Autowired
    public  AuthenticationService(UserMapper userMapper, HashService hashService){
        this.hashService = hashService;
        this.userMapper = userMapper;
    }
    @Override
    public Authentication authenticate(org.springframework.security.core.Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserModel user = userMapper.getUser(username);
        if (user!=null){
            String hashPassword = hashService.getHashedValue(password, user.getModelsalt());
            if (user.getModelpassword().equals(hashPassword)){
                return new UsernamePasswordAuthenticationToken(username, hashPassword, new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
