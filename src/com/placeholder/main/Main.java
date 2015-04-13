package com.placeholder.main;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/geocode")
public class Main {
  // This method is called if XMLis request
 
    
  // This can be used to test the integration with the browser
  @GET
  public static Response getAddr(@QueryParam("latitude") String lati,
		  						@QueryParam("longitude") String longi) {
	  double d_lati = 0.0;
	  double d_longi = 0.0;
	  JSONObject obj = new JSONObject();
	  try{
		  d_lati = Double.parseDouble(lati);
		  d_longi = Double.parseDouble(longi);
	  }catch(Exception e){
		  return Response.status(Status.NOT_ACCEPTABLE).build();
	  }
	  try {
		obj.put("house",Database.getHouses(d_lati,d_longi));
		obj.put("road",Database.getRoads(d_lati,d_longi));
		obj.put("city",Database.getCities(d_lati,d_longi));
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return Response.ok(obj.toString()).build();
  }
  @Path("/reg")
  @GET
  public static String reg(){
	  Database.DriverRegistration();
	  return "done";
  }
} 
