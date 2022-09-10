
-- moja baza danych -- 
USE cassetterentaldb;
CREATE USER rental_user IDENTIFIED BY 'mypassword123';
GRANT ALL privileges ON cassetterentaldb.* to rental_user;

CREATE TABLE categories (
  id            INT AUTO_INCREMENT,
  name          VARCHAR(32) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE directors (
    id          INT AUTO_INCREMENT,
    first_name  VARCHAR(32) NOT NULL,
    last_name   VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
  id           INT AUTO_INCREMENT,
  nick_name    VARCHAR(32) NOT NULL,
  privileges   INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE rents (
	id 			INT AUTO_INCREMENT,
	movie     	INT NOT NULL,
	user		INT NOT NULL,
	rent_date 	DATE NOT NULL,
	return_date DATE,
	PRIMARY KEY (ID),
	FOREIGN KEY (movie) REFERENCES movies(id),
    FOREIGN KEY (user) REFERENCES users(id)
);

CREATE TABLE movies (
  id            INT AUTO_INCREMENT,
  title         VARCHAR(128) NOT NULL,
  category      INT NOT NULL,
  director      INT NOT NULL,
  release_date  DATE NOT NULL,
  amount		INT,
  PRIMARY KEY (id),
  FOREIGN KEY (category) REFERENCES categories(id),
  FOREIGN KEY (director) REFERENCES directors(id)
);

INSERT INTO users VALUES
    (1, 'User1', 1),
    (2, 'User2', 1),
    (3, 'Administrator', 2);
    
INSERT INTO directors VALUES
    (1, 'James', 'Cameron'),
    (2, 'Quentin', 'Tarantino'),
    (3, 'Francis', 'Coppola'),
    (4, 'Christopher', 'Nolan'),
    (5, 'David', 'Lynch');
    
INSERT INTO categories VALUES
    (1, 'Komedia'),
    (2, 'Sci-Fi'),
    (3, 'Thriller'),
    (4, 'Kryminał'),
    (5, 'Horror'),
    (6, 'Przygodowy'),
    (7, 'Dramat'),
    (8, 'Akcja'),
    (9, 'Western');
    
INSERT INTO movies VALUES
    (1, 'Avatar', 2, 1, '2009-12-25', 1),
    (2, 'Titanic', 7, 1, '1998-02-13', 1),
    (3, 'Alien', 5, 1, '1990-09-12', 1),
    (4, 'Once upon a time in Hollywood', 7, 2, '2019-08-16', 1),
    (5, 'Pulp Fiction', 8, 2, '1995-05-19', 1),
    (6, 'The Hateful Eight', 9, 2, '2016-01-15', 1),
    (7, 'The Godfather', 7, 3, '1972-12-31', 1),
    (8, 'The Godfather: Part II', 7, 3, '1972-12-31', 1),
    (9, 'Dracula', 5, 3, '1992-12-31', 1),
	(10, 'Inception', 2, 4, '2010-07-30', 1),
	(11, 'Interstellar', 2, 4, '2014-11-07', 1),
	(12, 'Dunkirk', 7, 4, '2017-07-21', 1),
    (13, 'Lost Highway', 4, 5, '1997-04-25', 1),
    (14, 'Twin Peaks', 3, 5, '1990-04-08', 1),
	(15, 'The Straight Story', 7, 5, '2000-01-07', 1);

