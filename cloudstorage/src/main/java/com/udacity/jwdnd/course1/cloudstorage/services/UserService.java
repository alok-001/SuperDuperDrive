package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService){
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //    Insert or create a User
    public int insert(UserModel user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String saltEncode = Base64.getEncoder().encodeToString(salt);
        String passwordHash = hashService.getHashedValue(user.getModelpassword(), saltEncode);
        UserModel user1 = new UserModel(null, user.getModelfirstName(), user.getModellastName(), user.getModelusername(), passwordHash, saltEncode);
        return userMapper.insertUser(user1);
    }

    //    Getting user by username
    public UserModel getUser(String username){
        return  userMapper.getUser(username);
    }

    //    Getting all user by id
    public UserModel getAllUsers(String username){
        return userMapper.getUser(username);
    }

//    Get user by id
    public UserModel getUserById(Integer userId){
        return userMapper.getanyUser(userId);
    }

//    Delete user
    public void deleteUser(UserModel userModel){
        userMapper.deleteUser(userModel.getModelusername());
    }

//    update user
    public void updateUser(UserModel userModel){
        userMapper.updateUser(userModel);
    }

    //    Getting user exist or not
    public boolean userExist(String username){
        return  userMapper.getUser(username)==null;
    }
}
