	CREATE TABLE Condicion (
        id INTEGER AUTO_INCREMENT PRIMARY KEY,        
		otra_empresa_id INTEGER, 
	    calculo_id INTEGER,
	    valor DECIMAL,
	);
        
    CREATE TABLE Cuenta (
        id INTEGER AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(255),
        valor NUMERIC(19,2),
        periodo_id INTEGER,
    );

    CREATE TABLE Empresa (
		id INTEGER AUTO_INCREMENT PRIMARY KEY,
        nombre varchar(255)
		/*cuentas varbinary(255),*/
    );

    CREATE TABLE Periodo (
		id INTEGER AUTO_INCREMENT PRIMARY KEY,
        fechaFin DATETIME, identity
        fechaInicio DATETIME,
    );
    
	CREATE TABLE Indicador (
		id INTEGER AUTO_INCREMENT PRIMARY KEY,
        fechaFin DATETIME,
        fechaInicio DATETIME,
    );
    
    CREATE TABLE Metodologia (
    	id INTEGER AUTO_INCREMENT PRIMARY KEY    	
    );
    
    CREATE TABLE Periodo (
		id INTEGER AUTO_INCREMENT PRIMARY KEY
		fechaInicio DATETIME,
		fechaFin DATETIME
    );
    
    
    ALTER TABLE Cuenta ADD CONSTRAINT FK_Cuenta_Periodo FOREIGN KEY (periodo_id) REFERENCES Periodo(id);
    ALTER TABLE Condicion ADD CONSTRAINT FK_Condicion_Cuenta FOREIGN KEY (calculo_id) REFERENCES Cuenta(id);