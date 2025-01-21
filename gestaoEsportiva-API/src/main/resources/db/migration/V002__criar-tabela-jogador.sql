CREATE TABLE Jogador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_nascimento VARCHAR(255) NOT NULL,
    time_id BIGINT,
    FOREIGN KEY (time_id) REFERENCES Time(id)
);