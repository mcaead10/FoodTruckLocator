SELECT ST_Distance( a.geog, ST_GeographyFromText('POINT(-73.581985 45.543213)')  ) from bixi a ;

SELECT * from bixi a  where  ST_Distance( a.geog, ST_GeographyFromText('POINT(-73.568344 45.50358)')  ) <= 200 ;






INSERT INTO bixi(id, name, geog, ouvert, veloDisponible, emplacementDisponible) VALUES (1,place,ST_GeographyFromText('POINT(-73.568344 45.50358)'),t,8,14) ON CONFLICT(id) DO UPDATE SET velodisponible = EXCLUDED.velodisponible DO UPDATE SET emplacementdisponible = EXCLUDED.emplacementdisponible;