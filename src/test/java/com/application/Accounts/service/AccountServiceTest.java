package com.application.Accounts.service;

import com.application.Accounts.dto.AccountDTO;
import com.application.Accounts.entity.Account;
import com.application.Accounts.exception.EmailAlreadyExistsException;
import com.application.Accounts.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
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

        Optional<Account> optional = Optional.empty();

        Mockito.lenient().when(accountRepository.findByEmail(accountDTO.getEmail())).thenReturn(optional);
        Mockito.lenient().when(accountRepository.save(any(Account.class))).thenReturn(account);

        accountService.createAccount(accountDTO);

        verify(accountRepository, times(1)).findByEmail(accountDTO.getEmail());
        verify(accountRepository, times(1)).save(any(Account.class));

        assertNotNull(accountDTO);
        assertEquals(accountDTO.getFirstName(), account.getFirstName());
        assertEquals(accountDTO.getLastName(), account.getLastName());
        assertEquals(accountDTO.getAge(), account.getAge());
        assertEquals(accountDTO.getEmail(), account.getEmail());
        assertEquals(accountDTO.getAddress(), account.getAddress());
        assertEquals(accountDTO.getVehicles(), account.getVehicles());
    }

    @Test
    void when_CreateAccount_then_ReturnError() {
        AccountDTO accountDTO = new AccountDTO("Mario", "oliveira", 21, "test@example.com", "address", new ArrayList<>());

        Account account = new Account();
        account.setFirstName("Mario");
        account.setLastName("oliveira");
        account.setAge(21);
        account.setEmail("test@example.com");
        account.setAddress("address");
        account.setVehicles(new ArrayList<>());

        Optional<Account> optional = Optional.of(account);

        when(accountRepository.findByEmail(accountDTO.getEmail())).thenReturn(optional);

        assertThrows(EmailAlreadyExistsException.class, () -> accountService.createAccount(accountDTO));

        verify(accountRepository, times(1)).findByEmail(accountDTO.getEmail());
        verify(accountRepository, times(0)).save(any(Account.class));

        assertEquals(accountDTO.getEmail(), account.getEmail());


    }

    @Test
    void when_activateAccount_return(){

        Account account = new Account();
        account.setFirstName("Mario");
        account.setLastName("oliveira");
        account.setAge(21);
        account.setEmail("test@example.com");
        account.setAddress("address");
        account.setVehicles(new ArrayList<>());

        Optional<Account> optional = Optional.of(account);

        when(accountRepository.findById(any(Long.class))).thenReturn(optional);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        accountService.activateAccount(any(Long.class));

        verify(accountRepository,times(1)).findById(any(Long.class));
        verify(accountRepository,times(1)).save(any(Account.class));

    }
}