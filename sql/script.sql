CREATE TABLE Tipo (
	Denominazione VARCHAR(50) PRIMARY KEY,
	Descrizione VARCHAR(200)
);

CREATE TABLE Iscritto (
	Email VARCHAR(50) PRIMARY KEY,
	Nome VARCHAR(30) NOT NULL,
	Cognome VARCHAR(30) NOT NULL,
	Data_nascita DATE,
	Username VARCHAR(20) NOT NULL,
	Password VARCHAR(20) NOT NULL
);

CREATE TABLE Istruttore (
	Id_istruttore INTEGER PRIMARY KEY,
	Nome VARCHAR(30) NOT NULL,
	Cognome VARCHAR(30) NOT NULL,
	Telefono VARCHAR(15)
);

CREATE TABLE Corso (
	Id_corso INTEGER PRIMARY KEY,
	Nome VARCHAR(30),
	Descrizione VARCHAR(200),
	Data_inizio DATE,
	Data_fine DATE NOT NULL,
	Tipo_attivita VARCHAR(50),
	Tipo_corso CHAR NOT NULL,
	Istruttore_resp INTEGER NOT NULL,
	UNIQUE(Nome, Data_inizio),
	CHECK(Tipo_corso IN('s', 'm')),
	FOREIGN KEY (Istruttore_resp) REFERENCES Istruttore(Id_istruttore),
	FOREIGN KEY (Tipo_attivita) REFERENCES Tipo(Denominazione)
);

CREATE TABLE Lezione (
	Corso INTEGER,
	Giorno INTEGER,
	Ora_inizio TIME,
	Ora_fine TIME NOT NULL,
	PRIMARY KEY(Corso, Giorno, Ora_inizio),
	FOREIGN KEY (Corso) REFERENCES Corso(Id_corso),
	CHECK(Giorno >= 1 AND Giorno <= 7)
);
	
CREATE TABLE Materiale_didattico (
	Percorso VARCHAR(200) PRIMARY KEY,
	Nome VARCHAR(30),
	Tipo VARCHAR(15),
	Formato VARCHAR(10)
);

CREATE TABLE Materiali_corso (
	Corso INTEGER,
	Materiale VARCHAR(200),
	PRIMARY KEY (Corso, Materiale),
	FOREIGN KEY (Corso) REFERENCES Corso(Id_corso),
	FOREIGN KEY (Materiale) REFERENCES Materiale_didattico(Percorso)
);

CREATE TABLE Istruttori_Corsi (
	Istruttore INTEGER,
	Corso INTEGER,
	PRIMARY KEY (Istruttore, Corso),
	FOREIGN KEY (Istruttore) REFERENCES Istruttore(Id_istruttore),
	FOREIGN KEY (Corso) REFERENCES Corso(Id_corso)
);

CREATE TABLE Iscrizione (
	Data_iscrizione DATE NOT NULL,
	Iscritto VARCHAR(50),
	Corso INTEGER,
	PRIMARY KEY (Iscritto, Corso),
	FOREIGN KEY (Iscritto) REFERENCES Iscritto(Email),
	FOREIGN KEY (Corso) REFERENCES Corso(Id_corso)
);
