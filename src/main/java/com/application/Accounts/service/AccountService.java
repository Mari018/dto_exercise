package com.application.Accounts.service;

import com.application.Accounts.dto.AccountDTO;
import com.application.Accounts.dto.UpdateAccountDTO;
import com.application.Accounts.entity.Account;
import com.application.Accounts.entity.Vehicle;
import com.application.Accounts.exception.EmailAlreadyExistsException;
import com.application.Accounts.exception.UserNotFoundException;
import com.application.Accounts.mapper.AccountMapper;
import com.application.Accounts.repository.AccountRepository;
import com.application.Accounts.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = accountMapper.accountDTOToAccount(accountDTO);

        if (accountRepository.findByEmail(accountDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("email already exists");
        }

        return accountMapper.accountToAccountDTO(accountRepository.save(account));
    }

    public AccountDTO activateAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user do not found"));
        account.setActive(true);
        accountRepository.save(account);
        return AccountMapper.INSTANCE.accountToAccountDTO(account);
    }

    public AccountDTO deactivateAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new UserNotFoundException("user do nor found"));
        account.setActive(false);
        accountRepository.save(account);
        return AccountMapper.INSTANCE.accountToAccountDTO(account);
    }

    public AccountDTO deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user do not exist"));

        if(!account.getVehicles().isEmpty()){
            throw new RuntimeException("cant delete an account");
        }
        accountRepository.delete(account);
        return AccountMapper.INSTANCE.accountToAccountDTO(account);
    }

    public List<AccountDTO> getDeactivatedAccounts() {
        List<Account> accounts = new ArrayList<>(accountRepository.findAllByActiveFalse());
        return AccountMapper.INSTANCE.listAccountToListAccountDTO(accounts);
    }

    public List<UpdateAccountDTO> getFNameAndLNameDeactivated() {
        List<Account> accounts = new ArrayList<>(accountRepository.findAllByActiveFalse());
        return AccountMapper.INSTANCE.listAccountToListUpdate(accounts);
    }

    public UpdateAccountDTO updateFAndLName(Long id, UpdateAccountDTO updateAccountDTO) {
        Account account =  AccountMapper.INSTANCE.updateAccountToAccount(updateAccountDTO);
        account.setId(id);
        return AccountMapper.INSTANCE.accountToUpdateAccount(accountRepository.save(account));
    }

    public AccountDTO updateAccount(AccountDTO accountDTO, Long id) {
        Account account = AccountMapper.INSTANCE.accountDTOToAccount(accountDTO);
       account.setId(id);
       return AccountMapper.INSTANCE.accountToAccountDTO(accountRepository.save(account));
    }
}
