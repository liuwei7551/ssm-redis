package com.lw.endpoint;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.lw.dto.ResponseDto;
import com.lw.entity.AcsUser;
import com.lw.service.AcsUserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Controller
public class AcsUserEndpoint {

  @Resource
  private AcsUserService acsUserService;
  
  private static final Logger log = LoggerFactory.getLogger(AcsUserService.class);

  @ApiOperation(value = "用户注册")
  @ResponseBody // 将Java对象输出json
  @RequestMapping(value = { "api/v1/register" }, method = RequestMethod.POST)
  public ResponseDto<?> insert(@RequestParam final String phone, @RequestParam final String password, @RequestParam final String confirmPassword) {
    if(acsUserService.checkPhone(phone)) {
      return ResponseDto.create("用户已存在，请直接登录！");
    }
    if(password.equals(confirmPassword)) {
      acsUserService.insert(new AcsUser(phone, password));
      return ResponseDto.OK;
    }else {
      return ResponseDto.create("密码不一致！");
    }
  }
  
  @ApiOperation(value = "用户登录")
  @ResponseBody // 将Java对象输出json
  @RequestMapping(value = { "api/v1/login" }, method = RequestMethod.POST)
  public ResponseDto<?> login(@RequestBody AcsUser user) {
    if(user.getPhone() == "18705190227" && user.getPassword() == "123456") {
      return ResponseDto.OK;
    }else {
      return ResponseDto.create("用户名或密码错误");
    }
  }
}
