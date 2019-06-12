package com.lzp.demo.model;


import java.io.Serializable;
import java.util.Date;

public class Expenditure  implements Serializable {

  private Integer id;
  private Integer adminid;
  private double paymoney;//支出金额
  private Date paydate;//支出时间
  private String payreason;//支出原由

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAdminid() {
    return adminid;
  }

  public void setAdminid(Integer adminid) {
    this.adminid = adminid;
  }

  public double getPaymoney() {
    return paymoney;
  }

  public void setPaymoney(double paymoney) {
    this.paymoney = paymoney;
  }

  public Date getPaydate() {
    return paydate;
  }

  public void setPaydate(Date paydate) {
    this.paydate = paydate;
  }

  public String getPayreason() {
    return payreason;
  }

  public void setPayreason(String payreason) {
    this.payreason = payreason;
  }
}
