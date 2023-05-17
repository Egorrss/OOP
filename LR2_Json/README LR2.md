# [LR2]([https://github.com/Egorrss/OOP/tree/main/LR1_about_us](https://github.com/Egorrss/OOP/tree/main/LR2_Json))

_Задание:
Разработать страницу отображающую список объектов, с хранением их на сервере в файле, 
передача данных осуществляется по JSON, клиент и сервер общаются по средством передачи данных. 
На странице предусмотреть форму добавления объектов в список._

_Основные условия выполнения_
- _Сформировать строку JSON_
- _Передать строку JSON на сервер_
- _Должно быть не менее 5 свойств у объекта (Например: Список студентов 1 - Имя, 2 - Фамилия,  3- группа, 4 - возраст,  5- изучаемый предмет)_
- _Записать полученную строку в файл на сервере_

### Основные файлы
1. main.html страница расположена в файле: [main.html](https://github.com/Egorrss/OOP/blob/main/LR2_Json/src/main/webapp/main.html)
2. BookServlet расположен в файле: [BookServlet](https://github.com/Egorrss/OOP/blob/main/LR2_Json/src/main/java/com/example/BookServlet.java)
3. Для обработки объектов был создан класс [Book](https://github.com/Egorrss/OOP/blob/main/LR2_Json/src/main/java/com/example/Book.java)

main.html:
![image](https://github.com/Egorrss/OOP/assets/129698533/4836e9c4-ea5b-4a58-b899-1c564e860be8)

library.txt:

![image](https://github.com/Egorrss/OOP/assets/129698533/81652063-cf6a-426d-b45c-2df50b938b22)

### Коментарии к коду [BookServlet](https://github.com/Egorrss/OOP/blob/main/LR2_Json/src/main/java/com/example/BookServlet.java)
1. Метод init. При запуске сервера в список List<Book> library выгружаются данные из БД (файла library.txt)

2. Метод doGet загружает информацию о книгах из файла library.txt на сервере и отправляет ее в формате JSON клиенту для отображения на странице main.html.

3. Метод doPost обрабатывает запрос от клиента, добавляет новую книгу в список и отправляет обновленную информацию о книгах в формате JSON обратно на клиентскую сторону.
