package com.lw.endpoint;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.lw.search.service.GCSearchService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
public class GCSearchEndpoint {

  @Resource
  private GCSearchService gCSearchService;
  
  @ApiOperation(value = "自定义搜索")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "start", required = true, defaultValue = "0", paramType = "query", dataType = "String"),
    @ApiImplicitParam(name = "q", value = "搜索内容", required = true, paramType = "query", dataType = "String") })
  @RequestMapping(value = { "api/v1/search" }, method = RequestMethod.GET)
  public String gCSearch(@RequestParam(value = "start", required = true, defaultValue = "0") final String start, @RequestParam(value = "q") final String q) {
    return this.gCSearchService.search(start, q);
  }

}
