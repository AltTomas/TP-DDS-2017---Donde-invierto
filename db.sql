    -- BASE DE DATOS
	create table Cuenta (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
        nombre VARCHAR(255),
        valor numeric(19,2),
        periodo VARCHAR(255),
        empresa_id integer,
        indicador_id integer,
        primary key (id)
    );

    create table Empresa (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
        nombre varchar(255),
        antiguedad integer,
        primary key (id)
    );

    
    create table Indicador (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
        formula VARCHAR(255),
        primary key (id)
    );
    
    create table Metodologia (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
		formula LONGTEXT,
        primary key (id)
    );
    	
	ALTER TABLE Cuenta ADD CONSTRAINT FK_Cuenta_Empresa FOREIGN KEY (empresa_id) REFERENCES Empresa(id);	
	