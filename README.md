# Seven Winds Test

## Краткое описание

REST-сервис на Java. Оригинальное описание задания [здесь](https://docs.google.com/document/d/1qLEQdIDeWTlUYiMWvirYwPF2dc--sjANJy0REn-6BTE/edit). Для наглядности описание задания продублировано ниже:

### Тестовое задание Kotlin/Java Seven Winds Studio

Простое приложение по чтению и записи в БД.

На вход - POST / GET запросы, фронтенд писать не нужно. Принять данные от пользователя, записать их в БД. Потом для другого запроса отдать обратно.

Данные для передачи: e-mail, фамилия, имя, отчество, номер телефона.

Библиотеки и инструменты для подключения

- Flyway для управления структурой БД. Т.е. чтобы не спринг создавал таблицы так как сам считает нужным, а мы сами имели контроль над этим процессом
- Swagger для построения и отображения публичного апи
- Вести разработку с использованием системы контроля версий Git
- 1-2 Unit теста на получившийся код

Доступы в github передать:

- [https://github.com/ITurchenko](https://github.com/ITurchenko)
- [mihanvr@gmail.com](mailto:mihanvr@gmail.com)

P.S. Предпочтительнее реализация тестового на Kotlin. Просьба, не увлекаться использованием коробочных фич Spring для CRUD, предпочтительнее Ktor+Exposed :-P

### Используемые фреймворки и библиотеки:

- Spring (Boot, Hibernate, Data JPA, Test)
- Lombok
- Swagger-UI
- JUnit 5
- Встраиваемая СУБД H2
- Flyway

### Сборка проекта

```
$ cd seven-winds-test
$ mvn package
```

### Запуск проекта:

```jsx
java -jar ./target/seven-winds-test-0.0.1-SNAPSHOT.jar
```

### Swagger документация

[http://localhost:8080/seven-winds-test/swagger-ui.html](http://localhost:8080/seven-winds-test/swagger-ui.html)

## Краткое описание классов

В пакете `io.github.anton_petrunov.seven_winds_test` проекта находится класс:

- `SevenWindsTestApplication.java` — содержит в себе точку входа в приложение

В пакете `error` содержатся классы:

- `AppException`
- `IllegalRequestDataException`
- `NotFoundException`

В пакете`model` содержится класс:

- `User` — класс-модель данных, хранящихся `UserRepository`

В пакете`repository` содержится интерфейс хранилища данных, логика которого автоматически имплементируется с помощью библиотеки `Spring Data JPA`:

- `UserRepository` — интерфейс взаимодействия с БД `users`

В пакете `util` содержится класс:

- `ValidationUtil` — служит для валидации данных, приходящих в контроллеры

В пакете `web` содержится класс:

- `UserRestController` — контроллер, который принимает все команды сервиса:
    - `/users` — получение всех пользователей
    - `/users/{id}` — получение конкретного пользователя по `ID`
    - `/users` — создание нового пользователя

## Описание API

### Получение и создание пользователей

Для получения всех пользователей необходимо послать GET-запрос на адрес `/users`

В ответе будет содержаться массив `JSON`-объектов со следующими полями:

- `id` — ID пользователя
- `email` — Email пользователя
- `surname` — фамилия пользователя
- `name` — имя
- `patronymic` — отчество
- `phone` — телефон

Пример запроса:

```json
http://localhost:8080/seven-winds-test/users
```

Пример ответа:

```jsx
[
  {
    "id": 1,
    "email": "petrunov.ru@gmail.com",
    "surname": "petrunov",
    "name": "Anton",
    "patronymic": "Nikolaich",
    "phone": "+79312211019"
  },
  {
    "id": 2,
    "email": "petrunov@kirill.ru",
    "surname": "Petrunov",
    "name": "Kirill",
    "patronymic": "Antonovich",
    "phone": "+79312211019"
  }
]
```

Для получения пользователя с конкретным выбранным `ID` необходимо послать GET-запрос на эндпоинт `/users/{id}`

Пример запроса:

```json
http://localhost:8080/seven-winds-test/users/2
```

Пример ответа:

```jsx
{
  "id": 2,
  "email": "petrunov@kirill.ru",
  "surname": "Petrunov",
  "name": "Kirill",
  "patronymic": "Antonovich",
  "phone": "+79312211019"
}
```

В случае, если `ID` *не* будет числом, клиенту возвращается код `400 Bad Request`

Для создания нового нового пользователя необходимо послать POST-запрос на адрес `/users`

Пример тела запроса:

```jsx
{
"email": "string@mail",
"surname": "Sur",
"name": "Nam",
"patronymic": "Pat",
"phone": "+1234"
}
```

Пример ответа:

```jsx
{
  "id": 3,
  "email": "string@mail",
  "surname": "Sur",
  "name": "Nam",
  "patronymic": "Pat",
  "phone": "+1234"
}
```

Если в теле запроса будет допущена ошибка, то клиенту возвращается код `400 Bad Request`
