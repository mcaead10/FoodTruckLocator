CREATE TABLE FOODTRUCK
	(
		camion 		VARCHAR(255) 	NOT NULL,
		truckid 	VARCHAR(255) 	NOT NULL,
		PRIMARY KEY (truckid)
	)
;

CREATE TABLE POINTDEVENTE
	(
		truckid 	VARCHAR(255) 	NOT NULL,
		lieu 		VARCHAR(255) 	NOT NULL,
		longitude 	VARCHAR(255) 	NOT NULL,
		latitude 	VARCHAR(255) 	NOT NULL,
		jour 		DATE 			NOT NULL,
		heure_debut DATE 			NOT NULL,
		heure_fin 	DATE 			NOT NULL,
		PRIMARY KEY (truckid, heure_debut, jour, lieu),
		FOREIGN KEY (truckid) REFERENCES FOODTRUCK(truckid) ON DELETE CASCADE
	)
;