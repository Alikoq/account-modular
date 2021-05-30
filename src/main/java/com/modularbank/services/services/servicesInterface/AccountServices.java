package com.modularbank.services.services.servicesInterface;

import com.modularbank.services.dto.baseResponse.CommonResponse;
import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.dto.response.CreatedAccountResponse;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountServices {
     CommonResponse<CreatedAccountResponse> createAccount(AccountRequest accountRequest);
     CommonResponse<CreatedAccountResponse> getAccountByAccountId(Long accountId);

}
