DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS daily_menu;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  address          VARCHAR
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);


CREATE TABLE dishes
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  price            DECIMAL                 NOT NULL
  --rest_id          INTEGER,
  --FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
--CREATE UNIQUE INDEX dishes_unique_name_rest_idx ON dishes (rest_id, name);
CREATE UNIQUE INDEX dishes_unique_name_idx ON dishes (name);


CREATE TABLE daily_menu (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date        DATE    NOT NULL,
  rest_id     INTEGER NOT NULL,
  dish_id     INTEGER NOT NULL,
  FOREIGN KEY (dish_id) REFERENCES dishes (id)      ON DELETE CASCADE,
  FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dailymenu_unique_date_rest_dish_idx ON daily_menu (date, rest_id, dish_id);

CREATE TABLE votes (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id     INTEGER   NOT NULL,
  rest_id     INTEGER   NOT NULL,
  date        DATE      NOT NULL,
  date_time   TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)       ON DELETE CASCADE,
  FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_user_date_idx ON votes (user_id, date);


/*

--*************---
create function generatedailydishes(IN fromdate date, IN todate date)
returns void
language plpgsql
as $$
DECLARE
  CURDATE DATE;
  CURREST INTEGER;

  RESTS INTEGER ARRAY;

  DISH INTEGER;
  DISHES INTEGER ARRAY;

BEGIN
  RESTS := ARRAY(SELECT ID FROM RESTAURANTS);

  FOR D IN 0..(TODATE-FROMDATE) LOOP
    CURDATE := FROMDATE + D;
    FOR R IN 1..(ARRAY_LENGTH(RESTS, 1)) LOOP
      CURREST := RESTS[R];
      FOR I IN 0..4 LOOP
        DISHES := ARRAY(
		  SELECT DISHES.ID FROM DISHES
		  WHERE NOT DISHES.ID IN
			(SELECT DISH_ID
			 FROM DAILY_MENU WHERE DAILY_MENU.DATE = CURDATE AND REST_ID = CURREST)
			);

        DISH := DISHES[FLOOR(RANDOM() * ARRAY_LENGTH(DISHES, 1)+1)];

        INSERT INTO DAILY_MENU(ID, DATE, REST_ID, DISH_ID)
          VALUES(NEXTVAL('GLOBAL_SEQ'), CURDATE, CURREST, DISH);
      END LOOP;
    END LOOP;
  END LOOP;

END;
$$;


--***********

create function generatedailymenu(fromdatestr character varying, todatestr character varying) returns integer
language plpgsql
as
$$
DECLARE
  fromdate date;
  todate date;
BEGIN
  fromdate := to_date(fromdatestr,'DD-MM-YYYY');
  todate := to_date(todatestr,'DD-MM-YYYY');
  PERFORM generatedailydishes(fromdate, todate);

  RETURN 1;
END;
$$;
*/
