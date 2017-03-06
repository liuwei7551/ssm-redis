package com.lw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.lw.common.mapper.Pager;
import com.lw.dao.IndexHistoryMapper;
import com.lw.entity.IndexHistory;

@Service
public class IndexHistoryService {
  
  @Resource
  private IndexHistoryMapper indexHistoryMapper;
  
  public List<IndexHistory> listIndexHistory(Pager pager){
    PageHelper.startPage(pager.getPageNum(),pager.getPageSize());
    List<IndexHistory> list = this.indexHistoryMapper.list();
    return list;
  }
}
