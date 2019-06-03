package com.lzp.demo.dao.jpa;

import com.lzp.demo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,Integer> {
}
