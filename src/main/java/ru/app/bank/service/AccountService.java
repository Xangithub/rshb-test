package ru.app.bank.service;

import org.springframework.transaction.annotation.Transactional;
import ru.app.bank.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    @Transactional
    Account updateAccount(Long numberOfAccount, Integer operation);

    List<Account> readAllAccount();

    Optional<Account> getAccount(Long id);

    Long createAccount(String name);
}
