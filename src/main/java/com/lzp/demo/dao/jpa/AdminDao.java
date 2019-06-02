package com.lzp.demo.dao.jpa;

import com.lzp.demo.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface AdminDao extends JpaRepository<AdminDao,Long> {

}
