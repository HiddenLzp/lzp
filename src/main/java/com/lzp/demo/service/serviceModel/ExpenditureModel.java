package com.lzp.demo.service.serviceModel;

import java.util.Date;

/**
 * @authorHmLzp
 * @create 2019 - 06 - 21 0:04
 */
public class ExpenditureModel {
    private Integer id;
      private Integer adminid;
      private double paymoney;//支出金额
      private Date paydate;//支出时间
      private String payreason;//支出原由

    private Integer type;//支出类型
    private String typeValue;

    private String paydateValue;

    private Integer index;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getPaydateValue() {
        return paydateValue;
    }

    public void setPaydateValue(String paydateValue) {
        this.paydateValue = paydateValue;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
