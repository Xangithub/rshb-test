package ru.app.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.app.bank.domain.Account;
import ru.app.bank.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    public static final int BALANCE = 0;
    private final AccountRepository accountRepository;
    private final JournalService journalService;

    @Override
    @Transactional
    public Account updateAccount(Long numberOfAccount, Integer operation) {
        Account account = accountRepository.findAccountById(numberOfAccount);
        account.setBalance(account.getBalance() + operation);
        Account savedAccount = accountRepository.save(account);
        journalService.saveEvent(numberOfAccount, operation.toString());
        return savedAccount;
    }

    @Override
    public List<Account> readAllAccount() {
       return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Long createAccount(String name) {
        Account account = new Account().setBalance(BALANCE).setOwner(name);
        Account savedAccount = accountRepository.save(account);
        return savedAccount.getId();
    }
}
