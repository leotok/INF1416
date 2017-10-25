PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
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
INSERT INTO "User" VALUES('Administrador','admin@inf1416.puc-rio.br','administrador','5740238012','e4329c58f2cba5f718e74a53bd97eb44f8d44f83',1,0,NULL,1,0,'-----BEGIN CERTIFICATE-----
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
-----END CERTIFICATE-----',0,0);
INSERT INTO "User" VALUES('Usuario 02','user02@inf1416.puc-rio.br','usuario','2366011612','8b75bb41034a384c376a52fc1311256700ceb271',1,3,'2017-10-25 19:48:43',0,0,'-----BEGIN CERTIFICATE-----
MIID5TCCAs2gAwIBAgIBBDANBgkqhkiG9w0BAQsFADB6MQswCQYDVQQGEwJCUjEM
MAoGA1UECAwDUmlvMQwwCgYDVQQHDANSaW8xDDAKBgNVBAoMA1BVQzELMAkGA1UE
CwwCREkxEzARBgNVBAMMCkFDIElORjE0MTYxHzAdBgkqhkiG9w0BCQEWEGNhQGRp
LnB1Yy1yaW8uYnIwHhcNMTcwNDExMjA1NjA2WhcNMTgwNDExMjA1NjA2WjB1MQsw
CQYDVQQGEwJCUjEMMAoGA1UECAwDUmlvMQwwCgYDVQQKDANQVUMxCzAJBgNVBAsM
AkRJMRMwEQYDVQQDDApVc3VhcmlvIDAyMSgwJgYJKoZIhvcNAQkBFhl1c2VyMDJA
aW5mMTQxNi5wdWMtcmlvLmJyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKC
AQEAtCUDXo2E6vE2ZxvQuX86AZfAA7twRLvJBjP9rGOJR2WlrL6PFc9E1qM18m7y
SdAWn5ggb73LLL+UQJRmUeCKxWIK5su2lW6zQ+RZIBFrDjyUFy/wkcXfS+KgtqLn
3n9tO7AsZGU7MBzyXBF+SgYwwaueEVtdDtKJ5T+9kwPh5W18GVf0bPa/1n9lKBJQ
AoOprnmB09DO4UXYh1eSw5TbTDIj2kNf/FpewEt+9y/Nx6vYpqRNo+8XUhPOpziP
9cisGuH/cvVlwrjjPH9+sBZzJ8spPqcPvR6oBqsuA9JGXQjPr1MM0ucFh2YVI3bG
dnbUQqsMIvQ7zJGsY6fEp2Up2QIDAQABo3sweTAJBgNVHRMEAjAAMCwGCWCGSAGG
+EIBDQQfFh1PcGVuU1NMIEdlbmVyYXRlZCBDZXJ0aWZpY2F0ZTAdBgNVHQ4EFgQU
wybfoeS/jWeq/BR0lJDec89vCbgwHwYDVR0jBBgwFoAUEY7rPCMt62gRfkON1E8H
SCX1KyYwDQYJKoZIhvcNAQELBQADggEBAJW9z/YN/D8y8Pt7QlSLJk0+P5IQfx7B
OVEuBmViZRtK+0eCTCn9i+jlQkZftvRz9uv4SfsT8hqY/MFAFv0/ttZg6YzH0Iio
iCXLRDfx3kMHt1w86bxKbo4iY5ps0QbqXNQg2rrXVsy/edGLxgg5yNJh0sZO/hlm
u/8uIq9ATkn47tOnZe9vf3YE6KLvmfo6DGn92hz+MNdjYgrz+RNlA4kyGTssqumS
GPQ31pollRf6mAEhwyDpDEEYmpPtnvCe1XIleJarfoYzRc/mhyfwMe4k6N9twh88
2BKn/lIh8gE8Nh4YY2MupkvTe8TtpTWxXC+g5LnnXg9GRmcIjr2TJVs=
-----END CERTIFICATE-----
',0,0);
INSERT INTO "User" VALUES('Usuario 03','user03@inf1416.puc-rio.br','usuario','6832627312','0d7bac929ac793f37c7bde66968dbbebbf820dd2',1,0,'2017-10-25 19:50:07',0,0,'-----BEGIN CERTIFICATE-----
MIID5TCCAs2gAwIBAgIBBTANBgkqhkiG9w0BAQsFADB6MQswCQYDVQQGEwJCUjEM
MAoGA1UECAwDUmlvMQwwCgYDVQQHDANSaW8xDDAKBgNVBAoMA1BVQzELMAkGA1UE
CwwCREkxEzARBgNVBAMMCkFDIElORjE0MTYxHzAdBgkqhkiG9w0BCQEWEGNhQGRp
LnB1Yy1yaW8uYnIwHhcNMTcwNDExMjA1NjI1WhcNMTgwNDExMjA1NjI1WjB1MQsw
CQYDVQQGEwJCUjEMMAoGA1UECAwDUmlvMQwwCgYDVQQKDANQVUMxCzAJBgNVBAsM
AkRJMRMwEQYDVQQDDApVc3VhcmlvIDAzMSgwJgYJKoZIhvcNAQkBFhl1c2VyMDNA
aW5mMTQxNi5wdWMtcmlvLmJyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKC
AQEA2vbFwuMKFMKcXnEXOumehObPEfaprFzzehgickUZSXZjy0y7qu6912d8xWGx
LVdpMP2VahTDHExX5H7ROPVGvfdobXx+aOFW9/2LQVzTQixEcO3rq9TIn1uX78Vj
KVwjD1+i8O66KkW+lJ/yXnFMhNcpcRreW4wrEijUy33DQI3ZT7wWk/sIycWoi30b
R9HA4R3S99JuPss15Y26k6NZawxU2KY6ZwDrTim37KOarIPhe0wJDVeSYIWdWBGT
gbrRYCIsO2mfDGenCxU3WiQYFq+r+xXbzfZQpKgIua6u4xaKml+CfPu1u703h0K4
rQrPR1U8b+BSIKd729ZnatHx5QIDAQABo3sweTAJBgNVHRMEAjAAMCwGCWCGSAGG
+EIBDQQfFh1PcGVuU1NMIEdlbmVyYXRlZCBDZXJ0aWZpY2F0ZTAdBgNVHQ4EFgQU
rBqJ2IKsR/uJg2e8ZWZFc6BmvdQwHwYDVR0jBBgwFoAUEY7rPCMt62gRfkON1E8H
SCX1KyYwDQYJKoZIhvcNAQELBQADggEBAK0tq5sfMmNWoskbnedsKVqxbg/Im3Qi
SLVV6T0ZU7tmlOUL+xgp9XtgPdK7+87WF6H9EgRRChIfa7UP4gHl8sYUyviHo6+X
qr6QSAiKdbVsyPISxwvC+vIHXJBJV01AsMjVgO5NLdfFcJn3Uj34YE2o05/whgvE
teB+/+x17COCJPoP0fz1EMU6Q6Nwaujks7HE70NSvigO0mENIPRboFOKIc63sZ+y
6ldlpkxbDUcy2qcNRYjf/uS3yQ100WAOHgseEA/PVn9MRhLBc8LAR3n/jSnq/mbU
SrrgEGyCXaxEND6wmTu5UFWOd01KpsibPgcUPApFZYq+OnvTYFOfWbI=
-----END CERTIFICATE-----
',0,4);
INSERT INTO "User" VALUES('Usuario 04','user04@inf1416.puc-rio.br','usuario','5711530717','a426556a0b34ab1d45b707d57c1bdf58879500e4',1,0,NULL,1,0,'-----BEGIN CERTIFICATE-----
MIID5TCCAs2gAwIBAgIBBjANBgkqhkiG9w0BAQsFADB6MQswCQYDVQQGEwJCUjEM
MAoGA1UECAwDUmlvMQwwCgYDVQQHDANSaW8xDDAKBgNVBAoMA1BVQzELMAkGA1UE
CwwCREkxEzARBgNVBAMMCkFDIElORjE0MTYxHzAdBgkqhkiG9w0BCQEWEGNhQGRp
LnB1Yy1yaW8uYnIwHhcNMTcwNDExMjA1NjQ4WhcNMTgwNDExMjA1NjQ4WjB1MQsw
CQYDVQQGEwJCUjEMMAoGA1UECAwDUmlvMQwwCgYDVQQKDANQVUMxCzAJBgNVBAsM
AkRJMRMwEQYDVQQDDApVc3VhcmlvIDA0MSgwJgYJKoZIhvcNAQkBFhl1c2VyMDRA
aW5mMTQxNi5wdWMtcmlvLmJyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKC
AQEAxTxRUatF0qt39AzoK+7RjV6nESmxyQTWBSsAaZGrLCto+Zz5rvw5/8fRo+9U
uYwdcYBPbgKLBqQkarpOL2GqdHqUsyyHqqpOKgBc9Hy954O8oau1/W0ti7jxPuku
v0QBOFawArd8iYuddzvGHfCRQbLbamvS3+heFThuzWVvvFXewDD0VdnI9Dq6IR17
MEEvSnHkHc4WVRp+lnWqHnkJMQlrOdAWhdF90OxcGUMNk2tbWdZ4L8C7YHsHU/lC
u9I9fo+OrN23ftBHaUDy4C/iuwizgvCGYaRXNJY7/IVodk0SlzOCRXOR4yLhbkhF
nhpOTfpbmGEBpM2D63RTFdtzCQIDAQABo3sweTAJBgNVHRMEAjAAMCwGCWCGSAGG
+EIBDQQfFh1PcGVuU1NMIEdlbmVyYXRlZCBDZXJ0aWZpY2F0ZTAdBgNVHQ4EFgQU
LPHsSFw5tP58eYjW4Ahkaw0ciakwHwYDVR0jBBgwFoAUEY7rPCMt62gRfkON1E8H
SCX1KyYwDQYJKoZIhvcNAQELBQADggEBANLdh5mSZ9Yz65U2xJnCg3xahg1BFOEx
0LyIk5AOxVyfCshRmm4YNpNCwMFoC4ffBHnC/Y4nzi5f2m3ZQCxT24i5MNiwFPP9
jEz8g5mqYDFhKCy/tCRIMBKBlBBaJ8Kg7utOJcg8Kew5FBwsP7M606dtaONtkmfw
xhYaeh2DFXemzyU3W4bmCmHxbtwITjed6GzxwVszu9jXyyK1k6jhT5x2WAOLXXk9
Mi6QdmpRZGsCi5mUfo4v5Mlo/TNRkGy3DPwm/vS3F77SVH5Mrceri/3sOVia/cYl
bxMul4wdqZF4whLoo2wwlJR3jGDkIMXxdnbh3W5jxfHBBOnBRxJR6d0=
-----END CERTIFICATE-----
',0,0);
CREATE TABLE Registro (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  messageId INTEGER NOT NULL,
  email TEXT,
  filename TEXT,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(messageId) REFERENCES Mensagem(id)
);
INSERT INTO "Registro" VALUES(1,1001,'null','null','2017-10-25 19:40:55');
INSERT INTO "Registro" VALUES(2,2001,'null','null','2017-10-25 19:40:55');
INSERT INTO "Registro" VALUES(3,2003,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:17');
INSERT INTO "Registro" VALUES(4,2002,'null','null','2017-10-25 19:41:17');
INSERT INTO "Registro" VALUES(5,3001,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:17');
INSERT INTO "Registro" VALUES(6,3003,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:28');
INSERT INTO "Registro" VALUES(7,3002,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:28');
INSERT INTO "Registro" VALUES(8,4001,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:29');
INSERT INTO "Registro" VALUES(9,4003,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:39');
INSERT INTO "Registro" VALUES(10,4002,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:39');
INSERT INTO "Registro" VALUES(11,5001,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:39');
INSERT INTO "Registro" VALUES(12,5002,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:43');
INSERT INTO "Registro" VALUES(13,6001,'admin@inf1416.puc-rio.br','null','2017-10-25 19:41:43');
INSERT INTO "Registro" VALUES(14,6002,'admin@inf1416.puc-rio.br','null','2017-10-25 19:45:00');
INSERT INTO "Registro" VALUES(15,6006,'admin@inf1416.puc-rio.br','null','2017-10-25 19:45:12');
INSERT INTO "Registro" VALUES(16,6001,'admin@inf1416.puc-rio.br','null','2017-10-25 19:45:15');
INSERT INTO "Registro" VALUES(17,6002,'admin@inf1416.puc-rio.br','null','2017-10-25 19:46:11');
INSERT INTO "Registro" VALUES(18,6006,'admin@inf1416.puc-rio.br','null','2017-10-25 19:46:15');
INSERT INTO "Registro" VALUES(19,6001,'admin@inf1416.puc-rio.br','null','2017-10-25 19:46:17');
INSERT INTO "Registro" VALUES(20,6002,'admin@inf1416.puc-rio.br','null','2017-10-25 19:47:01');
INSERT INTO "Registro" VALUES(21,6007,'admin@inf1416.puc-rio.br','null','2017-10-25 19:47:03');
INSERT INTO "Registro" VALUES(22,6002,'admin@inf1416.puc-rio.br','null','2017-10-25 19:47:19');
INSERT INTO "Registro" VALUES(23,6006,'admin@inf1416.puc-rio.br','null','2017-10-25 19:47:23');
INSERT INTO "Registro" VALUES(24,6001,'admin@inf1416.puc-rio.br','null','2017-10-25 19:47:25');
INSERT INTO "Registro" VALUES(25,6008,'admin@inf1416.puc-rio.br','null','2017-10-25 19:47:27');
INSERT INTO "Registro" VALUES(26,5001,'admin@inf1416.puc-rio.br','null','2017-10-25 19:47:28');
INSERT INTO "Registro" VALUES(27,5005,'admin@inf1416.puc-rio.br','null','2017-10-25 19:47:38');
INSERT INTO "Registro" VALUES(28,1002,'null','null','2017-10-25 19:47:42');
INSERT INTO "Registro" VALUES(29,1001,'null','null','2017-10-25 19:47:47');
INSERT INTO "Registro" VALUES(30,2001,'null','null','2017-10-25 19:47:48');
INSERT INTO "Registro" VALUES(31,2005,'user05@inf1416.puc-rio.br','null','2017-10-25 19:48:20');
INSERT INTO "Registro" VALUES(32,2003,'user02@inf1416.puc-rio.br','null','2017-10-25 19:48:29');
INSERT INTO "Registro" VALUES(33,2002,'null','null','2017-10-25 19:48:30');
INSERT INTO "Registro" VALUES(34,3001,'user02@inf1416.puc-rio.br','null','2017-10-25 19:48:30');
INSERT INTO "Registro" VALUES(35,3004,'user02@inf1416.puc-rio.br','null','2017-10-25 19:48:36');
INSERT INTO "Registro" VALUES(36,3005,'user02@inf1416.puc-rio.br','null','2017-10-25 19:48:40');
INSERT INTO "Registro" VALUES(37,3006,'user02@inf1416.puc-rio.br','null','2017-10-25 19:48:43');
INSERT INTO "Registro" VALUES(38,3007,'user02@inf1416.puc-rio.br','null','2017-10-25 19:49:09');
INSERT INTO "Registro" VALUES(39,2001,'null','null','2017-10-25 19:49:14');
INSERT INTO "Registro" VALUES(40,2004,'user02@inf1416.puc-rio.br','null','2017-10-25 19:49:25');
INSERT INTO "Registro" VALUES(41,2003,'user03@inf1416.puc-rio.br','null','2017-10-25 19:49:38');
INSERT INTO "Registro" VALUES(42,2002,'null','null','2017-10-25 19:49:38');
INSERT INTO "Registro" VALUES(43,3001,'user03@inf1416.puc-rio.br','null','2017-10-25 19:49:39');
INSERT INTO "Registro" VALUES(44,3003,'user03@inf1416.puc-rio.br','null','2017-10-25 19:49:49');
INSERT INTO "Registro" VALUES(45,3002,'user03@inf1416.puc-rio.br','null','2017-10-25 19:49:49');
INSERT INTO "Registro" VALUES(46,4001,'user03@inf1416.puc-rio.br','null','2017-10-25 19:49:49');
INSERT INTO "Registro" VALUES(47,4004,'user03@inf1416.puc-rio.br','null','2017-10-25 19:49:55');
INSERT INTO "Registro" VALUES(48,4005,'user03@inf1416.puc-rio.br','null','2017-10-25 19:49:58');
INSERT INTO "Registro" VALUES(49,4006,'user03@inf1416.puc-rio.br','null','2017-10-25 19:50:00');
INSERT INTO "Registro" VALUES(50,4007,'user03@inf1416.puc-rio.br','null','2017-10-25 19:50:04');
INSERT INTO "Registro" VALUES(51,2001,'null','null','2017-10-25 19:50:06');
INSERT INTO "Registro" VALUES(52,2004,'user03@inf1416.puc-rio.br','null','2017-10-25 19:50:12');
INSERT INTO "Registro" VALUES(53,2003,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:17');
INSERT INTO "Registro" VALUES(54,2002,'null','null','2017-10-25 19:50:17');
INSERT INTO "Registro" VALUES(55,3001,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:17');
INSERT INTO "Registro" VALUES(56,3003,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:28');
INSERT INTO "Registro" VALUES(57,3002,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:29');
INSERT INTO "Registro" VALUES(58,4001,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:29');
INSERT INTO "Registro" VALUES(59,4003,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:41');
INSERT INTO "Registro" VALUES(60,4002,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:41');
INSERT INTO "Registro" VALUES(61,5001,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:41');
INSERT INTO "Registro" VALUES(62,5003,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:51');
INSERT INTO "Registro" VALUES(63,7001,'user04@inf1416.puc-rio.br','null','2017-10-25 19:50:51');
INSERT INTO "Registro" VALUES(64,7007,'user04@inf1416.puc-rio.br','null','2017-10-25 19:51:03');
INSERT INTO "Registro" VALUES(65,5001,'user04@inf1416.puc-rio.br','null','2017-10-25 19:51:03');
INSERT INTO "Registro" VALUES(66,5004,'user04@inf1416.puc-rio.br','null','2017-10-25 19:52:03');
INSERT INTO "Registro" VALUES(67,8001,'user04@inf1416.puc-rio.br','null','2017-10-25 19:52:03');
INSERT INTO "Registro" VALUES(68,8007,'user04@inf1416.puc-rio.br','null','2017-10-25 19:52:52');
INSERT INTO "Registro" VALUES(69,8003,'user04@inf1416.puc-rio.br','null','2017-10-25 19:52:52');
INSERT INTO "Registro" VALUES(70,8008,'user04@inf1416.puc-rio.br','null','2017-10-25 19:52:52');
INSERT INTO "Registro" VALUES(71,8007,'user04@inf1416.puc-rio.br','null','2017-10-25 19:54:49');
INSERT INTO "Registro" VALUES(72,8002,'user04@inf1416.puc-rio.br','null','2017-10-25 19:54:49');
INSERT INTO "Registro" VALUES(73,8009,'user04@inf1416.puc-rio.br','null','2017-10-25 19:54:49');
INSERT INTO "Registro" VALUES(74,8005,'user04@inf1416.puc-rio.br','null','2017-10-25 19:55:35');
INSERT INTO "Registro" VALUES(75,8008,'user04@inf1416.puc-rio.br','null','2017-10-25 19:56:33');
INSERT INTO "Registro" VALUES(76,8006,'user04@inf1416.puc-rio.br','null','2017-10-25 19:57:38');
INSERT INTO "Registro" VALUES(77,5001,'user04@inf1416.puc-rio.br','null','2017-10-25 19:57:38');
INSERT INTO "Registro" VALUES(78,5005,'user04@inf1416.puc-rio.br','null','2017-10-25 19:57:41');
INSERT INTO "Registro" VALUES(79,1002,'null','null','2017-10-25 19:57:44');
CREATE TABLE Mensagem (
  id INTEGER PRIMARY KEY,
  texto TEXT NOT NULL
);
INSERT INTO "Mensagem" VALUES(1001,'Sistema iniciado.');
INSERT INTO "Mensagem" VALUES(1002,'Sistema encerrado.');
INSERT INTO "Mensagem" VALUES(2001,'Autenticação etapa 1 iniciada.');
INSERT INTO "Mensagem" VALUES(2002,'Autenticação etapa 1 encerrada.');
INSERT INTO "Mensagem" VALUES(2003,'Login name <login_name> identificado com acesso liberado.');
INSERT INTO "Mensagem" VALUES(2004,'Login name <login_name> identificado com acesso bloqueado.');
INSERT INTO "Mensagem" VALUES(2005,'Login name <login_name> não identificado.');
INSERT INTO "Mensagem" VALUES(3001,'Autenticação etapa 2 iniciada para <login_name>.');
INSERT INTO "Mensagem" VALUES(3002,'Autenticação etapa 2 encerrada para <login_name>.');
INSERT INTO "Mensagem" VALUES(3003,'Senha pessoal verificada positivamente para <login_name>.');
INSERT INTO "Mensagem" VALUES(3004,'Senha pessoal verificada negativamente para <login_name>.');
INSERT INTO "Mensagem" VALUES(3005,'Primeiro erro da senha pessoal contabilizado para <login_name>.');
INSERT INTO "Mensagem" VALUES(3006,'Segundo erro da senha pessoal contabilizado para <login_name>.');
INSERT INTO "Mensagem" VALUES(3007,'Terceiro erro da senha pessoal contabilizado para <login_name>.');
INSERT INTO "Mensagem" VALUES(3008,'Acesso do usuario <login_name> bloqueado pela autenticação etapa 2.');
INSERT INTO "Mensagem" VALUES(4001,'Autenticação etapa 3 iniciada para <login_name>.');
INSERT INTO "Mensagem" VALUES(4002,'Autenticação etapa 3 encerrada para <login_name>.');
INSERT INTO "Mensagem" VALUES(4003,'Senha de única vez verificada positivamente para <login_name>.');
INSERT INTO "Mensagem" VALUES(4004,'Primeiro erro da senha de única vez contabilizado para <login_name>.');
INSERT INTO "Mensagem" VALUES(4005,'Segundo erro da senha de única vez contabilizado para <login_name>.');
INSERT INTO "Mensagem" VALUES(4006,'Terceiro erro da senha de única vez contabilizado para <login_name>.');
INSERT INTO "Mensagem" VALUES(4009,'Acesso do usuario <login_name> bloqueado pela autenticação etapa 3.');
INSERT INTO "Mensagem" VALUES(5001,'Tela principal apresentada para <login_name>.');
INSERT INTO "Mensagem" VALUES(5002,'Opção 1 do menu principal selecionada por <login_name>.');
INSERT INTO "Mensagem" VALUES(5003,'Opção 2 do menu principal selecionada por <login_name>.');
INSERT INTO "Mensagem" VALUES(5004,'Opção 3 do menu principal selecionada por <login_name>.');
INSERT INTO "Mensagem" VALUES(5005,'Opção 4 do menu principal selecionada por <login_name>.');
INSERT INTO "Mensagem" VALUES(6001,'Tela de cadastro apresentada para <login_name>.');
INSERT INTO "Mensagem" VALUES(6002,'Botão cadastrar pressionado por <login_name>.');
INSERT INTO "Mensagem" VALUES(6003,'Caminho do certificado digital inválido fornecido por <login_name>.');
INSERT INTO "Mensagem" VALUES(6004,'Confirmação de dados aceita por <login_name>.');
INSERT INTO "Mensagem" VALUES(6005,'Confirmação de dados rejeitada por <login_name>.');
INSERT INTO "Mensagem" VALUES(6006,'Botão voltar de cadastro para o menu principal pressionado por <login_name>.');
INSERT INTO "Mensagem" VALUES(7001,'Tela de carregamento da chave privada apresentada para <login_name>.');
INSERT INTO "Mensagem" VALUES(7002,'Caminho da chave privada inválido fornecido por <login_name>.');
INSERT INTO "Mensagem" VALUES(7003,'Frase secreta inválida fornecida por <login_name>.');
INSERT INTO "Mensagem" VALUES(7004,'Erro de validação da chave privada com o certificado digital de <login_name>.');
INSERT INTO "Mensagem" VALUES(7005,'Chave privada validada com sucesso para <login_name>.');
INSERT INTO "Mensagem" VALUES(7006,'Botão voltar de carregamento para o menu principal pressionado por <login_name>.');
INSERT INTO "Mensagem" VALUES(8001,'Tela de consulta de arquivos secretos apresentada para <login_name>.');
INSERT INTO "Mensagem" VALUES(8002,'Botão voltar de consulta para o menu principal pressionado por <login_name>.');
INSERT INTO "Mensagem" VALUES(8003,'Botão Listar de consulta pressionado por <login_name>.');
INSERT INTO "Mensagem" VALUES(8006,'Caminho de pasta inválido fornecido por <login_name>.');
INSERT INTO "Mensagem" VALUES(8007,'Lista de arquivos apresentada para <login_name>.');
INSERT INTO "Mensagem" VALUES(8008,'Arquivo <arq_name> selecionado por <login_name> para decriptação.');
INSERT INTO "Mensagem" VALUES(8009,'Arquivo <arq_name> decriptado com sucesso para <login_name>.');
INSERT INTO "Mensagem" VALUES(8010,'Arquivo <arq_name> verificado (integridade e autenticidade) com sucesso para <login_name>.');
INSERT INTO "Mensagem" VALUES(8011,'Falha na decriptação do arquivo <arq_name> para <login_name>.');
INSERT INTO "Mensagem" VALUES(8012,'Falha na verificação do arquivo <arq_name> para <login_name>.');
INSERT INTO "Mensagem" VALUES(9001,'Tela de saída apresentada para <login_name>.');
INSERT INTO "Mensagem" VALUES(9002,'Botão sair pressionado por <login_name>.');
INSERT INTO "Mensagem" VALUES(9003,'Botão voltar de sair para o menu principal pressionado por <login_name>.');
CREATE TABLE TanList (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  email TEXT NOT NULL,
  tan TEXT NOT NULL,
  usada BOOL DEFAULT 0,
   posicao INTEGER NOT NULL,
  -- PRIMARY KEY(id, email),
  FOREIGN KEY(email) REFERENCES User(email)
);
INSERT INTO "TanList" VALUES(1,'admin@inf1416.puc-rio.br','P6YV1',0,0);
INSERT INTO "TanList" VALUES(2,'admin@inf1416.puc-rio.br','EVQ23',0,1);
INSERT INTO "TanList" VALUES(3,'admin@inf1416.puc-rio.br','TP2M2',0,2);
INSERT INTO "TanList" VALUES(4,'admin@inf1416.puc-rio.br','5GHF4',0,3);
INSERT INTO "TanList" VALUES(5,'admin@inf1416.puc-rio.br','S7HV5',0,4);
INSERT INTO "TanList" VALUES(6,'admin@inf1416.puc-rio.br','LQ976',0,5);
INSERT INTO "TanList" VALUES(7,'admin@inf1416.puc-rio.br','SNQJ7',1,6);
INSERT INTO "TanList" VALUES(8,'admin@inf1416.puc-rio.br','DR6S8',0,7);
INSERT INTO "TanList" VALUES(9,'admin@inf1416.puc-rio.br','DRIL9',0,8);
INSERT INTO "TanList" VALUES(10,'admin@inf1416.puc-rio.br','5K202',0,9);
INSERT INTO "TanList" VALUES(11,'user02@inf1416.puc-rio.br','S0WP5',0,0);
INSERT INTO "TanList" VALUES(12,'user02@inf1416.puc-rio.br','FP501',0,1);
INSERT INTO "TanList" VALUES(13,'user02@inf1416.puc-rio.br','LX7O5',0,2);
INSERT INTO "TanList" VALUES(14,'user02@inf1416.puc-rio.br','RKB6F',0,3);
INSERT INTO "TanList" VALUES(15,'user02@inf1416.puc-rio.br','15Q8G',0,4);
INSERT INTO "TanList" VALUES(16,'user02@inf1416.puc-rio.br','CE21V',0,5);
INSERT INTO "TanList" VALUES(17,'user02@inf1416.puc-rio.br','LYGLJ',0,6);
INSERT INTO "TanList" VALUES(18,'user02@inf1416.puc-rio.br','EK3MC',0,7);
INSERT INTO "TanList" VALUES(19,'user02@inf1416.puc-rio.br','00QV0',0,8);
INSERT INTO "TanList" VALUES(20,'user02@inf1416.puc-rio.br','RHXWZ',0,9);
INSERT INTO "TanList" VALUES(21,'user03@inf1416.puc-rio.br','VLPW2',0,0);
INSERT INTO "TanList" VALUES(22,'user03@inf1416.puc-rio.br','K7I6Z',0,1);
INSERT INTO "TanList" VALUES(23,'user03@inf1416.puc-rio.br','P1DMK',0,2);
INSERT INTO "TanList" VALUES(24,'user03@inf1416.puc-rio.br','5GC6X',0,3);
INSERT INTO "TanList" VALUES(25,'user03@inf1416.puc-rio.br','WM7P8',0,4);
INSERT INTO "TanList" VALUES(26,'user03@inf1416.puc-rio.br','75EZA',0,5);
INSERT INTO "TanList" VALUES(27,'user03@inf1416.puc-rio.br','64UX8',0,6);
INSERT INTO "TanList" VALUES(28,'user03@inf1416.puc-rio.br','FEH2K',0,7);
INSERT INTO "TanList" VALUES(29,'user03@inf1416.puc-rio.br','9EGQH',0,8);
INSERT INTO "TanList" VALUES(30,'user03@inf1416.puc-rio.br','ANNBL',0,9);
INSERT INTO "TanList" VALUES(31,'user04@inf1416.puc-rio.br','QVYG5',0,0);
INSERT INTO "TanList" VALUES(32,'user04@inf1416.puc-rio.br','GVKSD',0,1);
INSERT INTO "TanList" VALUES(33,'user04@inf1416.puc-rio.br','2FBRZ',0,2);
INSERT INTO "TanList" VALUES(34,'user04@inf1416.puc-rio.br','GATMJ',0,3);
INSERT INTO "TanList" VALUES(35,'user04@inf1416.puc-rio.br','KV239',0,4);
INSERT INTO "TanList" VALUES(36,'user04@inf1416.puc-rio.br','WM8EO',0,5);
INSERT INTO "TanList" VALUES(37,'user04@inf1416.puc-rio.br','KWT05',1,6);
INSERT INTO "TanList" VALUES(38,'user04@inf1416.puc-rio.br','NFNDX',0,7);
INSERT INTO "TanList" VALUES(39,'user04@inf1416.puc-rio.br','H12H9',0,8);
INSERT INTO "TanList" VALUES(40,'user04@inf1416.puc-rio.br','CTM0V',0,9);
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('TanList',40);
INSERT INTO "sqlite_sequence" VALUES('Registro',79);
COMMIT;
