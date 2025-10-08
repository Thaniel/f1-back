/*
	Users
*/
INSERT INTO users (email, user_name, first_name, last_name, birth_date, is_admin, country) 
VALUES 	('example@email.com', 'exampleUser', 'John', 'Doe', '1990-01-01', false, 'USA'),
 		('jane.doe@email.com', 'janeDoe', 'Jane', 'Doe', '1992-05-15', true, 'Canada'),
 		('michael.smith@email.com', 'mikeSmith', 'Michael', 'Smith', '1985-11-23', false, 'UK'),
 		('linda.brown@email.com', 'lindaBrown', 'Linda', 'Brown', '2000-07-08', true, 'Australia');


/*
	Races
*/
INSERT INTO races (
    grand_prix_name, first_appearance, race_distance, circuit_name, description, country, image, record, laps, circuit_length, race_date, qualifying_date, first_practice_date, second_practice_date, third_practice_date
) VALUES
('Monaco Grand Prix', 1929, 260.52, 'Circuit de Monaco', 'The Monaco Grand Prix is one of the most prestigious automobile races in the world.', 'Monaco', 'https://example.com/images/monaco.jpg', '1:10.166 by Lewis Hamilton (2019)', 78, 3.337, '2025-05-25', '2025-05-24', '2025-05-23', '2025-05-23', '2025-05-24'),
('Japanese Grand Prix', 1976, 307.471, 'Suzuka International Racing Course', 'The Japanese Grand Prix at Suzuka is known for its challenging figure-eight layout.', 'Japan', 'https://example.com/images/japan.jpg', '1:27.064 by Lewis Hamilton (2019)', 53, 5.807, '2025-10-12', '2025-10-11', '2025-10-10', '2025-10-10', '2025-10-11');


/*
	Teams
*/
INSERT INTO teams (name, full_name, color_code, description, team_principal, car_image, logo_image, titles, points) 
VALUES 
('Red Bull', 'Oracle Red Bull Racing', '#0600ef', 'Equipo campeón de Fórmula 1', 'Christian Horner', 'redbull_car.png', 'redbull_logo.png', 5, 6800),
('Mercedes', 'Mercedes-AMG Petronas F1 Team', '#00d2be', 'Dominante durante la era híbrida', 'Toto Wolff', 'mercedes_car.png', 'mercedes_logo.png', 8, 7000),
('Ferrari', 'Scuderia Ferrari', '#ef1a2d', 'El equipo más legendario de la F1', 'Fred Vasseur', 'ferrari_car.png', 'ferrari_logo.png', 16, 9100);


/*
	Drivers
*/
INSERT INTO drivers (first_name, last_name, country, birth_date, points, titles, image, team_id) 
VALUES 
('Max', 'Verstappen', 'Netherlands', '1997-09-30', 440, 3, 'max_verstappen.png', 1),
('Sergio', 'Perez', 'Mexico', '1990-01-26', 280, 0, 'sergio_perez.png', 1),
('Lewis', 'Hamilton', 'United Kingdom', '1985-01-07', 432, 7, 'lewis_hamilton.png', 2),
('George', 'Russell', 'United Kingdom', '1998-02-15', 140, 0, 'george_russell.png', 2),
('Charles', 'Leclerc', 'Monaco', '1997-10-16', 160, 0, 'charles_leclerc.png', 3),
('Carlos', 'Sainz', 'Spain', '1994-09-01', 140, 0, 'carlos_sainz.png', 3);

 	
/*
    Notices
*/
INSERT INTO notices (date, summary, text, title, image, id_user)
VALUES 
('2025-01-08', 'This is a brief summary of the first entry.', 'This is the full text content of the first entry.', 'First Title', 'first_image_url.jpg', 1),
('2025-01-07', 'Second summary of another entry.', 'Detailed text for the second entry goes here.', 'Second Title', 'second_image_url.jpg', 2),
('2025-02-06', 'A different summary for the third entry.', 'Comprehensive text content for the third entry.', 'Third Title', 'third_image_url.jpg', 3),
('2024-01-06', 'Sumary......', 'Fourth entry.', 'Fourth Title', 'fourth_image_url.jpg', 1);

/*
    Comments for Notices
*/
INSERT INTO comments (date, text, id_user, id_notice, id_topic, comment_type)
VALUES 
('2025-01-08', 'Comment text 1... N', 1, 2, NULL, 'NOTICE'),
('2025-01-07', 'Comment text 2... N', 1, 2, NULL, 'NOTICE'),
('2025-01-06', 'Comment text 3... N', 2, 1, NULL, 'NOTICE');


/*
    Topics
*/
INSERT INTO topics (date, title, id_user)
VALUES 
('2025-02-01', 'First topic title', 1),
('2025-03-02', 'Title for second topic', 2),
('2025-04-03', 'Third title for the third entry.', 1);

/*
    Comments for Topics
*/
INSERT INTO comments (date, text, id_user, id_notice, id_topic, comment_type)
VALUES 
('2025-05-18', 'Comment text 1... T', 1, NULL, 1, 'TOPIC'),
('2025-05-17', 'Comment text 2... T', 1, NULL, 1, 'TOPIC'),
('2025-05-16', 'Comment text 3... T', 2, NULL, 2, 'TOPIC');




