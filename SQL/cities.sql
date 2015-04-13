CREATE OR REPLACE FUNCTION "SA_cities"(
     lati double precision,
    longi double precision,
    tol double precision)
  RETURNS TABLE(cities text, distance double precision) AS
$BODY$
BEGIN
RETURN QUERY SELECT name,ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) FROM planet_osm_point 
	     WHERE ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) <= tol
	     AND place != ''
	     ORDER BY ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way);
END

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION "SA_cities"(double precision, double precision, double precision)
  OWNER TO gisuser;
