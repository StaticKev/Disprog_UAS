USE isadp;

TRUNCATE TABLE notifications;
TRUNCATE TABLE favorite;
TRUNCATE TABLE purchase;
DELETE FROM users;
ALTER TABLE users AUTO_INCREMENT = 1;
DELETE FROM tickets;
ALTER TABLE tickets AUTO_INCREMENT = 1;

INSERT INTO users (username, `password`, email) VALUES
('u1', 'p1', 'e1@email.com'),
('lucas99', 'abc', 'lucas99@example.com'),
('sarah_m', 'qwe', 'sarah.m@example.com'),
('jordanX', '1234', 'jordanx@domain.com'),
('emilyRose', 'xy', 'emily.rose@service.org'),
('nathan77', 'pass', 'nathan77@webmail.com'),
('alex_theo', 'z', 'alex.theo@mailbox.net'),
('bella23', 'pw12', 'bella23@inbox.com'),
('matthewK', 'hi', 'matt.k@example.com'),
('daniellaV', '123', 'daniella.v@hostmail.com'),
('kevin_Z', '42', 'kevin.z@domain.org');

INSERT INTO tickets (title, location, price, event_date, category, sale_start, sale_ends, flash_sale_price, flash_sale_on, flash_sale_ends) VALUES
('UX Live Session', 'Makassar Stadium', 359988, '2025-05-10', 'WORKSHOP', '2025-04-30 00:00:00', '2025-05-08 00:00:00', NULL, NULL, NULL),
('Coding Bootcamp Live', 'Bandung Hall', 186849, '2025-03-22', 'MATCH', '2025-03-03 00:00:00', '2025-03-11 00:00:00', NULL, NULL, NULL),
('Pop Culture Fiesta', 'Makassar Stadium', 408221, '2025-03-21', 'WORKSHOP', '2025-03-02 00:00:00', '2025-03-05 00:00:00', 310398.83, '2025-03-03 00:00:00', '2025-03-05 00:00:00'),
('Football Fever', 'Yogyakarta Theater', 125493, '2025-06-21', 'CONCERT', '2025-06-04 00:00:00', '2025-06-12 00:00:00', NULL, NULL, NULL),
('Classical Harmony', 'Makassar Stadium', 215768, '2025-06-07', 'MOVIE', '2025-05-22 00:00:00', '2025-05-26 00:00:00', NULL, NULL, NULL),
('Art of the Match', 'Bali Open Stage', 215215, '2025-07-05', 'CONCERT', '2025-06-20 00:00:00', '2025-06-24 00:00:00', NULL, NULL, NULL),
('Anime Gala 2025', 'Semarang Studio', 493547, '2025-08-14', 'CONCERT', '2025-07-28 00:00:00', '2025-08-01 00:00:00', 299664.34, '2025-07-30 00:00:00', '2025-08-01 00:00:00'),
('Cinematic Workshop', 'Jakarta Convention Center', 79004, '2025-08-16', 'MATCH', '2025-08-03 00:00:00', '2025-08-11 00:00:00', NULL, NULL, NULL),
('Action Movie Fest', 'Makassar Stadium', 443409, '2025-06-27', 'MATCH', '2025-06-11 00:00:00', '2025-06-13 00:00:00', NULL, NULL, NULL),
('Jazz Under the Stars', 'Bali Open Stage', 326236, '2025-07-02', 'MOVIE', '2025-06-17 00:00:00', '2025-06-25 00:00:00', NULL, NULL, NULL),
('Startup Talk 2025', 'Bandung Hall', 138489, '2025-09-04', 'CONCERT', '2025-08-19 00:00:00', '2025-08-24 00:00:00', 87569.91, '2025-08-20 00:00:00', '2025-08-22 00:00:00'),
('Rockin'' Java', 'Makassar Stadium', 433681, '2025-08-18', 'WORKSHOP', '2025-07-30 00:00:00', '2025-08-04 00:00:00', NULL, NULL, NULL),
('Table Tennis Showdown', 'Surabaya Expo', 127191, '2025-08-18', 'MOVIE', '2025-08-01 00:00:00', '2025-08-06 00:00:00', NULL, NULL, NULL),
('The Film Directors Talk', 'Bandung Hall', 142569, '2025-09-09', 'MATCH', '2025-08-23 00:00:00', '2025-08-30 00:00:00', NULL, NULL, NULL),
('The Grand Orchestra', 'Semarang Studio', 445786, '2025-08-19', 'MATCH', '2025-08-01 00:00:00', '2025-08-06 00:00:00', 273733.89, '2025-08-03 00:00:00', '2025-08-05 00:00:00'),
('Mystery Movie Night', 'Yogyakarta Theater', 132194, '2025-07-23', 'CONCERT', '2025-07-01 00:00:00', '2025-07-04 00:00:00', NULL, NULL, NULL),
('Basketball Legends', 'Medan Arena', 367167, '2025-07-29', 'MOVIE', '2025-07-08 00:00:00', '2025-07-13 00:00:00', NULL, NULL, NULL),
('Indie Night Vibes', 'Makassar Stadium', 268241, '2025-08-27', 'WORKSHOP', '2025-08-08 00:00:00', '2025-08-16 00:00:00', NULL, NULL, NULL),
('Design Thinking', 'Yogyakarta Theater', 346166, '2025-08-01', 'WORKSHOP', '2025-07-14 00:00:00', '2025-07-18 00:00:00', NULL, NULL, NULL),
('Movie Premiere: Galaxy Quest', 'Bali Open Stage', 236699, '2025-08-07', 'CONCERT', '2025-07-20 00:00:00', '2025-07-28 00:00:00', NULL, NULL, NULL);

