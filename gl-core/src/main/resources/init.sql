CREATE TABLE users
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

INSERT INTO users (name, email)
VALUES ('John Doe', 'john.doe@example.com'),
       ('Jane Smith', 'jane.smith@example.com');