package com.application.Accounts.controller;

import com.application.Accounts.dto.AccountDTO;
import com.application.Accounts.dto.UpdateAccountDTO;
import com.application.Accounts.exception.UserNotFoundException;
import com.application.Accounts.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDTO accountDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
        return ResponseEntity.ok( accountService.createAccount(accountDTO));
    }

    @PatchMapping(path = "activate/{id}")
    public ResponseEntity<?> activateAccount(@PathVariable Long id) {
        try {

            return ResponseEntity.ok(accountService.activateAccount(id));

        } catch (UserNotFoundException e) {
            Error error = new Error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PatchMapping(path = "deactivate/{id}")
    public ResponseEntity<?> deactivateAccount(@PathVariable Long id) {
        try {

            return ResponseEntity.ok(accountService.deactivateAccount(id));
        } catch (UserNotFoundException e) {
            Error error = new Error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(accountService.deleteAccount(id));
        } catch (UserNotFoundException e) {
            Error error = new Error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping(path = "/deactivated")
    public ResponseEntity<?> getDeactivatedAccounts() {
        return ResponseEntity.ok(accountService.getDeactivatedAccounts());

    }

    @GetMapping(path = "/names/deactivated")
    public ResponseEntity<?> getFNameAndLNameDeactivated() {
      return ResponseEntity.ok(accountService.getFNameAndLNameDeactivated());
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateFAndLName (@PathVariable Long id, @RequestBody UpdateAccountDTO accountDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(accountService.updateFAndLName(id,accountDTO));

    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateAccount (@PathVariable Long id, @RequestBody AccountDTO accountDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(accountService.updateAccount(accountDTO,id));
    }
}
