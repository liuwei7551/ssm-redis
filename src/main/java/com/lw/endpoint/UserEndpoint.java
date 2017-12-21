package com.lw.endpoint;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.lw.dto.ResponseDto;
import com.lw.entity.ImportResult;
import com.lw.entity.User;
import com.lw.service.AcsUserService;
import com.lw.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Controller
public class UserEndpoint {

  @Resource
  private UserService userService;
  
  private static final Logger log = LoggerFactory.getLogger(AcsUserService.class);

  @ApiOperation(value = "获取用户信息")
  @ResponseBody // 将Java对象输出json
  @RequestMapping(value = { "api/v1/user/{userId}" }, method = RequestMethod.GET)
  public ResponseDto<User> getUser(@PathVariable final int userId) {
    return ResponseDto.create(userService.getUserById(userId));
  }
  
  @ApiOperation(value = "新建用户")
  @ResponseBody // 将Java对象输出json
  @RequestMapping(value = { "api/v1/user" }, method = RequestMethod.POST)
  public void insert(@RequestBody User user) {
    userService.insert(user);
  }
  
  @ApiOperation(value = "获取用户列表")
  @ResponseBody // 将Java对象输出json
  @RequestMapping(value = { "api/v1/user" }, method = RequestMethod.GET)
  public ResponseDto<List<User>> queryAll() {
    return ResponseDto.create(userService.queryAll());
  }
  
  @ApiOperation(value = "修改用户信息")
  @ResponseBody // 将Java对象输出json
  @RequestMapping(value = { "api/v1/user/{id}" }, method = RequestMethod.PUT)
  public ResponseDto<?> update(@RequestBody User record) {
    userService.update(record);
    return ResponseDto.OK;
  }
  
  @ApiOperation(value = "删除用户信息")
  @ResponseBody // 将Java对象输出json
  @RequestMapping(value = { "api/v1/user/{id}" }, method = RequestMethod.DELETE)
  public ResponseDto<?> delete(@PathVariable Integer id) {
    userService.delete(id);
    return ResponseDto.OK;
  }
  
  @ApiOperation(value = "批量导入用户信息")
  @ResponseBody
  @RequestMapping(value = { "api/v1/user/batchImport" }, method = RequestMethod.POST)
  public ResponseDto<?> batchImport(@RequestParam(value = "file", required = false) MultipartFile file) {
    ResponseDto<?> result = new ResponseDto<>();
    try {
      if (file == null)
      {
          throw new Exception("文件为空");
      }
      ImportResult<User> importResult = userService.batchImport(file);
      result.setResponseDesc("批量导入成功： "+ importResult.getMessage());
      
//      //返回导入失败的数据
//      if (importResult.getResultList() != null && importResult.getResultList().size() > 0)
//      {
//          return new ExcelUtil().returnFailImportExcel(response, ExcelUtil.RESIDENCE_MODEL, importResult.getResultList(),
//                  ResidenceInfo.class, ExcelUtil.ResidenceMap, result);
//      }
  } catch (Exception e) {
      result.setResponseCode("0011");
      result.setResponseDesc("批量导入失败: " + e.getMessage());
      e.printStackTrace();
  }
    return result;
  }
  
}
