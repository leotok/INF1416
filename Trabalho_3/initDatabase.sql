DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Registro;
DROP TABLE IF EXISTS Mensagem;
DROP TABLE IF EXISTS TanList;

CREATE TABLE User (
  name TEXT NOT NULL,
  email TEXT UNIQUE NOT NULL,
  groupName TEXT NOT NULL,
  salt TEXT NOT NULL,
  passwordDigest TEXT NOT NULL,
  acesso BOOL DEFAULT 1,
  numAcessoErrados INTEGER DEFAULT 0,
  ultimaTentativa DATETIME,
  totalAcessos INTEGER DEFAULT 0,
  totalConsultas INTEGER DEFAULT 0,
  certificado TEXT NOT NULL,
  numChavePrivadaErrada INTEGER DEFAULT 0,
  numTanErrada INTEGER DEFAULT 0
);

CREATE TABLE Registro (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  messageId INTEGER NOT NULL,
  email TEXT,
  filename TEXT,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(messageId) REFERENCES Mensagem(id)
);

CREATE TABLE Mensagem (
  id INTEGER PRIMARY KEY,
  texto TEXT NOT NULL
);

CREATE TABLE TanList (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  email TEXT NOT NULL,
  tan TEXT NOT NULL,
  usada BOOL DEFAULT 0,
   posicao INTEGER NOT NULL,
  -- PRIMARY KEY(id, email),
  FOREIGN KEY(email) REFERENCES User(email)
);


