-- Function: "SA_dist"(double precision, double precision, double precision)

-- DROP FUNCTION "SA_dist"(double precision, double precision, double precision);

CREATE OR REPLACE FUNCTION "SA_roads"(
     lati double precision,
    longi double precision,
    tol double precision)
  RETURNS TABLE(roads text, distance double precision) AS
$BODY$
BEGIN
RETURN QUERY SELECT name,ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) FROM planet_osm_roads 
	     WHERE ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) <= tol
	     ORDER BY ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way);
END

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION "SA_roads"(double precision, double precision, double precision)
  OWNER TO gisuser;
