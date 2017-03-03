package com.lw.entity;

import java.math.BigDecimal;
import java.util.Date;

public class IndexHistory {
    private Integer id;

    private String indexCode;

    private Date tradeDate;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    private Integer tradeYear;

    private Integer tradeMonth;

    private Integer tradeWeek;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode == null ? null : indexCode.trim();
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public Integer getTradeYear() {
        return tradeYear;
    }

    public void setTradeYear(Integer tradeYear) {
        this.tradeYear = tradeYear;
    }

    public Integer getTradeMonth() {
        return tradeMonth;
    }

    public void setTradeMonth(Integer tradeMonth) {
        this.tradeMonth = tradeMonth;
    }

    public Integer getTradeWeek() {
        return tradeWeek;
    }

    public void setTradeWeek(Integer tradeWeek) {
        this.tradeWeek = tradeWeek;
    }
}