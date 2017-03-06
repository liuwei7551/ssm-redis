package com.lw.common.mapper;

import javax.validation.constraints.Pattern;
import org.apache.commons.lang3.StringUtils;
import com.github.pagehelper.PageHelper;
import com.lw.common.Regexes;
import com.lw.common.utils.TextUtils;

/**
 * MyBatis分页插件的包装<br>
 * 在调用mapper的方法之前，执行page或者pageWithoutCount，插件会自动修改SQL执行分页
 */
public class Pager {

  /**
   * 仅分页，没有统计信息
   *
   * @param pager
   */
  public void pageWithoutCount() {
    PageHelper.startPage(this.pageNum, this.pageSize);
    if (StringUtils.isNotBlank(this.orderBy)) {
      PageHelper.orderBy(TextUtils.camelToUnderline(this.orderBy));
    }
  }

  /**
   * 分页并包含统计信息
   *
   * @param pager
   */
  public void page() {
    PageHelper.startPage(this.pageNum, this.pageSize, true);
    if (StringUtils.isNotBlank(this.orderBy)) {
      PageHelper.orderBy(TextUtils.camelToUnderline(this.orderBy));
    }
  }

  public static int calcOffset(final int page, final int pageSize) {
    return (page - 1) * pageSize;
  }

  /**
   * 页码，从1开始
   */
  private int pageNum;
  /**
   * 页面大小
   */
  private int pageSize;
  /**
   * 排序
   */
  @Pattern(regexp = Regexes.SQL_ORDER)
  private String orderBy;

  public int getPageNum() {
    return this.pageNum;
  }

  public void setPageNum(final int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(final int pageSize) {
    this.pageSize = pageSize;
  }

  public String getOrderBy() {
    return this.orderBy;
  }

  public void setOrderBy(final String orderBy) {
    this.orderBy = orderBy;
  }

}
