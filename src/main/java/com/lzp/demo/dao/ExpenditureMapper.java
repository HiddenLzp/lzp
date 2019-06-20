package com.lzp.demo.dao;

import com.lzp.demo.model.Expenditure;
import com.lzp.demo.service.serviceModel.ExpenditureModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ExpenditureMapper {

    /**
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param adminId 用户Id
     * @param type 类型
     * @return 返回集合
     */
    List<ExpenditureModel> queryMoneyInfo(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("adminId")Integer adminId, @Param("type") Integer type);
}
