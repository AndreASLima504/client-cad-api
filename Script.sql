DROP TABLE cliente;
CREATE DATABASE AGENDA;
USE AGENDA;
# Tabelas de referência
CREATE TABLE cliente(
	id INT PRIMARY KEY auto_increment,
	nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE,
    endereco VARCHAR(100)
    );
CREATE TABLE contato(
	id INT PRIMARY KEY,
    cliente_id INT,
    tipo_contato VARCHAR(50),
    valor VARCHAR(100),
    observacao VARCHAR(255),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

# Execute daqui para a frente para inserir novos dados
INSERT INTO cliente(nome, cpf, data_nascimento, endereco)
VALUES ('Fulano', '123.456.789-01', '2000-01-01', 'Rua dos fundos'),
('Ciclano', '223.456.789-01', '1999-01-04', 'Rua da frente'),
('Beltrano', '323.456.789-01', '1987-05-30', 'Rua do lado'),
('Trajano', '423.456.789-01', '1995-08-15', 'Avenida Central'),
('Roberto', '523.456.789-01', '1982-12-03', 'Travessa das Palmeiras'),
('Fernanda', '623.456.789-01', '1990-07-21', 'Rua das Flores'),
('Carlos', '723.456.789-01', '1985-03-10', 'Alameda Azul'),
('Juliana', '823.456.789-01', '1993-06-25', 'Praça das Rosas'),
('Eduardo', '923.456.789-01', '2001-09-14', 'Rua do Mercado');

INSERT INTO contato (id, cliente_id, tipo_contato, valor, observacao)  
VALUES  (1, 1, 'Telefone', '(11) 98765-4321', 'Contato principal'),  
(2, 1, 'Email', 'fulano@email.com', 'Usar para comunicações oficiais'),  
(3, 2, 'Telefone', '(21) 99999-8888', 'Prefere contato à tarde'),  
(4, 3, 'Email', 'beltrano@email.com', 'Responder em horário comercial'),  
(5, 3, 'Telefone', '(31) 98888-7777', 'WhatsApp disponível'),  
(6, 4, 'Email', 'trajano@email.com', 'Contato profissional'),  
(7, 4, 'Telefone', '(41) 97777-6666', 'Evitar horário de almoço'),  
(8, 5, 'Telefone', '(51) 96666-5555', 'Ligação após as 18h'),  
(9, 5, 'Email', 'roberto@email.com', 'Verifica e-mails somente à noite'),  
(10, 6, 'Telefone', '(61) 95555-4444', 'WhatsApp disponível'),  
(11, 7, 'Email', 'carlos@email.com', 'Contato comercial'),  
(12, 8, 'Telefone', '(71) 94444-3333', 'Prefere chamadas rápidas'),  
(13, 9, 'Email', 'eduardo@email.com', 'Responder em até 24h');  

select * from cliente;
select * from contato; 
