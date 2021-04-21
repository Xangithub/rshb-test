package ru.app.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.app.bank.domain.Account;
import ru.app.bank.repository.AccountRepository;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}


	/**
	 * Наполнение для БД
	 */
	@Bean
	public CommandLineRunner demo(AccountRepository repository) {
		return (args) -> {
			repository.save(new Account().setOwner("Atlant").setBalance(1000));
			repository.save(new Account().setOwner("Kolt").setBalance(2000));
		};
	}

	/**
	 * Заглушка для аудита. Возвращает пользователя
	 */
	@Bean
	public AuditorAware<String> auditorProvider() {
//		SecurityContextHolder.getContext().getAuthentication().getName()
		return () -> Optional.ofNullable("Xaxa");
	}

}
