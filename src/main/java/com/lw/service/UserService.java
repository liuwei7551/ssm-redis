package com.lw.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lw.common.utils.ExcelUtil;
import com.lw.common.utils.RedisCache;
import com.lw.dao.UserMapper;
import com.lw.entity.ImportResult;
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
  
//  @Transactional(readOnly=true)
  public void insert(User record) {
    userMapper.insert(record);
  }
  
  public List<User> queryAll() {
    return userMapper.queryAll();
  }
  
  public void update(User record) {
    userMapper.updateByPrimaryKeySelective(record);
  }
  
  public void delete(Integer id) {
    userMapper.deleteByPrimaryKey(id);
  }
  
  public ImportResult<User> batchImport(MultipartFile file) throws IOException{
    ImportResult<User> importResult = new ImportResult<>();
    List<User> userList = new ArrayList<>();// 作为从文件中读取出来的原始列表数据
    // 最终新增的列表，先检查填写信息的规范性，再加入列表，保证事务的完整性
    List<User> insertList = new ArrayList<>(); // 新增列表
    List<User> updateList = new ArrayList<>(); // 更新列表
    List<User> invalidList = new ArrayList<>(); // 无效数据列表
    List<User> insertFailList = new ArrayList<>(); // 失败数据列表
    List<User> updateFailList = new ArrayList<>(); // 失败数据列表
    List<String> uniquenessList = new ArrayList<>(); // 存放唯一值参数，监测excel记录中是否存在相同记录
    
    InputStream inputStream = null;
    try {
        inputStream = file.getInputStream();
        new ExcelUtil().parseImportFile(inputStream, file.getOriginalFilename(),
            userList, User.class, ExcelUtil.UserMap);
        System.out.println(userList);
    }
    catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (inputStream != null)
            inputStream.close();
    }
    importResult.setMessage("成功");
    importResult.setResultList(userList);
    return importResult;
  }
}
