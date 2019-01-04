DELETE FROM votes;
DELETE FROM daily_menu;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', '{noop}password'),
('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name) VALUES
  ('Ресторан 1'), -- 100002
  ('Ресторан 2'), -- 100003
  ('Ресторан 3'), -- 100004
  ('Ресторан 4'), -- 100005
  ('Ресторан 5'); -- 100006


INSERT INTO dishes (name, price) VALUES
  ('Салат Оливье', 50.0), -- 100007
  ('Салат Столичный', 40.0),
  ('Салат Цезарь', 90.0),
  ('Стейк', 150.0),
  ('Шашлык', 120.0),
  ('Салат Шефский', 70.0),  -- 100012
  ('Салат Греческий особенный', 80.0),
  ('Стейк от шефа', 140.0),
  ('Шашлык шея', 130.0),
  ('Запеченные овощи', 110.0),
  ('Пицца', 125.0),
  ('Картошка Фри', 40.0),
  ('Картошка запеченная', 45.0),
  ('Стейк с картошкой', 170.0),   -- 100020
  ('Шашлык телятина', 135.0),
  ('Запеченные овощи в тандыре', 70.0),
  ('Курица Гриль', 105.0),
  ('Сэндвич', 50.0),  --100024
  ('Суп Харчо', 60.0),
  ('Салат Шефский обычный', 65.0),
  ('Салат Греческий', 75.0), -- 100027
  ('Красный борщ', 50.0), --
  ('Устрицы', 140.0), --
  ('Кофе американо', 25.0), --
  ('Кофе эспрессо', 30.0), --
  ('Чай черный', 20.0), --
  ('Чай зеленый', 20.0), -- 100033
  ('Пельмени', 80.0), --
  ('Вареники', 60.0), --
  ('Рагу', 75.0); -- 100036


INSERT INTO daily_menu (date, dish_id, rest_id) VALUES
('2018-11-21', 100007, 100002),
('2018-11-21', 100008, 100002),
('2018-11-21', 100009, 100003),
('2018-11-21', 100010, 100003),
('2018-11-21', 100011, 100004),
('2018-11-21', 100012, 100004),
('2018-11-22', 100013, 100002),
('2018-11-22', 100014, 100002),
('2018-11-22', 100015, 100003),
('2018-11-22', 100016, 100003),
('2018-11-22', 100017, 100004),
('2018-11-22', 100018, 100004),
('2018-11-23', 100019, 100002),
('2018-11-23', 100020, 100002),
('2018-11-23', 100021, 100003),
('2018-11-23', 100022, 100003),
('2018-11-23', 100023, 100004),
('2018-11-23', 100024, 100004);



INSERT INTO VOTES (DATE, USER_ID, REST_ID, DATE_TIME) VALUES
  ('2018-11-11 00:00:00',  100000, 100004, CURRENT_TIMESTAMP),
  ('2018-11-11 00:00:00',  100001, 100003, CURRENT_TIMESTAMP),
  ('2018-11-12 00:00:00',  100000, 100005, CURRENT_TIMESTAMP),
  ('2018-11-12 00:00:00',  100001, 100004, CURRENT_TIMESTAMP),
  ('2018-11-13 00:00:00',  100000, 100003, CURRENT_TIMESTAMP),
  ('2018-11-13 00:00:00',  100001, 100003, CURRENT_TIMESTAMP),
  ('2018-11-14 00:00:00',  100000, 100002, CURRENT_TIMESTAMP),
  ('2018-11-14 00:00:00',  100001, 100005, CURRENT_TIMESTAMP);

select GenerateDailyDishes((current_date-1), (current_date + 4));

--select GenerateDailyDishes(to_date('01-01-2019','DD-MM-YYYY'), to_date('01-01-2019','DD-MM-YYYY'));

--select GenerateDailyDishes('11.11.2018','15.11.2018');
--select GenerateDailyDishes('01.01.2019','01.01.2019');


/*
INSERT INTO daily_menu (date, dish_id, rest_id) VALUES
  ('2018-05-21', 100006, 100002),
  ('2018-05-21', 100007, 100002),
  ('2018-05-21', 100011, 100002),
  ('2018-05-21', 100012, 100003),
  ('2018-05-21', 100019, 100003),
  ('2018-05-21', 100020, 100003),
  ('2018-05-21', 100023, 100004),
  ('2018-05-21', 100024, 100004),
  ('2018-05-21', 100025, 100004),
  ('2018-05-22', 100008, 100002),
  ('2018-05-22', 100009, 100002),
  ('2018-05-22', 100015, 100002),
  ('2018-05-22', 100016, 100003),
  ('2018-05-22', 100021, 100003),
  ('2018-05-22', 100022, 100003),
  ('2018-05-22', 100025, 100004),
  ('2018-05-22', 100026, 100004);


INSERT INTO daily_menu (date, dish_id) VALUES
  ('2018-05-21', 100006),
  ('2018-05-21', 100007),
  ('2018-05-21', 100011),
  ('2018-05-21', 100012),
  ('2018-05-21', 100019),
  ('2018-05-21', 100020),
  ('2018-05-21', 100023),
  ('2018-05-21', 100024),
  ('2018-05-21', 100025),
  ('2018-05-22', 100008),
  ('2018-05-22', 100009),
  ('2018-05-22', 100015),
  ('2018-05-22', 100016),
  ('2018-05-22', 100021),
  ('2018-05-22', 100022),
  ('2018-05-22', 100025),
  ('2018-05-22', 100026);
*/
