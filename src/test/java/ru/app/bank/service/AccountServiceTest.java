package ru.app.bank.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.app.bank.domain.Account;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountServiceImpl accountService;


    @Test
    void loadTest() {
        int count = 100;
        final int add = 10;
        int beginBalance = accountService.getAccount(1L).get().getBalance();
        AtomicInteger end = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(count);
        Runnable task = () -> {
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            accountService.updateAccount(1L, 10);
            end.incrementAndGet();
        };
        for (int i = 0; i < count; i++) {
            new Thread(task).start();
        }
        while (end.get() != count) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Optional<Account> account = accountService.getAccount(1L);
        assertEquals(beginBalance+count * add, account.get().getBalance());
    }

    @Test
    void getAccount() {
        Optional<Account> account = accountService.getAccount(1L);
        String owner = account.get().getOwner();
        assertEquals("Atlant", owner);
    }
}