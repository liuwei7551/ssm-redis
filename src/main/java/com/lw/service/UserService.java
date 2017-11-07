package com.lw.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.utils.RedisCache;
import com.lw.dao.UserMapper;
import com.lw.entity.User;

@Service
public class UserService {

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  @Resource
  private UserMapper userMapper;
  @Resource
  private RedisCache redisCache;

  public User getUserById(int userId) {
    String cache_key = RedisCache.CAHCENAME + "|getUserById" + userId;
    User user = redisCache.getCache(cache_key, User.class);
    if (user != null) {
      log.info("get cache with key:" + cache_key);
    } else {
      user = userMapper.selectByPrimaryKey(userId);
      redisCache.putCacheWithExpireTime(cache_key, user, RedisCache.CAHCETIME);
      log.info("put cache with key:" + cache_key);
    }
    return user;
  }
  
  @Transactional(readOnly=true)
  public void insert(User record) {
    userMapper.insert(record);
  }
  
  public List<User> queryAll() {
    return userMapper.queryAll();
  }
}
