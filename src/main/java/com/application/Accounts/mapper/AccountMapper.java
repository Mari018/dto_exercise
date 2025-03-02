package com.application.Accounts.mapper;

import com.application.Accounts.dto.AccountDTO;
import com.application.Accounts.dto.UpdateAccountDTO;
import com.application.Accounts.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account accountDTOToAccount (AccountDTO accountDTO);

    AccountDTO accountToAccountDTO (Account account);

    List<AccountDTO> listAccountToListAccountDTO (List<Account> accounts);

    Account updateAccountToAccount (UpdateAccountDTO updateAccountDTO);

    UpdateAccountDTO accountToUpdateAccount (Account account);

    List<UpdateAccountDTO> listAccountToListUpdate (List<Account> accounts);
}
