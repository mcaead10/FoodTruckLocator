CREATE TABLE BIXI
(
  id        		    INTEGER      NOT NULL,
  name       	        VARCHAR(255) NOT NULL,
  longitude 		    FLOAT        NOT NULL,
  latitude  		    FLOAT        NOT NULL,
  ouvert    		    BOOLEAN      NOT NULL,
  veloDisponible        INTEGER      NOT NULL,
  emplacementDisponible INTEGER      NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE ANCRAGEVELO
(
  longitude FLOAT        NOT NULL,
  latitude  FLOAT        NOT NULL,
  PRIMARY KEY (longitude, latitude)
);

CREATE TABLE FOODTRUCK
(
  camion  VARCHAR(255) NOT NULL,
  truckid VARCHAR(255) NOT NULL,
  PRIMARY KEY (truckid)
);


CREATE TABLE POINTDEVENTE
(
  truckid     VARCHAR(255) NOT NULL,
  lieu        VARCHAR(255) NOT NULL,
  longitude   FLOAT        NOT NULL,
  latitude    FLOAT        NOT NULL,
  heure_debut TIMESTAMPTZ  NOT NULL,
  heure_fin   TIMESTAMPTZ  NOT NULL,
  PRIMARY KEY (truckid, heure_debut, lieu),
  FOREIGN KEY (truckid) REFERENCES FOODTRUCK (truckid) ON DELETE CASCADE
);

-- select Distinct foodtruck.camion, pointdevente.lieu from foodtruck, pointdevente where pointdevente.lieu like 'Maisonneuve Rosemont';