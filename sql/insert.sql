INSERT INTO Tipo 
VALUES ('Body building', 'Per lo sviluppo e potenziamento della massa muscolare.');

INSERT INTO Tipo
VALUES ('Ginnastica correttiva', 'Tecnici esperti, tra cui fisioterapisti, chinesiologi e osteopati affiancano i nostri istruttori per 
offrire una gamma di esercizi personalizzati per le vostre esigenze, volti al miglioramento della postura.');

INSERT INTO Tipo
VALUES ('Nuoto', 'I corsi in acqua per tutti i livelli si svolgono nelle due ampie vasche di cui dispone la struttura. Il nuoto permette un regolare ed uniforme sviluppo di tutti i gruppi muscolari del corpo.');

INSERT INTO Tipo
VALUES ('Cardio', 'Tecniche, programmi di fitness e particolari sequenze di esercizi per poter tonificare il corpo, rendendolo più flessibile, sano e armonioso allo stesso tempo.');



INSERT INTO Istruttore
VALUES (1, 'Mario', 'Rossi', '045802123');

INSERT INTO Istruttore
VALUES (2, 'Luigi', 'Valli', '3391208373');

INSERT INTO Istruttore
VALUES (3, 'Antonella', 'Marchi', '3331098473');

INSERT INTO Istruttore
VALUES (4, 'Paola', 'Mantovani', '3342312980');


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
