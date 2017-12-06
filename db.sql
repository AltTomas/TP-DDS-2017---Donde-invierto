
    create table Cuenta (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
        nombre VARCHAR(255),
        valor numeric(19,2),
        periodo_id integer,
        primary key (id)
    );

    create table Empresa (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
        nombre varchar(255),
        primary key (id)
    );

    create table Periodo (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
        fechaFin varbinary(255),
        fechaInicio varbinary(255),
        primary key (id)
    );

    create table Indicador (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
        formula VARCHAR(255),
        periodo_id integer,
        primary key (id)
    );
    
    create table Metodologia (
        id integer NOT NULL AUTO_INCREMENT /*DEFAULT 1,*/,
		metodologia VARCHAR(255),
        primary key (id)
    );

	ALTER TABLE Cuenta ADD CONSTRAINT FK_Cuenta_Periodo FOREIGN KEY (periodo_id) REFERENCES Periodo(id);
	ALTER TABLE Cuenta ADD CONSTRAINT FK_Cuenta_Empresa FOREIGN KEY (empresa_id) REFERENCES Empresa(id);	
	ALTER TABLE Indicador ADD CONSTRAINT FK_Indicador_Periodo FOREIGN KEY (periodo_id) REFERENCES Periodo(id);