##О проекте
Приложение реализует простейшие взаимодейтсвия с бд такие, как создать пользователя с нулевым балансом и получить информацию о пользователе по его id. В качестве программного интерфейса выступает веб-интерфейс с главной страницей, страницей логина, страницей регистрации и личным кабинетом

##Необходимо
- Java 1.8
- Maven 3.x.x

##Что использовалось в проекте
- Spring Boot (MVC, Security)
- JDBC
- H2 Database

##Запуск
Из корневой папки проекта
```
mvn spring-boot:run
```
Сервер запускается по адресу http://localhost:8080/

##Как пользоваться
- http://localhost:8080/ - главная страница
- http://localhost:8080/login - главная страница
- http://localhost:8080/register - главная страница
- http://localhost:8080/profile - главная страница

Заранее созданные пользователи (login, password, balance, id):
- admin, admin 1337, 1
- qwe, rty, 777, 2

Запросы фронта к api данного приложения уходят на:
- /api/v1/user/{id} - получить всю информацию о пользователе