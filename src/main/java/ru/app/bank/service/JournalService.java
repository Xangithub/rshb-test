package ru.app.bank.service;

public interface JournalService {
    void saveEvent(Long account, String operation);
}
