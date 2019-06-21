package com.lzp.demo.service;

import com.lzp.demo.model.Admin;
import com.lzp.demo.service.serviceModel.AdminModel;
import com.lzp.demo.service.serviceModel.ExpenditureModel;
import com.lzp.demo.utils.ResultMap;

import java.util.Date;
import java.util.List;

public interface ExpenditureService {//String token, Date startDate, Date endDate, Integer type
    List<ExpenditureModel> queryMoneyInfo(AdminModel admin);
}
