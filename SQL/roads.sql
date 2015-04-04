-- Function: "SA_dist"(double precision, double precision, double precision)

-- DROP FUNCTION "SA_dist"(double precision, double precision, double precision);

CREATE OR REPLACE FUNCTION "SA_dist"(
     lati double precision,
    longi double precision)
  RETURNS TABLE(road text, distance double precision) AS
$BODY$
BEGIN
RETURN QUERY SELECT name,ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) FROM planet_osm_roads 
	     WHERE ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) <= 0.1
	     ORDER BY ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way);
END

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION "SA_dist"(double precision, double precision)
  OWNER TO gisuser;
