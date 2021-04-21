package ru.app.bank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.bank.domain.Account;
import ru.app.bank.dto.AccountDto;
import ru.app.bank.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> read(@PathVariable Long id)  {
        return accountService.getAccount(id)
                .map( account -> ResponseEntity.ok(account))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/account")
    public ResponseEntity<List<Account>> read() {
        List<Account> accountList = accountService.readAllAccount();
        return new ResponseEntity(accountList,HttpStatus.OK);
    }

    @PutMapping("/account")
    public ResponseEntity<Account> update(@RequestBody AccountDto account) {
        Long number = account.getNumber();
        if (number == null) {
            return ResponseEntity.badRequest().build();
        }
        Integer op = account.getOperation();
        Account updatedAcc = accountService.updateAccount(number, op);
        return new ResponseEntity(updatedAcc,HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity<Integer> create(@RequestBody AccountDto account) {
        Long accNumber = accountService.createAccount(account.getName());
        return new ResponseEntity(accNumber,HttpStatus.OK);
    }
}
