package com.lw.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;

public final class ResponseDto<T> {

  /**
   * 通用正常输出
   */
  public static ResponseDto<?> OK = new ResponseDto<>();
  
  /**
   * 创建无内容正常输出，自定义应答描述
   *
   * @param responseDesc
   *          应答描述
   * @return
   */
  public static ResponseDto<?> create(final String responseDesc) {
    final ResponseDto<?> responseDto = new ResponseDto<>();
    responseDto.responseDesc = responseDesc;
    return responseDto;
  }
  
  /**
   * 创建正常输出
   *
   * @param t
   *          DTO类
   * @return
   */
  public static <T> ResponseDto<T> create(final T t) {
    final ResponseDto<T> responseDto = new ResponseDto<T>(t);
    return responseDto;
  }
  
  @ApiModelProperty("应答码")
  private String responseCode = "000";

  @JsonInclude(value = Include.NON_EMPTY)
  @ApiModelProperty("应答描述")
  private String responseDesc = "";

  @JsonInclude(value = Include.NON_EMPTY)
  @ApiModelProperty("请求参数")
  private Map<String, Object> requestParams;

  @JsonInclude(value = Include.NON_NULL)
  @ApiModelProperty("返回数据")
  private T data;

  public ResponseDto() {
    super();
  }

  public ResponseDto(T data) {
    this.data = data; 
  }

  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  public String getResponseDesc() {
    return responseDesc;
  }

  public void setResponseDesc(String responseDesc) {
    this.responseDesc = responseDesc;
  }

  public Map<String, Object> getRequestParams() {
    return requestParams;
  }

  public void setRequestParams(Map<String, Object> requestParams) {
    this.requestParams = requestParams;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
