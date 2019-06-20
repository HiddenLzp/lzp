package com.lzp.demo.service.impl;

import com.lzp.demo.dao.ExpenditureMapper;
import com.lzp.demo.model.Admin;
import com.lzp.demo.model.Expenditure;
import com.lzp.demo.service.CommonService;
import com.lzp.demo.service.ExpenditureService;
import com.lzp.demo.service.serviceModel.ExpenditureModel;
import com.lzp.demo.utils.DateUtil;
import com.lzp.demo.utils.ErrorCode;
import com.lzp.demo.utils.ErrorMessage;
import com.lzp.demo.utils.ResultMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @authorHmLzp
 * @create 2019 - 06 - 20 23:30
 */
@Service
public class ExpenditureServiceImpl implements ExpenditureService {

    @Autowired
    private ExpenditureMapper expenditureMapper;

    @Autowired
    private CommonService commonService;

    @Override//String token ,Date startDate, Date endDate, Integer type
    public List<ExpenditureModel> queryMoneyInfo() {
        try{
            /*Admin admin = commonService.getAdmin(token);
            if(admin != null){*/
                //startDate, endDate, admin.getId(), type
                List<ExpenditureModel> expenditures = expenditureMapper.queryMoneyInfo(null,null,1,null);

                for (int i = 0; i < expenditures.size(); i++) {
                    ExpenditureModel expenditureModel = expenditures.get(i);
                    expenditureModel.setIndex((i+1));
                    expenditureModel.setPaydateValue(DateUtil.conversionDateToString2(expenditureModel.getPaydate()));
                    if(expenditureModel.getType() == 1){
                        expenditureModel.setTypeValue("Yes");
                    }else {
                        expenditureModel.setTypeValue("No");
                    }
                }
                return expenditures;
            /*}else {
                return ResultMap.error(ErrorCode.SERVER_ERROR, ErrorMessage.SERVER_ERROR);
            }*/
        }catch ( Exception e){

            return null;
        }
    }
}
