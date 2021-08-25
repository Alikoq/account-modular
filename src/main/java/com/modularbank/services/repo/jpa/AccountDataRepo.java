package com.modularbank.services.repo.jpa;

import com.modularbank.services.entity.accounts.AccountDataEntity;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountDataRepo extends JpaRepository<AccountDataEntity,Long> {

    @Query("select case when count(a)>0 then true else false end from AccountDataEntity a where a.email=:email")
    Boolean getAccountByEmailCustom(@Param("email") String email);

}
