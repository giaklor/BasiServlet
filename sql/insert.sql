INSERT INTO Tipo 
VALUES ('Body building', 'Per lo sviluppo e potenziamento della massa muscolare.');

INSERT INTO Tipo
VALUES ('Ginnastica correttiva', 'Tecnici esperti, tra cui fisioterapisti, chinesiologi e osteopati affiancano i nostri istruttori per 
offrire una gamma di esercizi personalizzati per le vostre esigenze, volti al miglioramento della postura.');

INSERT INTO Tipo
VALUES ('Nuoto', 'I corsi in acqua per tutti i livelli si svolgono nelle due ampie vasche di cui dispone la struttura. Il nuoto permette un regolare ed uniforme sviluppo di tutti i gruppi muscolari del corpo.');

INSERT INTO Tipo
VALUES ('Cardio', 'Tecniche, programmi di fitness e particolari sequenze di esercizi per poter tonificare il corpo, rendendolo più flessibile, sano e armonioso allo stesso tempo.');

INSERT INTO Tipo
VALUES('Arti marziali', 'Insieme di pratiche fisiche, mentali e spirituali legate all''aspetto non solo pratico ma anche artistico del combattimento.');


INSERT INTO Istruttore
VALUES (1, 'Mario', 'Rossi', '045802123');

INSERT INTO Istruttore
VALUES (2, 'Luigi', 'Valli', '3391208373');

INSERT INTO Istruttore
VALUES (3, 'Antonella', 'Marchi', '3331098473');

INSERT INTO Istruttore
VALUES (4, 'Paola', 'Mantovani', '3342312980');

INSERT INTO Istruttore
VALUES (5, 'Carlo', 'Bellotti', '3923423091');

INSERT INTO Istruttore
VALUES (6, 'Marta', 'Bianco', '3351010234');


INSERT INTO Corso
VALUES (1, 'Stretch & Tone', 'Attività a medio impatto di rinforzo muscolare che prevede esercizi a corpo libero o con piccoli attrezzi con musica di sottofondo.', '2014-07-15','2014-11-01', 'Body building', 's', 3);

INSERT INTO Corso
VALUES (2, 'Kinefit', 'Riequilibra la postura con semplicità e in tempi brevissimi, agendo sulla globalità delle catene, attraverso esercizi su panca per allungamento globale decompensato.', '2014-07-01', '2014-09-12', 'Ginnastica correttiva', 'm', 1);

INSERT INTO Corso
VALUES (3, 'Nuoto bambini < 8 anni', 'Corso di nuoto per i più piccoli.', '2014-07-01', '2014-10-02', 'Nuoto', 's', 3);

INSERT INTO Corso
VALUES (4, 'Nuoto ragazzi < 15 anni', 'Corso di nuoto intermedio per perfezionare gli stili e la resistenza.', '2014-07-03', '2014-10-10', 'Nuoto', 's', 3);

INSERT INTO Corso
VALUES (5, 'Nuoto Extreme', 'Corso di nuoto di potenziamento, per chi è già in possesso di abilità di base in acqua e vuole imparare stili e tecniche avanzate.', '2014-06-28', '2014-10-12', 'Nuoto', 'm', 2);

INSERT INTO Corso
VALUES (6, 'Body Tone', 'Sequenze di esercizi eseguiti a terra e in piedi con l''ausilio di piccoli attrezzi. Migliora l''efficienza cardiovascolare e definisce piccoli e grandi muscoli.', '2014-07-02', '2014-11-30', 'Body building', 's', 2);

INSERT INTO Corso
VALUES (7, 'Aerobica', 'Migliora respirazione, resistenza ed elasticità muscolare. Aiuta a perdere peso.', '2014-06-27', '2014-12-01', 'Cardio', 'm', 4);

INSERT INTO Corso
VALUES (8, 'Zumba', 'Corso fitness imperniato su musiche e ritmi latino americani. Oltre che basarsi su movimenti facili da apprendere, le lezioni sono particolarmente divertenti e coinvolgenti.', '2014-06-22', '2014-11-30', 'Cardio', 's', 6);

INSERT INTO Corso
VALUES (9, 'Step', 'Allenamento aerobico con piattaforma individuale ad altezza regolabile. Migliora la resistenza, l''efficienza cardiovascolare e potenzia gli arti inferiori.', '2014-05-02', '2014-08-01', 'Cardio', 'm', 4);


INSERT INTO Istruttori_Corsi
VALUES (1, 5);

INSERT INTO Istruttori_Corsi
VALUES (5, 5);

INSERT INTO Istruttori_Corsi
VALUES (2, 3);

INSERT INTO Istruttori_Corsi
VALUES (6, 9);

INSERT INTO Istruttori_Corsi
VALUES (1, 9);


INSERT INTO Iscritto
VALUES ('alessia.verdelli@gmail.com', 'Alessia', 'Verdelli', '1990-05-28', 'alessia', 'verd');

INSERT INTO Iscritto
VALUES ('francesco.gallusi@gmail.com', 'Francesco', 'Gallusi', '1987-10-01', 'francesco', 'gall');

INSERT INTO Iscritto
VALUES ('mirko.scaglioni@gmail.com', 'Mirko', 'Scaglioni', '1975-01-31', 'mirko', 'scag');

INSERT INTO Iscritto
VALUES ('daniela.silvani@gmail.com', 'Daniela', 'Silvani', '1958-11-24', 'daniela', 'silv');

INSERT INTO Iscritto
VALUES ('luca.marchi@gmail.com', 'Luca', 'Marchi', '1988-02-03', 'luca', 'marc');

INSERT INTO Iscritto
VALUES ('gloria.trevi@gmail.com', 'Gloria', 'Trevi', '1994-04-10', 'gloria', 'trev');

INSERT INTO Iscritto
VALUES ('matteo.storti@gmail.com', 'Matteo', 'Storti', '2009-01-12', 'matteo', 'stor');


INSERT INTO Iscrizione
VALUES ('2014-02-10', 'alessia.verdelli@gmail.com', 7);

INSERT INTO Iscrizione
VALUES ('2014-06-25', 'gloria.trevi@gmail.com', 7);

INSERT INTO Iscrizione
VALUES ('2014-05-31', 'mirko.scaglioni@gmail.com', 6);

INSERT INTO Iscrizione
VALUES ('2014-06-02', 'francesco.gallusi@gmail.com', 6);

INSERT INTO Iscrizione
VALUES ('2014-06-20', 'luca.marchi@gmail.com', 5);

INSERT INTO Iscrizione
VALUES ('2014-06-01', 'luca.marchi@gmail.com', 1);

INSERT INTO Iscrizione
VALUES ('2014-05-31', 'daniela.silvani@gmail.com', 1);

INSERT INTO Iscrizione
VALUES ('2014-06-29', 'matteo.storti@gmail.com', 3);


INSERT INTO Lezione
VALUES (1, 1, '10:30', '11:30');

INSERT INTO Lezione
VALUES (1, 5, '09:30', '10:30');

INSERT INTO Lezione
VALUES (2, 2, '17:15', '18:45');

INSERT INTO Lezione
VALUES (2, 4, '16:30', '18:00');

INSERT INTO Lezione
VALUES (3, 3, '14:30', '16:30');

INSERT INTO Lezione
VALUES (4, 1, '15:00', '16:30');

INSERT INTO Lezione
VALUES (4, 4, '15:00', '16:30');

INSERT INTO Lezione
VALUES (5, 1, '17:30', '19:00');

INSERT INTO Lezione
VALUES (5, 3, '16:30', '18:00');

INSERT INTO Lezione
VALUES (6, 1, '09:00', '10:30');

INSERT INTO Lezione
VALUES (7, 5, '10:30', '12:30');

INSERT INTO Lezione
VALUES (8, 2, '10:00', '12:00');

INSERT INTO Lezione
VALUES (9, 2, '15:30', '17:00');


INSERT INTO Materiale_didattico
VALUES ('guidaChestIncline.pdf', 'Guida Chest Incline', 'documento', 'pdf');

INSERT INTO Materiale_didattico
VALUES ('guidaLatMachine.pdf', 'Guida Lat Machine', 'documento', 'pdf');

INSERT INTO Materiale_didattico
VALUES ('mapFisio.jpg', 'Mappa sala fisioterapia', 'immagine', 'jpg');

INSERT INTO Materiale_didattico
VALUES ('nuoto.jpg', 'Vasca olimpionica 1', 'immagine', 'jpg');

INSERT INTO Materiale_didattico
VALUES ('wobbleBoard.jpg', 'Istruzioni Wobble Board', 'immagine', 'jpg');


INSERT INTO Materiali_corso
VALUES (2, 'mapFisio.jpg');

INSERT INTO Materiali_corso
VALUES (2, 'wobbleBoard.jpg');

INSERT INTO Materiali_corso
VALUES (6, 'guidaLatMachine.pdf');

INSERT INTO Materiali_corso
VALUES (6, 'guidaChestIncline.pdf');

INSERT INTO Materiali_corso
VALUES (3, 'nuoto.jpg');

INSERT INTO Materiali_corso
VALUES (4, 'nuoto.jpg');

INSERT INTO Materiali_corso
VALUES (5, 'nuoto.jpg');
