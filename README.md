## Запуск приложения

1. Установать Docker Desktop (скачать можно по ссылке https://docs.docker.com/desktop/)
2. Запустить Docker Desktop
3. Открыть вкладку терминала в IntelliJ IDEA
4. В зависимости от тестируемой базы данных отредактировать docker-compose.yml файл
5. Запустить команду в терминале
```
docker-compose up
```
6. Создать клон репозитория или скачать архив https://github.com/Mari14460/Diploma-Project
7. Открыть новую вкладку терминала в IntelliJ IDEA
8. Запустить команду в терминале
```
java -jar aqa-shop.jar
```
9. SUT должен работать по адресу:
```
http://localhost:8080/
```
