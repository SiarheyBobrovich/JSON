Использование библиотеки jackson, GET, POST, PUT, DELETE, слой контроллеров, сервисов, дао.

1. Создать dto Группа
	1. Номер
	2. Список студентов
2. Взять dto Студент из работы по JSON
3. Создать DDL для ранее созданных сущностей. Сохранить в файла ddl.sql
4. Написать CRUD контроллер для сущности Студент.
4. Написать CRUD контроллер для сущности Группа.
5. Написать контроллер принимающий список студентов на добавление и удаление из группы.


-- Тестовые данные

INSERT INTO
    courses.groups (name)
    VALUES
        ('MK-JD2-90-22'),
        ('MK-JD2-90-23');

INSERT into
    courses.students
        (name, age, score, olimpic_gamer)
    VALUES
        ('Siarhey', 34, 2.2, true),
        ('Pavel', 21, 5.34, true),
        ('Mihael', 25, 9.9, false),
        ('Valera', 120, null, true);


	INSERT into
    courses.students_in_groupe
        (groupe_id, student_id)
    VALUES
		(1, 1),
		(1, 3),
        (3, 2),
		(3, 4);