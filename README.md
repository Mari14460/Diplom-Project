## Запуск приложения

1. Скачать Docker Desktop по ссылке https://docs.docker.com/desktop/
2. Установать Docker Desktop
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
10. Запустить команду в терминале
```
java -jar aqa-shop.jar
```
11. SUT должен работать по адресу в браузере:
```
http://localhost:8080/
```
