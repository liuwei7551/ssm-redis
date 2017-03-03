package com.lw.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lw.dao.UserMapper;
import com.lw.entity.User;

@Service
public class UserService {
  
  @Resource
  private UserMapper userMapper;
  
  public User getUserById(int userId) {
    return this.userMapper.selectByPrimaryKey(userId);
  }
}
