package com.lw.endpoint;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lw.dto.ResponseDto;
import com.lw.entity.User;
import com.lw.service.UserService;

import io.swagger.annotations.ApiOperation;

@Controller
public class UserEndpoint {

  @Resource
  private UserService userService;

  @ApiOperation(value = "获取用户信息")
  @ResponseBody  //将Java对象输出json
  @RequestMapping(value = { "api/v1/user/{userId}" }, method = RequestMethod.GET)
  public ResponseDto<User> getUser(@PathVariable final int userId) {
     return ResponseDto.create(userService.getUserById(userId));
  }

}
