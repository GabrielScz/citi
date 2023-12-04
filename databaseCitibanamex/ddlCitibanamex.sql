DROP DATABASE IF EXISTS citibanamex;
CREATE DATABASE citibanamex;
USE citibanamex;

DROP TABLE IF EXISTS banco;
CREATE TABLE banco (

idBanco INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
nombreBanco VARCHAR(50) NOT NULL,
fondoDisponible DOUBLE NOT NULL

);

DROP TABLE IF EXISTS cuenta;
CREATE TABLE cuenta (

idCuenta INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
idBanco INT NOT NULL,
nip VARCHAR(4) NOT NULL,
noTarjeta VARCHAR(12) NULL,
montoTotal DOUBLE NOT NULL,

CONSTRAINT fk_banco FOREIGN KEY (idBanco) 
	REFERENCES banco(idBanco)

);

DROP TABLE IF EXISTS transaccion;
CREATE TABLE transaccion (

idTransaccion INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
idCuenta INT NOT NULL,
banco VARCHAR(45) NOT NULL,
codigo VARCHAR(10) NOT NULL,
monto DOUBLE NOT NULL,
estatus INT NOT NULL, -- 0: Error, 1: Exitosa, 2: Pendiente (estatus en caso de ser otro banco).
fecha DATE NOT NULL,
hora TIME NOT NULL,

CONSTRAINT fk_cuenta FOREIGN KEY (idCuenta) 
	REFERENCES cuenta(idCuenta)

);