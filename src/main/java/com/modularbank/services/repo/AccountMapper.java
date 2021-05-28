package com.modularbank.services.repo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper  {

    @Insert("INSERT INTO accountInfo(customer_id,)")
}