INSERT INTO Mensagem VALUES(1001, 'Sistema iniciado.');
INSERT INTO Mensagem VALUES(1002, 'Sistema encerrado.');
INSERT INTO Mensagem VALUES(2001, 'Autenticação etapa 1 iniciada.');
INSERT INTO Mensagem VALUES(2002, 'Autenticação etapa 1 encerrada.');
INSERT INTO Mensagem VALUES(2003, 'Login name <login_name> identificado com acesso liberado.');
INSERT INTO Mensagem VALUES(2004, 'Login name <login_name> identificado com acesso bloqueado.');
INSERT INTO Mensagem VALUES(2005, 'Login name <login_name> não identificado.');
INSERT INTO Mensagem VALUES(3001, 'Autenticação etapa 2 iniciada para <login_name>.');
INSERT INTO Mensagem VALUES(3002, 'Autenticação etapa 2 encerrada para <login_name>.');
INSERT INTO Mensagem VALUES(3003, 'Senha pessoal verificada positivamente para <login_name>.');
INSERT INTO Mensagem VALUES(3004, 'Senha pessoal verificada negativamente para <login_name>.');
INSERT INTO Mensagem VALUES(3005, 'Primeiro erro da senha pessoal contabilizado para <login_name>.');
INSERT INTO Mensagem VALUES(3006, 'Segundo erro da senha pessoal contabilizado para <login_name>.');
INSERT INTO Mensagem VALUES(3007, 'Terceiro erro da senha pessoal contabilizado para <login_name>.');
INSERT INTO Mensagem VALUES(3008, 'Acesso do usuario <login_name> bloqueado pela autenticação etapa 2.');
INSERT INTO Mensagem VALUES(4001, 'Autenticação etapa 3 iniciada para <login_name>.');
INSERT INTO Mensagem VALUES(4002, 'Autenticação etapa 3 encerrada para <login_name>.');
INSERT INTO Mensagem VALUES(4003, 'Senha de única vez verificada positivamente para <login_name>.');
INSERT INTO Mensagem VALUES(4004, 'Primeiro erro da senha de única vez contabilizado para <login_name>.');
INSERT INTO Mensagem VALUES(4005, 'Segundo erro da senha de única vez contabilizado para <login_name>.');
INSERT INTO Mensagem VALUES(4006, 'Terceiro erro da senha de única vez contabilizado para <login_name>.');
INSERT INTO Mensagem VALUES(4009, 'Acesso do usuario <login_name> bloqueado pela autenticação etapa 3.');
INSERT INTO Mensagem VALUES(5001, 'Tela principal apresentada para <login_name>.');
INSERT INTO Mensagem VALUES(5002, 'Opção 1 do menu principal selecionada por <login_name>.');
INSERT INTO Mensagem VALUES(5003, 'Opção 2 do menu principal selecionada por <login_name>.');
INSERT INTO Mensagem VALUES(5004, 'Opção 3 do menu principal selecionada por <login_name>.');
INSERT INTO Mensagem VALUES(5005, 'Opção 4 do menu principal selecionada por <login_name>.');
INSERT INTO Mensagem VALUES(6001, 'Tela de cadastro apresentada para <login_name>.');
INSERT INTO Mensagem VALUES(6002, 'Botão cadastrar pressionado por <login_name>.');
INSERT INTO Mensagem VALUES(6003, 'Caminho do certificado digital inválido fornecido por <login_name>.');
INSERT INTO Mensagem VALUES(6004, 'Confirmação de dados aceita por <login_name>.');
INSERT INTO Mensagem VALUES(6005, 'Confirmação de dados rejeitada por <login_name>.');
INSERT INTO Mensagem VALUES(6006, 'Botão voltar de cadastro para o menu principal pressionado por <login_name>.');
INSERT INTO Mensagem VALUES(7001, 'Tela de carregamento da chave privada apresentada para <login_name>.');
INSERT INTO Mensagem VALUES(7002, 'Caminho da chave privada inválido fornecido por <login_name>.');
INSERT INTO Mensagem VALUES(7003, 'Frase secreta inválida fornecida por <login_name>.');
INSERT INTO Mensagem VALUES(7004, 'Erro de validação da chave privada com o certificado digital de <login_name>.');
INSERT INTO Mensagem VALUES(7005, 'Chave privada validada com sucesso para <login_name>.');
INSERT INTO Mensagem VALUES(7006, 'Botão voltar de carregamento para o menu principal pressionado por <login_name>.');
INSERT INTO Mensagem VALUES(8001, 'Tela de consulta de arquivos secretos apresentada para <login_name>.');
INSERT INTO Mensagem VALUES(8002, 'Botão voltar de consulta para o menu principal pressionado por <login_name>.');
INSERT INTO Mensagem VALUES(8003, 'Botão Listar de consulta pressionado por <login_name>.');
INSERT INTO Mensagem VALUES(8006, 'Caminho de pasta inválido fornecido por <login_name>.');
INSERT INTO Mensagem VALUES(8007, 'Lista de arquivos apresentada para <login_name>.');
INSERT INTO Mensagem VALUES(8008, 'Arquivo <arq_name> selecionado por <login_name> para decriptação.');
INSERT INTO Mensagem VALUES(8009, 'Arquivo <arq_name> decriptado com sucesso para <login_name>.');
INSERT INTO Mensagem VALUES(8010, 'Arquivo <arq_name> verificado (integridade e autenticidade) com sucesso para <login_name>.');
INSERT INTO Mensagem VALUES(8011, 'Falha na decriptação do arquivo <arq_name> para <login_name>.');
INSERT INTO Mensagem VALUES(8012, 'Falha na verificação do arquivo <arq_name> para <login_name>.');
INSERT INTO Mensagem VALUES(9001, 'Tela de saída apresentada para <login_name>.');
INSERT INTO Mensagem VALUES(9002, 'Botão sair pressionado por <login_name>.');
INSERT INTO Mensagem VALUES(9003, 'Botão voltar de sair para o menu principal pressionado por <login_name>.');

