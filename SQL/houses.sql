CREATE OR REPLACE FUNCTION "SA_houses"(
     lati double precision,
    longi double precision,
    tol double precision)
  RETURNS TABLE(houses text, distance double precision) AS
$BODY$
BEGIN
RETURN QUERY SELECT "addr:housenumber",ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) FROM planet_osm_point 
	     WHERE ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) <= tol
	     ORDER BY ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way);
END

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION "SA_houses"(double precision, double precision, double precision)
  OWNER TO gisuser;