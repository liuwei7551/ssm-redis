package com.lw.dao;

import java.util.List;

import com.lw.entity.IndexHistory;

public interface IndexHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexHistory record);

    int insertSelective(IndexHistory record);

    IndexHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexHistory record);

    int updateByPrimaryKey(IndexHistory record);
    
    List<IndexHistory> list();
}