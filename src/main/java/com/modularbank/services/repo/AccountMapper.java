package com.modularbank.services.repo;

import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AccountMapper    {

    @Select(("SELECT "
            + "	acc.id as ID, "
            + "	acc.customer_id as customer_id,"
            + "	acc.country as country"
           // + "	S.id as sid,"
            + " FROM "
            + "	account_info acc WHERE acc.id=#{accountId}"))  //INNER JOIN signup_with S ON U.signup_with_id = S.id
    @Results(value={
           @Result(property="customerId" , column="customer_id"),
           @Result(property="id" , column="id"),
           @Result(property="country", column="country"),
           //@Result(property="signUpWith.deviceName", column="device_name")
      })
    Optional<AccountInfoEntity> getAccountById(@Param("accountId") Long accountId);

    @Insert("INSERT INTO account_info ("
            + "country,"
            + "customer_id"
            + "	) VALUES ("
            + "	#{accountRequest.country},"
            + "	#{accountRequest.customerId} "
            + ")")
    @SelectKey(
            statement="SELECT * FROM account_seq",
            keyProperty="ID",before = false,resultType = Long.class

    )
     Long createAccount(@Param("accountRequest") AccountInfoEntity accountRequest);


}
