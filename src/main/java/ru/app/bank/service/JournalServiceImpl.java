package ru.app.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.app.bank.domain.Journal;
import ru.app.bank.repository.JournalRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;

    @Override
    @Transactional
    public void saveEvent(Long account, String operation){
        Journal journalEvent = new Journal().setAccount(account).setOperation(operation).setTime(Instant.now());
        journalRepository.save(journalEvent);
    }

}
