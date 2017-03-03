package com.lw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lw.dao.IndexHistoryMapper;
import com.lw.entity.IndexHistory;

@Service
public class IndexHistoryService {
  
  @Resource
  private IndexHistoryMapper indexHistoryMapper;
  
  public List<IndexHistory> listIndexHistory(){
    return this.indexHistoryMapper.list();
  }
}
