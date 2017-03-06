package com.lw.endpoint;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lw.common.mapper.Pager;
import com.lw.dto.ResponseDto;
import com.lw.entity.IndexHistory;
import com.lw.service.IndexHistoryService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
public class IndexHistoryEndpoint {

  @Resource
  private IndexHistoryService indexHistoryService;

  @ApiOperation("获取所有IndexHistory")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "pageNum", required = true, defaultValue = "1", paramType = "query", dataType = "int"),
      @ApiImplicitParam(name = "pageSize", required = true, defaultValue = "10", paramType = "query", dataType = "int"),
      @ApiImplicitParam(name = "orderBy", required = false, paramType = "query", dataType = "String"),
      @ApiImplicitParam(name = "pager", value = "不需要填写", required = false, paramType = "header", dataType = "String") })
  @ResponseBody
  @RequestMapping(value = { "api/v1/indexhistory" }, method = RequestMethod.GET)
  public ResponseDto<List<IndexHistory>> listIndexHistory(@Valid Pager pager) {
    return ResponseDto.create(this.indexHistoryService.listIndexHistory(pager));
  }
}
