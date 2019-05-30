package com.lzp.demo.dao;

import com.lzp.demo.model.Admin;
import com.lzp.demo.service.serviceModel.AdminModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @authorHmLzp
 * @create 2019 - 05 - 27 22:38
 */
@Mapper
public interface AdminMapper {
    Admin queryAdminInfo(@Param("telephone")String telephone, @Param("passWord")String passWord);

    Integer insertAdmin(@Param("telephone")String telephone, @Param("email")String email, @Param("passWord")String password,@Param("random")String random);

    Admin queryAdminByTelephone(String telephone);

    Integer modifyStatus(String telephone);
}
