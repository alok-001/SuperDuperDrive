package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;

    private EncryptionService encryptionService;

    Logger logger = LoggerFactory.getLogger(CredentialService.class);

    @Autowired
    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService){
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public CredentialService() {

    }


    public CredentialModel getCredentialId(Integer credentialId){
        return credentialMapper.getCredentialbyId(credentialId);
    }

    public ArrayList<CredentialModel> getAllCredentials(Integer userId){
        return credentialMapper.getAllCredentials(userId);
    }

    public int insert(CredentialModel credentialModel){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encoded = Base64.getEncoder().encodeToString(key);
        String password = encryptionService.encryptValue(credentialModel.getPassword(), encoded);
        credentialModel.setUrl(credentialModel.getUrl());
        credentialModel.setKey(encoded);
        credentialModel.setPassword(password);
        credentialModel.setUserid(credentialModel.getUserid());
        credentialModel.setUsername(credentialModel.getUsername());
        return credentialMapper.addCredential(credentialModel);
    }

    public int updateCredential(CredentialModel credentialModel){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encoded = Base64.getEncoder().encodeToString(key);
        String password = encryptionService.encryptValue(credentialModel.getPassword(), encoded);
        credentialModel.setKey(encoded);
        credentialModel.setPassword(password);
        credentialModel.setUrl(credentialModel.getUrl());
        credentialModel.setUserid(credentialModel.getUserid());
        credentialModel.setUsername(credentialModel.getUsername());
        return credentialMapper.updateCredential(credentialModel);
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }
}
