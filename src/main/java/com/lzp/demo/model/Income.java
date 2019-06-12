package com.lzp.demo.model;


import java.io.Serializable;
import java.util.Date;

public class Income implements Serializable {

  private Integer id;
  private double incomemoney;//收入金额
  private Date incomedate;//收入时间
  private String incomereason;//收入原因
  private Integer adminid;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public double getIncomemoney() {
    return incomemoney;
  }

  public void setIncomemoney(double incomemoney) {
    this.incomemoney = incomemoney;
  }

  public Date getIncomedate() {
    return incomedate;
  }

  public void setIncomedate(Date incomedate) {
    this.incomedate = incomedate;
  }

  public String getIncomereason() {
    return incomereason;
  }

  public void setIncomereason(String incomereason) {
    this.incomereason = incomereason;
  }

  public Integer getAdminid() {
    return adminid;
  }

  public void setAdminid(Integer adminid) {
    this.adminid = adminid;
  }
}
