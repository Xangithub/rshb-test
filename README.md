# Тестовый проект
### Описание
Демонстрация работы локов в многопоточной среде приложения где идут одновременные транзакции с одним с банковским счётом.
Приложение создаёт в базе 2 счёта. Для разработки была использована СУБД MySQL
Операции журналируются в БД и включён аудит.
Есть тесты в виде HTTP запросов и JUnit тест


