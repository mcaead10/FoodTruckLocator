SELECT ST_Distance( a.geog, ST_GeographyFromText('POINT(-73.581985 45.543213)')  ) from bixi a ;

SELECT * from bixi a  where  ST_Distance( a.geog, ST_GeographyFromText('POINT(-73.568344 45.50358)')  ) <= 200 ;