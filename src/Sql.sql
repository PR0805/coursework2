DROP DATABASE cinemadb;
CREATE DATABASE cinemadb;

use cinemadb;

CREATE TABLE Films(
	movie_title VARCHAR(50) NOT NULL,
	star_actor VARCHAR(50) NOT NULL,
        director VARCHAR(50) NOT NULL,
        genre VARCHAR(50) NOT NULL,
        age_classification VARCHAR(10),
        PRIMARY KEY (movie_title)
);


CREATE TABLE Showings(
	showing_id INTEGER NOT NULL,
	movie_title VARCHAR(50) NOT NULL,
	date_time DATETIME NOT NULL,
        room_number INTEGER NOT NULL,
	PRIMARY KEY(showing_id),
        FOREIGN KEY(movie_title) REFERENCES Films(movie_title)
);



INSERT INTO Films 
VALUES ('Aquaman', 'Jason Momoa', 'James Wan Peldi', 'Fantasy', 'PG-13'),
('Mortal Engines', 'Hera Hilmar', 'Christian Rivers', 'Action', 'PG-13'),
('Bumblebee', 'Hailee Steinfield', 'Travis Knight', 'Sci-Fic', 'PG-13');

INSERT INTO Showings 
VALUES ('1', 'Aquaman', '19/03/01 10:00', '10'),
('2', 'Mortal Engines', '19/03/01 10:00', '11'),
('3', 'Bumblebee', '19/03/01 10:00', '12');





