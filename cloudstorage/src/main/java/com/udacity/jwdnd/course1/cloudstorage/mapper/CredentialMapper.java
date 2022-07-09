package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface CredentialMapper {
    @Insert("INSERT INTO CREDENTIALS(userId, url, password, key, username) VALUES(#{userId}, #{url}, #{password}, #{key}, #{username})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(CredentialModel credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    CredentialModel getCredentialbyId(Integer credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    ArrayList<CredentialModel> getAllCredentials(Integer userId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    int deleteCredential(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, password = #{password}, key = #{key}, username = #{username} WHERE credentialId = #{credentialId}")
    int updateCredential(CredentialModel credentialModel);
}