INSERT INTO favorite (users_id, tickets_id) VALUES
(2, 1),
(3, 1),
(5, 2),
(2, 3),
(3, 3),
(1, 4),
(10, 4),
(2, 5),
(10, 5),
(5, 6),
(6, 7),
(8, 7),
(8, 8),
(2, 9),
(10, 9),
(6, 10),
(4, 11),
(9, 11),
(6, 12),
(10, 12),
(7, 13),
(1, 15),
(9, 15),
(7, 16),
(5, 17),
(6, 17),
(8, 17),
(5, 18),
(7, 19),
(4, 20);

INSERT INTO purchase (qty, purchased_on, `status`, users_id, ticket_id) VALUES
(6, '2024-04-28 15:03:37', 'CANCELLED', 8, 6),
(9, '2024-04-29 15:03:37', 'PENDING', 2, 8),
(1, '2024-05-20 15:03:37', 'CONFIRMED', 9, 5),
(2, '2024-04-29 15:03:37', 'CONFIRMED', 9, 1),
(4, '2024-05-04 15:03:37', 'CONFIRMED', 10, 10),
(6, '2024-04-26 15:03:37', 'PENDING', 9, 1),
(2, '2024-05-14 15:03:37', 'CONFIRMED', 2, 10),
(3, '2024-05-06 15:03:37', 'CONFIRMED', 5, 17),
(10, '2024-05-21 15:03:37', 'PENDING', 6, 10),
(7, '2024-05-11 15:03:37', 'CANCELLED', 4, 14),
(5, '2024-05-10 15:03:37', 'CONFIRMED', 1, 14),
(10, '2024-04-30 15:03:37', 'CANCELLED', 9, 13),
(7, '2024-05-04 15:03:37', 'PENDING', 2, 17),
(4, '2024-05-18 15:03:37', 'PENDING', 10, 13),
(6, '2024-05-18 15:03:37', 'PENDING', 6, 2);

INSERT INTO notifications (message, sent_on, `read`, users_id) VALUES
('Your ticket for Jazz Night has been confirmed.', '2024-06-10 14:20:00', 0, 9),
('Don’t miss out on our latest concert event!', '2024-06-05 09:15:00', 0, 9),
('Reminder: Flash sale ends soon!', '2024-06-02 18:45:00', 0, 9),
('You have a pending ticket payment.', '2024-05-29 11:30:00', 0, 9),
('Welcome to our ticketing platform, Daniella!', '2024-05-20 08:00:00', 1, 9);