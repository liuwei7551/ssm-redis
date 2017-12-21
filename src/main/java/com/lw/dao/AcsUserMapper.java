package com.lw.dao;

import com.lw.entity.AcsUser;

public interface AcsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AcsUser record);

    int insertSelective(AcsUser record);

    AcsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AcsUser record);

    int updateByPrimaryKey(AcsUser record);

    Boolean checkPhone(String phone);
}