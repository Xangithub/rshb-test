package ru.app.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.bank.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

}
