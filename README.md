## Запуск приложения

1. Скачать Docker Desktop по ссылке https://docs.docker.com/desktop/
2. Установить Docker Desktop
3. Запустить Docker Desktop
4. Создать клон репозитория или скачать архив https://github.com/Mari14460/Diploma-Project
5. Открыть проект в IntelliJ IDEA
6. Открыть вкладку терминала в IntelliJ IDEA
7. В зависимости от тестируемой базы данных отредактировать docker-compose.yml файл
8. Запустить команду в терминале
```
docker-compose up
```
9. Открыть новую вкладку терминала в IntelliJ IDEA
10. Для тестирования с **MySQL DB** запустить команду в терминале
```
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar
```
Запуск тестов (в новой вкладке терминала):
```
./gradlew test -Ddatasource.url=jdbc:mysql://localhost:3306/app -Ddatasource.username=app -Ddatasource.password=pass
```
Создание и просмотр отчета:
```
./gradlew allureServe
```

11. Для тестирования с **PostgreSQL** DB запустить команду в терминале
```
java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar
```
Запуск тестов (в новой вкладке терминала):
```
./gradlew test -Ddatasource.url=jdbc:postgresql://localhost:5432/app -Ddatasource.username=app -Ddatasource.password=pass
```
Создание и просмотр отчета:
```
./gradlew allureServe
```
12. SUT работает по адресу в браузере:
```
http://localhost:8080/
```

