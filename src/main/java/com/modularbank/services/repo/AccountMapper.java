package com.modularbank.services.repo;

import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.dto.response.BalanceOfAccount;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.entity.accounts.AccountsBalanceEntity;
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
          //  + "	b.amount,"
          //  + "	b.currency,"
      //      + "	b.id as bid"
            + " FROM "
            + "	account_info acc WHERE acc.id=#{accountId}"))  //INNER JOIN signup_with S ON U.signup_with_id = S.id
    @Results(value={
           @Result(property="customerId" , column="customer_id"),
           @Result(property="id" , column="id"),
           @Result(property="country", column="country"),
          // @Result(property="accountsBalanceEntityList", column="account_id" ,javaType= List.class, many=@Many(select="selectBalances")),
           //@Result(property="signUpWith.deviceName", column="device_name")
      })
    Optional<AccountInfoEntity> getAccountById(@Param("accountId") Long accountId);

    @Select("SELECT amount,currency,account_id FROM account_balances WHERE account_id = #{accountId}")
    @Results(value={
            @Result(property="amount", column ="amount" ),
            @Result(property="currency", column = "currency"),
            @Result(property="accountId", column = "account_id")
    })
    List<AccountsBalanceEntity> selectBalances(@Param("accountId") Long accountId);


    @Select("SELECT amount,currency,account_id FROM account_balances WHERE account_id = #{accountId} and currency=#{currency}")
    @Results(value={
            @Result(property="amount", column ="amount" ),
            @Result(property="currency", column = "currency"),
            @Result(property="accountId", column = "account_id")
    })
    AccountsBalanceEntity selectBalanceByCurrencyAndAccountId(@Param("accountId") Long accId,@Param("currency") String currency);

    @Update("UPDATE account_balances SET "
            + "amount=#{amount}"
            + " WHERE account_id=#{accountId} and currency=#{currency}")
    void update(@Param("accountId") Long accountId,@Param("amount") Double amount,@Param("currency") String currency);

    @Select("INSERT INTO account_info ("
            + "country,"
            + "customer_id"
            + "	) VALUES ("
            + "	#{accountRequest.country},"
            + "	#{accountRequest.customerId} "
            + ") returning id ")
     Long createAccount(@Param("accountRequest") AccountInfoEntity accountRequest);
    @Insert("<script>" +
            "INSERT INTO account_balances ("
            + "amount,"
            + "currency,"
            + "account_id"
            + "	) VALUES "
            + "	 <foreach collection=\"accountsBalanceEntityList\"" +
            " item=\"balance\"  separator=','>(" +
            " #{balance.amount}," +
            " #{balance.currency}," +
            " #{balance.accountId} " +
            "  ) </foreach>"+
            "  </script>"
           )
    void createAccountBalances(List<AccountsBalanceEntity> accountsBalanceEntityList);




}
