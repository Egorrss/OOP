# [LR3](https://github.com/Egorrss/OOP/tree/main/LR3_Json_MVC)

### Задание:
>Разработать страницу отображающую список объектов, с хранением их на сервере в файле, передача данных осуществляется по JSON, 
>клиент и сервер общаются по средством передачи данных. Применить шаблон проектирования MVC.На странице предусмотреть форму добавления объектов в список.
> #### Основные условия выполнения
>- Сформировать строку JSON
>- Передать строку JSON на сервер
>- Должно быть не менее 5 свойств у объекта (Например: Список студентов 1 - Имя, 2 - Фамилия,  3- группа, 4 - возраст,  5- изучаемый предмет)
>- Записать полученную строку в файл на сервере

### Основные файлы
1. [view.html](https://github.com/Egorrss/OOP/blob/main/LR3_Json_MVC/src/main/webapp/view.html) - страница-интерфейс, с помощью которого пользователь взаимодействует с программой.

2. [Model_library.java](https://github.com/Egorrss/OOP/blob/main/LR3_Json_MVC/src/main/java/com/example/Model_library.java) - модель библиотеки в котром реализованы методы добавления объекта в файл и извлечения всех объектов из файла.

3. [Book.java](https://github.com/Egorrss/OOP/blob/main/LR3_Json_MVC/src/main/java/com/example/Book.java) - объект Книга, который кладется в библиотеку и посылается в виде Json.

4. [ControllerServlet.java](https://github.com/Egorrss/OOP/blob/main/LR3_Json_MVC/src/main/java/com/example/ControllerServlet.java) - сервлет-контроллер. 
Через view.html пользователь отправляет информацию на сервлет-контроллер. 
Там информация принимается, обрабатывается, записывается в модель. 
Из модели данные отправляются в ответ.

view.html:
![image](https://github.com/Egorrss/OOP/assets/129698533/fe5aa567-ba23-41c5-b137-7a66802dfe5b)

library.txt:

![image](https://github.com/Egorrss/OOP/assets/129698533/cc42aeeb-11fb-48d6-ae51-3f3039c5c959)

