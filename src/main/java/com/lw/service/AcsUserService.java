package com.lw.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lw.common.utils.PasswordHash;
import com.lw.dao.AcsUserMapper;
import com.lw.entity.AcsUser;

@Service
public class AcsUserService {

  private static final Logger log = LoggerFactory.getLogger(AcsUserService.class);

  @Resource
  private AcsUserMapper acsUserMapper;
  
  public void insert(AcsUser acsUser) {
    acsUser.setPasswordSalt(PasswordHash.createSalt());
    acsUser.setPassword(PasswordHash.hashPassword(acsUser.getPassword(), acsUser.getPasswordSalt()));
    acsUserMapper.insert(acsUser);
  }
  
  public Boolean checkPhone(String phone) {
    return acsUserMapper.checkPhone(phone);
  }
  
}
