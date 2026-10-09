USE kennelDB;

CREATE TABLE clients (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  date_naissance DATE NOT NULL,
  pseudonyme VARCHAR(50)
);

CREATE TABLE adresses (
  id INT AUTO_INCREMENT PRIMARY KEY,
  numero VARCHAR(20) NOT NULL,
  rue VARCHAR(200) NOT NULL,
  code_postal VARCHAR(10) NOT NULL,
  commune VARCHAR(100) NOT NULL
);

CREATE TABLE client_adresse (
  client_id INT NOT NULL,
  adresse_id INT NOT NULL,
  PRIMARY KEY (client_id, adresse_id),
  FOREIGN KEY (client_id) REFERENCES clients (id),
  FOREIGN KEY (adresse_id) REFERENCES adresses (id)
);

CREATE TABLE chiens (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  date_naissance DATE NOT NULL,
  race VARCHAR(100) NOT NULL,
  sterilise BOOLEAN NOT NULL DEFAULT FALSE,
  client_id INT,
  FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE chats (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  date_naissance DATE NOT NULL,
  race VARCHAR(100) NOT NULL,
  sterilise BOOLEAN NOT NULL DEFAULT FALSE,
  client_id INT,
  FOREIGN KEY (client_id) REFERENCES clients (id)
);

INSERT INTO clients (nom, prenom, date_naissance, pseudonyme) VALUES
  ('Martin', 'Claire', '1990-04-12', 'ClaireM'),
  ('Dupont', 'Luc', '1985-11-03', 'LucD');

INSERT INTO adresses (numero, rue, code_postal, commune) VALUES
  ('12', 'Rue des Lilas', '69001', 'Lyon'),
  ('5', 'Avenue Victor Hugo', '75016', 'Paris');

INSERT INTO client_adresse (client_id, adresse_id) VALUES
  (1, 1),
  (2, 2);

INSERT INTO chiens (nom, date_naissance, race, sterilise, client_id) VALUES
  ('Rex', '2020-06-01', 'Berger allemand', TRUE, 1),
  ('Nova', '2022-01-15', 'Border collie', FALSE, 2);

INSERT INTO chats (nom, date_naissance, race, sterilise, client_id) VALUES
  ('Mistigri', '2019-09-20', 'Européen', TRUE, 1),
  ('Oscar', '2021-03-08', 'Maine coon', FALSE, 2);
