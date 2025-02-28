package com.application.Accounts.service;

import com.application.Accounts.dto.AccountDTO;
import com.application.Accounts.entity.Account;
import com.application.Accounts.mapper.AccountMapper;
import com.application.Accounts.mapper.AccountMapperImpl;
import com.application.Accounts.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountMapper accountMapper;

    @BeforeEach
    void before() {
        System.setProperty("mockito.mock.maker", "mock-maker-default");
    }

    @Test
    void when_CreateAccount_then_CreateUser() {
        AccountDTO accountDTO = new AccountDTO("Mario", "oliveira", 21, "test@example.com", "address", new ArrayList<>());

        Account account = new Account();
        account.setFirstName("Mario");
        account.setLastName("oliveira");
        account.setAge(21);
        account.setEmail("test@example.com");
        account.setAddress("address");
        account.setVehicles(new ArrayList<>());
        account.setId(1L);
        account.setActive(true);

        Optional<Account> optional = Optional.empty();

        when(accountRepository.findByEmail(accountDTO.getEmail())).thenReturn(optional);
        when(accountMapper.accountDTOToAccount(any(AccountDTO.class))).thenReturn(account);
        Mockito.lenient().when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(accountMapper.accountToAccountDTO(any(Account.class))).thenReturn(accountDTO);

        accountService.createAccount(accountDTO);

        verify(accountRepository, times(1)).findByEmail(accountDTO.getEmail());
        verify(accountMapper, times(1)).accountDTOToAccount(any(AccountDTO.class));
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(accountMapper, times(1)).accountToAccountDTO(any(Account.class));



        assertNotNull(accountDTO);
        assertEquals(accountDTO.getFirstName(), account.getFirstName());
        assertEquals(accountDTO.getLastName(), account.getLastName());
        assertEquals(accountDTO.getAge(), account.getAge());
        assertEquals(accountDTO.getEmail(), account.getEmail());
        assertEquals(accountDTO.getAddress(), account.getAddress());
        assertEquals(accountDTO.getVehicles(), account.getVehicles());


    }

}