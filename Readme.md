Создайте в DB postgresql схему: create schema bookshop;
В application.properties измените настройки подключения (url,username,password) на свои
Запуск приложения командой: mvn clean spring-boot:run

1)Добавление новой книги в таблицу book: POST: http://localhost:8081/book
    {
        "title": "php",
        "author": "alex",
        "description": "book about php"
    }
2)Фильтр возвращающий список всех книг, отсортированные в обратном алфавитном порядке по колонке title: GET: http://localhost:8081/book
3)Фильтр возвращающий список всех книг, сгруппированных по автору книги: GET: http://localhost:8081/book?author=name

4)Обновление записи о книге: PUT: http://localhost:8081/book
    {
        "id":1,
        "title": "php",
        "author": "alex",
        "description": "book about php 2022 year"
    }
5)Удаление книги из списка: DELETE: http://localhost:8081/book/3
6)Возвращает книгу по id: GET: http://localhost:8081/book/1


