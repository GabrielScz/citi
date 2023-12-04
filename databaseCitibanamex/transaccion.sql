USE citibanamex;

DROP VIEW IF EXISTS viewTransaccion;
CREATE VIEW viewTransaccion AS
SELECT t.idTransaccion, t.tipo, t.monto, t.estatus, t.fecha, t.hora, t.codigo,
	     c.idCuenta, c.noTarjeta, c.nip, c.montoTotal,
	     b.idBanco, b.nombreBanco, b.fondoDisponible
FROM transaccion AS t
INNER JOIN cuenta AS c ON c.idCuenta = t.idCuenta
INNER JOIN banco AS b ON b.idBanco = t.idBanco;

SELECT * FROM viewTransaccion;

DROP PROCEDURE IF EXISTS generarTransaccion;
DELIMITER $$
CREATE PROCEDURE generarTransaccion (
    
	/* Datos de Banco */
	IN    	var_idBanco        			INT,    				--  1
	IN    	var_nombreBanco		VARCHAR(50),   		--  2
	IN    	var_fondoDisponible  	DOUBLE,     			--  3
    
    /* Datos de Cuenta */
	IN    	var_idCuenta        		INT,    				--  4
	IN 		var_montoTotal 			DOUBLE,  			--  5
                                    
	/* Datos de Transaccion */
	IN 	 	var_monto 				DOUBLE,			--  6
	OUT    	var_codigo 				VARCHAR(10),		--  7
	OUT    	var_idTransaccion    		INT          			--  8
                )                                    
    BEGIN  
    
	SET var_codigo = CONCAT('B', SUBSTRING(var_nombreBanco, 1, 3), CONCAT(FLOOR(RAND() * 1000), 'XX'));
        INSERT INTO transaccion (banco, monto, estatus, fecha, hora, codigo)
		VALUES( var_nombreBanco, var_monto, 1, CURRENT_DATE(), CURRENT_TIME(), var_codigo);
        SET var_idTransaccion = LAST_INSERT_ID();

        SET var_montoTotal = ABS(var_montoTotal - var_monto);
        UPDATE cuenta SET montoTotal = var_montoTotal WHERE idCuenta = var_idCuenta;
        
        SET var_fondoDisponible = ABS(var_fondoDisponible - var_monto);
        UPDATE banco SET fondoDisponible = var_fondoDisponible WHERE idBanco = var_idBanco;
        
    END
$$
DELIMITER ;

DROP PROCEDURE IF EXISTS generarTransaccionExterna;
DELIMITER $$
CREATE PROCEDURE generarTransaccionExterna (
                                    
	/* Datos de Transaccion */
	IN     	var_banco				VARCHAR(45)		--  1
	IN 		var_monto 				DOUBLE,			--  2
	IN    	var_codigo 				VARCHAR(10),		--  3
	IN 		var_fondoDisponible		DOUBLE			--  4
	OUT   	var_idTransaccion    		INT          			--  5
                )                                    
    BEGIN  

        INSERT INTO transaccion (banco, monto, estatus, fecha, hora, codigo)
		VALUES(var_banco, var_monto, 2, CURRENT_DATE(), CURRENT_TIME(), var_codigo);
        SET var_idTransaccion = LAST_INSERT_ID();
        
        SET var_fondoDisponible = ABS(var_fondoDisponible - var_monto);
        UPDATE banco SET fondoDisponible = var_fondoDisponible WHERE idBanco = 1;
        
    END
$$
DELIMITER ;

CALL generarTransaccion (1, 'CITIBANAMEX', 20600, 1, '123456789123', '2070', 3200, 1, 600, @OUT1, @OUT2);

SELECT CONCAT('B', SUBSTRING('CITIBANAMEX', 1, 4), CONCAT(FLOOR(RAND() * 1000), 'XX'));

