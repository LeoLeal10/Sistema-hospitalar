CREATE SCHEMA dbsitemamedico;

CREATE TABLE dbsitemamedico.tbconvenio (
id INTEGER NOT NULL AUTO_INCREMENT,
nome VARCHAR(40) NOT null,
primary KEY(id)
);

CREATE TABLE dbsitemamedico.tbpaciente (
id INTEGER NOT NULL auto_increment PRIMARY KEY,
nome VARCHAR(40) NOT NULL,
dataNasc DATE NOT NULL,
cpf VARCHAR (14) NOT NULL,
idConvenio INTEGER NOT NULL,
cep VARCHAR(9) NOT NULL,
endereco VARCHAR(70) NOT NULL,
enderecoNum INTEGER NOT NULL,
telefone VARCHAR(12) NOT NULL,
FOREIGN KEY (idConvenio) REFERENCES dbsitemamedico.tbconvenio(id)
);


INSERT INTO dbsitemamedico.tbconvenio (nome) VALUES ('Intermedica');
INSERT INTO dbsitemamedico.tbconvenio (nome) VALUES ('Salvalus');
INSERT INTO dbsitemamedico.tbconvenio (nome) VALUES ('Amil');
INSERT INTO dbsitemamedico.tbconvenio (nome) VALUES ('Allianz');
INSERT INTO dbsitemamedico.tbconvenio (nome) VALUES ('Biosaude');
INSERT INTO dbsitemamedico.tbconvenio (nome) VALUES ('Prevent Senior');

INSERT INTO dbsitemamedico.tbpaciente (id, nome, dataNasc, cpf, idConvenio, cep, endereco, enderecoNum, telefone) VALUES(1, 'Pedro Augusto', '1985-07-22', '123.456.789-00', 1, '12331-122', 'Rua teste', 123, '4008-8922');
INSERT INTO dbsitemamedico.tbpaciente (id, nome, dataNasc, cpf, idConvenio, cep, endereco, enderecoNum, telefone) VALUES(2, 'Fernanda Goncalvel', '1985-11-22', '123.456.789-00', 5, '12331-122', 'Rua teste2', 333, '1223-5995');
