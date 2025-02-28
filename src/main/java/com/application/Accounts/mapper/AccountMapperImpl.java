package com.application.Accounts.mapper;

import com.application.Accounts.dto.AccountDTO;
import com.application.Accounts.dto.UpdateAccountDTO;
import com.application.Accounts.entity.Account;

import java.util.List;

public class AccountMapperImpl implements AccountMapper{
    @Override
    public Account accountDTOToAccount(AccountDTO accountDTO) {
        return new Account();
    }
    @Override
    public AccountDTO accountToAccountDTO(Account account) {
        return new AccountDTO();
    }

    @Override
    public List<AccountDTO> listAccountToListAccountDTO(List<Account> accounts) {
        return List.of(new AccountDTO());
    }

    @Override
    public Account updateAccountToAccount(UpdateAccountDTO updateAccountDTO) {
        return new Account();
    }

    @Override
    public UpdateAccountDTO accountToUpdateAccount(Account account) {
        return new UpdateAccountDTO();
    }

    @Override
    public List<UpdateAccountDTO> listAccountToListUpdate(List<Account> accounts) {
        return List.of(new UpdateAccountDTO());
    }
}
