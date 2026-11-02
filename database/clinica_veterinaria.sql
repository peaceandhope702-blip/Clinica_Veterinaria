DROP DATABASE IF EXISTS clinica_veterinaria;
CREATE DATABASE clinica_veterinaria CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE clinica_veterinaria;

CREATE TABLE usuarios (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(120) NOT NULL,
 login VARCHAR(60) NOT NULL UNIQUE,
 senha VARCHAR(120) NOT NULL,
 perfil ENUM('GERENTE','ATENDENTE','VETERINARIO') NOT NULL,
 ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE tutores (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(120) NOT NULL,
 cpf VARCHAR(20) NOT NULL UNIQUE,
 telefone VARCHAR(30),
 endereco VARCHAR(200)
);
CREATE TABLE animais (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(100) NOT NULL,
 tipo VARCHAR(60) NOT NULL,
 raca VARCHAR(80),
 idade INT NOT NULL,
 tutor_id INT NOT NULL,
 CONSTRAINT fk_animal_tutor FOREIGN KEY(tutor_id) REFERENCES tutores(id)
);
CREATE TABLE veterinarios (
 id INT AUTO_INCREMENT PRIMARY KEY,
 usuario_id INT NOT NULL UNIQUE,
 crmv VARCHAR(40) NOT NULL UNIQUE,
 CONSTRAINT fk_vet_usuario FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);
CREATE TABLE consultas (
 id INT AUTO_INCREMENT PRIMARY KEY,
 animal_id INT NOT NULL,
 veterinario_id INT NOT NULL,
 data_hora DATETIME NOT NULL,
 tratamento TEXT,
 observacoes TEXT,
 status ENUM('AGENDADA','REALIZADA','CANCELADA') NOT NULL DEFAULT 'AGENDADA',
 CONSTRAINT fk_consulta_animal FOREIGN KEY(animal_id) REFERENCES animais(id),
 CONSTRAINT fk_consulta_vet FOREIGN KEY(veterinario_id) REFERENCES veterinarios(id)
);
CREATE TABLE pagamentos (
 id INT AUTO_INCREMENT PRIMARY KEY,
 consulta_id INT NOT NULL,
 valor DECIMAL(10,2) NOT NULL,
 efetuado BOOLEAN NOT NULL DEFAULT FALSE,
 data_pagamento DATETIME NULL,
 CONSTRAINT fk_pagamento_consulta FOREIGN KEY(consulta_id) REFERENCES consultas(id)
);

INSERT INTO usuarios(nome,login,senha,perfil) VALUES
('Administrador VetCare','admin','admin123','GERENTE'),
('Atendente VetCare','atendente','1234','ATENDENTE'),
('Dr. Carlos','carlos','1234','VETERINARIO');
INSERT INTO veterinarios(usuario_id,crmv) VALUES (3,'CRMV-RS 12345');
INSERT INTO tutores(nome,cpf,telefone,endereco) VALUES
('Maria Silva','123.456.789-00','(51) 99999-1111','Porto Alegre - RS'),
('João Souza','987.654.321-00','(51) 98888-2222','Canoas - RS');
INSERT INTO animais(nome,tipo,raca,idade,tutor_id) VALUES
('Bob','Cachorro','Labrador',4,1),
('Luna','Gato','Siamês',2,2);
INSERT INTO consultas(animal_id,veterinario_id,data_hora,tratamento,observacoes,status) VALUES
(1,1,DATE_ADD(NOW(), INTERVAL 1 DAY),'','Consulta de rotina','AGENDADA'),
(2,1,DATE_SUB(NOW(), INTERVAL 3 DAY),'Medicação prescrita','Retorno em 15 dias','REALIZADA');
INSERT INTO pagamentos(consulta_id,valor,efetuado,data_pagamento) VALUES
(1,150.00,FALSE,NULL),
(2,180.00,TRUE,NOW());
