package com.lw.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lw.dto.ResponseDto;

@RestController
@ControllerAdvice
public class RestControllerAdvice {
  private static final Logger log = LoggerFactory.getLogger(RestControllerAdvice.class);

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ResponseDto<Map<String, Object>> handlerError(HttpServletRequest req, Exception e) {
    RestControllerAdvice.log.error("{} {} {}", req.getRequestURL(), req.getMethod(), e);
    RestControllerAdvice.log.debug("", e);
    Map<String, Object> map = new HashMap<>();
    map.put("tip", "此错误说明调用接口失败，失败原因见msg，如果msg为空，联系后台");
    map.put("msg", e.getMessage());
    map.put("path", req.getRequestURI());
    map.put("params", req.getParameterMap());
    map.put("status", "0");
    return ResponseDto.create(map);
  }
}
