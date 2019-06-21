package com.lzp.demo.controller;

import com.lzp.demo.model.Admin;
import com.lzp.demo.service.ExpenditureService;
import com.lzp.demo.service.serviceModel.AdminModel;
import com.lzp.demo.service.serviceModel.ExpenditureModel;
import com.lzp.demo.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 查询 moneyInfo
 * @authorHmLzp
 * @create 2019 - 06 - 20 23:44
 */
@RestController
@RequestMapping("/expenditure")
public class ExpenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    @PostMapping("/getMoneyInfo")//String token, @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,@DateTimeFormat(pattern = "yyyy-MM-dd")  Date endDate, Integer type
    public List<ExpenditureModel> queryMoneyInfo(HttpServletRequest request){//token,startDate,endDate,type
        AdminModel admin = (AdminModel) request.getSession().getAttribute("admin");
        return expenditureService.queryMoneyInfo(admin);
    }


}
