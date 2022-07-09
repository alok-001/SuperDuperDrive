package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Insert("INSERT INTO USERS(firstname, lastname, username, password, salt) VALUES(#{ModelfirstName}, #{ModellastName}, #{Modelusername}, #{Modelpassword}, #{Modelsalt})")
    @Options(useGeneratedKeys = true, keyProperty = "ModeluserId")
    int insertUser(UserModel user);
//    Creating a new User


    @Select("SELECT * FROM USERS WHERE userId = #{userId}")
    UserModel getanyUser(Integer userId);
//    Get any user by userId


    @Select("SELECT * FROM USERS WHERE username = #{username}")
    @Results({
            @Result(id = true, property = "ModeluserId", column = "userid"),
            @Result(property = "Modelusername", column = "username"),
            @Result(property = "Modelsalt", column = "salt"),
            @Result(property = "Modelpassword", column = "password"),
            @Result(property = "ModelfirstName", column = "firstname"),
            @Result(property = "ModellastName", column = "lastname")
    })
    UserModel getUser(String username);
//    To get an individual named user

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    int deleteUser(String username);
//    Delete user by username

    @Update("UPDATE USERS SET firstname= #{ModelfirstName}, lastname = #{ModellastName}, username =  #{Modelusername}, password = #{Modelpassword},salt = #{Modelsalt} WHERE userId = #{ModeluserId}")
    int updateUser(UserModel userModel);
//    Update any user by userid
}
