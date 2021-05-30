package com.modularbank.services.repo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.entity.transactions.TransactionInfoEntity;
import org.apache.ibatis.annotations.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Mapper
public interface TransactionsMapper {
/*
    private Long id;
    private Long accountId;
    private Double amount;
    private String currency;
    private String directionOfTransaction;
    private String description;
    private LocalDateTime createdAt;
    private Double balanceAfterTransaction;
 */
    @Select("INSERT INTO transaction_info ("
            + "account_id,"
            + "amount,"
            + "currency,"
            + "direction_transaction,"
            + "description,"
            + "created_at,"
            + "balance_after_transaction"
            + "	) VALUES ("
            + "	#{transactionRequest.accountId},"
            + "	#{transactionRequest.amount} ,"
            + "	#{transactionRequest.currency} , "
            + "	#{transactionRequest.directionOfTransaction} , "
            + "	#{transactionRequest.description} , "
            + "	#{transactionRequest.createdAt} , "
            + "	#{transactionRequest.balanceAfterTransaction} "
            + ") returning id")
    Long createTransaction(@Param("transactionRequest") TransactionInfoEntity transactionRequest);

    /*
    "account_id,"
            + "amount,"
            + "currency,"
            + "direction_transaction,"
            + "description,"
            + "created_at,"
            + "balance_after_transaction"
     */
    @Select(("SELECT "
            + "	tr.id as ID, "
            + "	tr.account_id as account_id,"
            + "	tr.currency as currency,"
            + "	tr.direction_transaction as direction_transaction,"
            + "	tr.description as description,"
            + "	tr.created_at as created_at,"
            + "	tr.amount as amount,"
            + "	tr.balance_after_transaction as balance_after_transaction"
            + " FROM "
            + "	transaction_info tr WHERE tr.account_id=#{accountId}"))
    @Results(value={
            @Result(property="id" , column="ID"),
            @Result(property="accountId" , column="account_id"),
            @Result(property="currency", column="currency"),
            @Result(property="directionOfTransaction", column="direction_transaction"),
            @Result(property="description", column="description"),
            @Result(property="amount", column="amount"),
            @Result(property="createdAt", column="created_at"),
            @Result(property="balanceAfterTransaction", column="balance_after_transaction"),
            //@Result(property="signUpWith.deviceName", column="device_name")
    })
     List<TransactionInfoEntity> getTransactionByAccountId(@Param("accountId") Long accountId);

    @Select(("SELECT "
            + "	tr.id as ID, "
            + "	tr.account_id as account_id,"
            + "	tr.currency as currency,"
            + "	tr.direction_transaction as direction_transaction,"
            + "	tr.description as description,"
            + "	tr.created_at as created_at,"
            + "	tr.amount as amount,"
            + "	tr.balance_after_transaction as balance_after_transaction"
            + " FROM "
            + "	transaction_info tr WHERE tr.id=#{transactionId}"))
    @Results(value={
            @Result(property="id" , column="ID"),
            @Result(property="accountId" , column="account_id"),
            @Result(property="currency", column="currency"),
            @Result(property="directionOfTransaction", column="direction_transaction"),
            @Result(property="description", column="description"),
            @Result(property="amount", column="amount"),
            @Result(property="createdAt", column="created_at"),
            @Result(property="balanceAfterTransaction", column="balance_after_transaction"),
            //@Result(property="signUpWith.deviceName", column="device_name")
    })
     TransactionInfoEntity getTransactionById(@Param("transactionId") Long trId);

}
