CREATE OR REPLACE FUNCTION "SA_dist"(
    lati double precision,
    longi double precision,
    tol double precision)
  RETURNS TABLE(road text,distance double precision) AS
$$
BEGIN

RETURN QUERY SELECT name,ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) FROM planet_osm_roads 
	     WHERE ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way) <= tol
	     ORDER BY ST_Distance(ST_SetSRID(ST_MakePoint(longi,lati),4326),way);
END

$$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION "SA_dist"(double precision, double precision,double precision)
  OWNER TO gisuser;