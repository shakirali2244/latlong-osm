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

@Path("/todo")
public class Main {
  // This method is called if XMLis request
 
    
  // This can be used to test the integration with the browser
  @GET
  public static String getHTML(@QueryParam("latitude") String lati,
		  						@QueryParam("longitude") String longi) {
	Connection c = Database.create();
	Statement stmt = null;
	try {
		stmt = c.createStatement();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	ResultSet rs = null;
	
	
	String sql = "SELECT * FROM \"SA_dist\"("+lati+","+longi+");";
	System.out.println(sql);
    try {
		 rs = stmt.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    String response ="sadas";
    ResultSetMetaData rsmd = null;
	try {
		rsmd = rs.getMetaData();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    try {
		PrintColumnTypes.printColTypes(rsmd);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    System.out.println("");

    int numberOfColumns = 0;
	try {
		numberOfColumns = rsmd.getColumnCount();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    for (int i = 1; i <= numberOfColumns; i++) {
      if (i > 1) System.out.print(",  ");
      String columnName = null;
	try {
		columnName = rsmd.getColumnName(i);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      System.out.print(columnName);
    }
    System.out.println("");

    try {
		while (rs.next()) {
		  for (int i = 1; i <= numberOfColumns; i++) {
		    if (i > 1) System.out.print(",  ");
		    String columnValue = rs.getString(i);
		    System.out.print(columnValue);
		  }
		  System.out.println("");  
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return response;
  }
  @Path("/reg")
  @GET
  public static String reg(){
	  Database.DriverRegistration();
	  return "done";
  }


} 
class PrintColumnTypes  {

	  public static void printColTypes(ResultSetMetaData rsmd)
	                            throws SQLException {
	    int columns = rsmd.getColumnCount();
	    for (int i = 1; i <= columns; i++) {
	      int jdbcType = rsmd.getColumnType(i);
	      String name = rsmd.getColumnTypeName(i);
	      System.out.print("Column " + i + " is JDBC type " + jdbcType);
	      System.out.println(", which the DBMS calls " + name);
	    }
	  }
	}