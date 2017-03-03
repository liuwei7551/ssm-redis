package com.lw.endpoint;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lw.dto.ResponseDto;
import com.lw.entity.IndexHistory;
import com.lw.service.IndexHistoryService;

import io.swagger.annotations.ApiOperation;

@Controller
public class IndexHistoryEndpoint {
  
  @Resource
  private IndexHistoryService indexHistoryService;

  @ApiOperation("获取所有IndexHistory")
  @ResponseBody
  @RequestMapping(value = { "api/v1/indexhistory" }, method = RequestMethod.GET)
  public ResponseDto<List<IndexHistory>> listIndexHistory(){
     return ResponseDto.create(this.indexHistoryService.listIndexHistory());
  }
}