-- -- Tests
-- INSERT INTO User VALUES('Usuario 01', 'user01@inf1416.puc-rio.br', 'usuario', '5740238012', 'e4329c58f2cba5f718e74a53bd97eb44f8d44f83', 
-- 1, 0, NULL, 0, 0,
-- '-----BEGIN CERTIFICATE-----
-- MIID5TCCAs2gAwIBAgIBAjANBgkqhkiG9w0BAQsFADB6MQswCQYDVQQGEwJCUjEM
-- MAoGA1UECAwDUmlvMQwwCgYDVQQHDANSaW8xDDAKBgNVBAoMA1BVQzELMAkGA1UE
-- CwwCREkxEzARBgNVBAMMCkFDIElORjE0MTYxHzAdBgkqhkiG9w0BCQEWEGNhQGRp
-- LnB1Yy1yaW8uYnIwHhcNMTcwNDEwMTkyMTU1WhcNMTgwNDEwMTkyMTU1WjB1MQsw
-- CQYDVQQGEwJCUjEMMAoGA1UECAwDUmlvMQwwCgYDVQQKDANQVUMxCzAJBgNVBAsM
-- AkRJMRMwEQYDVQQDDApVc3VhcmlvIDAxMSgwJgYJKoZIhvcNAQkBFhl1c2VyMDFA
-- aW5mMTQxNi5wdWMtcmlvLmJyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKC
-- AQEAvzUNGfNciZ6QYwqcHcy7oYe5C6QeLGPeb1gxHeN/Z+JeWlf5/N21XQeXaWg8
-- Ii/X610IjJ4NYb87/6gA6OWitR/h/DgOAQbqZAL9q7lnNt2xqF4PBbW3W4vm+Irg
-- XnTYMtUnYZdgvPICT12cmsVKvP+7LIp8aK+GcxSyu0zIfhhSrMfJh3SmSWSDod1+
-- no3oxv/iGb4Zs92wB92U3LHtf/XJrOoTwZtvof0WtJhUFbtY/6EG//+GvHFyzln6
-- WHBcRv++/6QQLWlajyE+/3035eUBVNiGVeevLi7vT0ZPnp4U+TqpRlHWVfu02WrK
-- c4FHV+skblwrwm370/rjsbC2EwIDAQABo3sweTAJBgNVHRMEAjAAMCwGCWCGSAGG
-- +EIBDQQfFh1PcGVuU1NMIEdlbmVyYXRlZCBDZXJ0aWZpY2F0ZTAdBgNVHQ4EFgQU
-- 5iOOnQ8g9xGbDhTiNx/P5q9axdcwHwYDVR0jBBgwFoAUEY7rPCMt62gRfkON1E8H
-- SCX1KyYwDQYJKoZIhvcNAQELBQADggEBAHqqyPZn/OwYr8FJM2eQplD1sL68mZ28
-- mP6IS91li4NWMlp7m7V+N4NLzfeMPcDy5bd8/kYT8UMz7v8o84WzlwQESbD3ZmVt
-- JCSfe2AuDj5MRL/vwWyEllADowudSZV6RK6RhsClrEz2J/TapCferFQorMLxQSs/
-- 89ID86F9txo4FVV08Aa1Fgnwas8pfIgFaj5/MGkZs+6MUIvEs9qPsOk42DjxmF1H
-- CT7m2DpJjlWMo/UN2ktydqfei93lazfHA2WlD/SCuTfiBCtyw7N9JH/260Htx7Vy
-- GKN0agqcnUjcMNJJ6FV2YUVAOB40PhXcnpSPXPjh1NnT/nx4ip5nibY=
-- -----END CERTIFICATE-----', 0);

INSERT INTO User VALUES('Administrador', 'admin@inf1416.puc-rio.br', 'administrador', '5740238012', 'e4329c58f2cba5f718e74a53bd97eb44f8d44f83', 
1, 0, NULL, 0, 0,
'-----BEGIN CERTIFICATE-----
MIID5zCCAs+gAwIBAgIBAzANBgkqhkiG9w0BAQsFADB6MQswCQYDVQQGEwJCUjEM
MAoGA1UECAwDUmlvMQwwCgYDVQQHDANSaW8xDDAKBgNVBAoMA1BVQzELMAkGA1UE
CwwCREkxEzARBgNVBAMMCkFDIElORjE0MTYxHzAdBgkqhkiG9w0BCQEWEGNhQGRp
LnB1Yy1yaW8uYnIwHhcNMTcwNDEwMTkyODM0WhcNMTgwNDEwMTkyODM0WjB3MQsw
CQYDVQQGEwJCUjEMMAoGA1UECAwDUmlvMQwwCgYDVQQKDANQVUMxCzAJBgNVBAsM
AkRJMRYwFAYDVQQDDA1BZG1pbmlzdHJhZG9yMScwJQYJKoZIhvcNAQkBFhhhZG1p
bkBpbmYxNDE2LnB1Yy1yaW8uYnIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK
AoIBAQDDnq2WpTioReNQ3EapxCdmUt9khsS2BHf/YB7tjGILCzQegnV1swvcH+xf
d9FUjR7pORFSNvrfWKt93t3l2Dc0kCvVffh5BSnXIwwbW94O+E1Yp6pvpyflj8YI
+VLy0dNCiszHAF5ux6lRZYcrM4KiJndqeFRnqRP8zWI5O1kJJMXzCqIXwmXtfqVj
WiwXTnjU97xfQqKkmAt8Z+uxJaQxdZJBczmo/jQAIz1gx+SXA4TshU5Ra4sQYLo5
+FgAfA2vswHGXA6ba3N52wydZ2IYUJL2/YmTyfxzRnsyuqbL+hcOw6bm+g0OEIIC
7JduKpinz3BieiO15vameAJlqpedAgMBAAGjezB5MAkGA1UdEwQCMAAwLAYJYIZI
AYb4QgENBB8WHU9wZW5TU0wgR2VuZXJhdGVkIENlcnRpZmljYXRlMB0GA1UdDgQW
BBSeUNmquC0OBxDLGpUaDNxe1t2EADAfBgNVHSMEGDAWgBQRjus8Iy3raBF+Q43U
TwdIJfUrJjANBgkqhkiG9w0BAQsFAAOCAQEARLoAiIG4F59BPa4JI0jrSuf1lzKi
SOUTKqxRBVJElaI/pbuImFXi3s0Ur6BprkIab8HLGYDIIIfF/WuM3cCHrqbpLtVn
1/QZ7imyr7m/owq8AypU5koOTa9Gn21oeEnIPO3Pqh5vVrtgZYM7Fdze4RLSZbt1
0sR2bM3PmfTrDFlfk557VZa+kKaTnUKrrg04P+npa9KV8lsZnmigYQyBzRIEUZJN
JvhgjK8iOLc08HU+A2YZuPI+aPde9X6Y2KIQ/Y1sQVnm5esP1zKzLrZ0Hwa+E62l
gv1Ck3N/H4Afb3uSNha6rOBWBuc02Gtex4avclOgDVdUDCB3IzIN0CAeKA==
-----END CERTIFICATE-----', 0, 0);



INSERT INTO TanList VALUES(1, 'admin@inf1416.puc-rio.br', 'P6YV1', 0, 0);
INSERT INTO TanList VALUES(2, 'admin@inf1416.puc-rio.br', 'EVQ23', 0, 1);
INSERT INTO TanList VALUES(3, 'admin@inf1416.puc-rio.br', 'TP2M2', 0, 2);
INSERT INTO TanList VALUES(4, 'admin@inf1416.puc-rio.br', '5GHF4', 0, 3);
INSERT INTO TanList VALUES(5, 'admin@inf1416.puc-rio.br', 'S7HV5', 0, 4);
INSERT INTO TanList VALUES(6, 'admin@inf1416.puc-rio.br', 'LQ976', 0, 5);
INSERT INTO TanList VALUES(7, 'admin@inf1416.puc-rio.br', 'SNQJ7', 0, 6);
INSERT INTO TanList VALUES(8, 'admin@inf1416.puc-rio.br', 'DR6S8', 0, 7);
INSERT INTO TanList VALUES(9, 'admin@inf1416.puc-rio.br', 'DRIL9', 0, 8);
INSERT INTO TanList VALUES(10, 'admin@inf1416.puc-rio.br', '5K202', 0, 9);