INSERT INTO servicers VALUES (1, 'James', 'Carter');
INSERT INTO servicers VALUES (2, 'Helen', 'Leary');
INSERT INTO servicers VALUES (3, 'Linda', 'Douglas');
INSERT INTO servicers VALUES (4, 'Rafael', 'Ortega');
INSERT INTO servicers VALUES (5, 'Henry', 'Stevens');
INSERT INTO servicers VALUES (6, 'Sharon', 'Jenkins');
INSERT INTO servicers VALUES (7, 'Jaxon', 'Kingston');
INSERT INTO servicers VALUES (8, 'Kenny', 'Rodriguez');
INSERT INTO servicers VALUES (9, 'John', 'Stone');

INSERT INTO specialties VALUES (1, 'Full-stack Software Developer');
INSERT INTO specialties VALUES (2, 'IT Project Manager');
INSERT INTO specialties VALUES (3, 'Cyber Security Expert');
INSERT INTO specialties VALUES (4, 'Network Specialist');
INSERT INTO specialties VALUES (5, 'UX/UI Designer');
INSERT INTO specialties VALUES (6, 'QA Expert');
INSERT INTO specialties VALUES (7, 'Cloud Engineer');
INSERT INTO specialties VALUES (8, 'Big Data Expert');
INSERT INTO specialties VALUES (9, 'Specialist in machine learning and artificial intelligence');


INSERT INTO servicer_specialties VALUES (1, 1);
INSERT INTO servicer_specialties VALUES (2, 2);
INSERT INTO servicer_specialties VALUES (3, 3);
INSERT INTO servicer_specialties VALUES (4, 4);
INSERT INTO servicer_specialties VALUES (5, 5);
INSERT INTO servicer_specialties VALUES (6, 6);
INSERT INTO servicer_specialties VALUES (7, 7);
INSERT INTO servicer_specialties VALUES (8, 8);
INSERT INTO servicer_specialties VALUES (9, 9);

INSERT INTO types VALUES (1, 'supercomputer');
INSERT INTO types VALUES (2, 'mainframe');
INSERT INTO types VALUES (3, 'minicomputer');
INSERT INTO types VALUES (4, 'microcomputer');
INSERT INTO types VALUES (5, 'laptop');
INSERT INTO types VALUES (6, 'smartphone');

INSERT INTO clients VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT INTO clients VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT INTO clients VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT INTO clients VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT INTO clients VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT INTO clients VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT INTO clients VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT INTO clients VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT INTO clients VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT INTO clients VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT INTO devices VALUES (1, 'HP 285 G3 MT 3VA15EA', '2010-09-07', 1, 1);
INSERT INTO devices VALUES (2, 'Lenovo Legion', '2012-08-06', 6, 2);
INSERT INTO devices VALUES (3, 'Asus GL12CXPL', '2011-04-17', 2, 3);
INSERT INTO devices VALUES (4, 'OPTIMUSESport', '2010-03-07', 2, 3);
INSERT INTO devices VALUES (5, 'G4M3R 560', '2010-11-30', 3, 4);
INSERT INTO devices VALUES (6, 'Dell Inspiron', '2010-01-20', 4, 5);
INSERT INTO devices VALUES (7, 'Lenovo GL2', '2012-09-04', 1, 6);
INSERT INTO devices VALUES (8, 'HP Omen 88', '2012-09-04', 1, 6);
INSERT INTO devices VALUES (9, 'Dell i360', '2011-08-06', 5, 7);
INSERT INTO devices VALUES (10, 'G4M3R 600', '2007-02-24', 2, 8);
INSERT INTO devices VALUES (11, 'JP Pavilion', '2010-03-09', 5, 9);
INSERT INTO devices VALUES (12, 'JP Omen 880', '2010-06-24', 2, 10);
INSERT INTO devices VALUES (13, 'HO 300', '2012-06-08', 1, 10);

INSERT INTO visits VALUES (1, 7, '2013-01-01', 'PSU Failure');
INSERT INTO visits VALUES (2, 8, '2013-01-02', 'Components overheating');
INSERT INTO visits VALUES (3, 8, '2013-01-03', 'Frequent system crashes');
INSERT INTO visits VALUES (4, 7, '2013-01-04', 'The computer wont turn on');