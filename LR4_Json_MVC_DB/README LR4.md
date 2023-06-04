# [LR4](https://github.com/Egorrss/OOP/tree/main/LR4_Json_MVC_DB)

### Задание:
>Разработать страницу отображающую список объектов, с хранением их на сервере в БД MySQL WorkBench, передача данных осуществляется по JSON, 
>клиент и сервер общаются по средством передачи данных. Применить шаблон проектирования MVC. На странице предусмотреть форму добавления объектов в список.
> #### Основные условия выполнения
>- Сформировать строку JSON
>- Передать строку JSON на сервер
>- Должно быть не менее 5 свойств у объекта (Например: Список студентов 1 - Имя, 2 - Фамилия,  3- группа, 4 - возраст,  5- изучаемый предмет)
>- Должны быть все четыре элемента CRUD (create-read-update-delete)

### Основные файлы
1. [view.html](https://github.com/Egorrss/OOP/blob/main/LR4_Json_MVC_DB/src/main/webapp/view.html) - страница-интерфейс, с помощью которого пользователь взаимодействует с программой. 
CRUD операции реализованы с помощью ajax запросов. 

2. [Model_library.java](https://github.com/Egorrss/OOP/blob/main/LR4_Json_MVC_DB/src/main/java/com/example/Model_library.java) - модель библиотеки в котром реализованы методы добавления, чтения, обновления и удаления объектов. Из этого класса программа взаимодействует с БД.

3. [Book.java](https://github.com/Egorrss/OOP/blob/main/LR4_Json_MVC_DB/src/main/java/com/example/Book.java) - объект Книга, который кладется в библиотеку и посылается в виде Json.

4. [ControllerServlet.java](https://github.com/Egorrss/OOP/blob/main/LR4_Json_MVC_DB/src/main/java/com/example/ControllerServlet.java) - сервлет-контроллер. 
Через view.html пользователь отправляет информацию на сервлет-контроллер. 
Там информация принимается, обрабатывается, записывается в модель.
Для осуществления CRUD операций реализованы методы doPost, doGet, doPut, doDelete соответственно. 

#### view.html:
![image](https://github.com/Egorrss/OOP/assets/129698533/66981d53-1aad-45b2-bfa1-74bdf629d42a)


#### db:
![image](https://github.com/Egorrss/OOP/assets/129698533/65349631-eea5-4dd8-bb4e-7db50147546d)
