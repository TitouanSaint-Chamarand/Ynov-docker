CREATE TABLE IF NOT EXISTS etudiants (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL
);

INSERT INTO etudiants (nom, email) VALUES
  ('Dupont', 'dupont@example.com'),
  ('Martin', 'martin@example.com'),
  ('Bernard', 'bernard@example.com');
