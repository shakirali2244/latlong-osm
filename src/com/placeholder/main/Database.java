package com.placeholder.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	static String host = "localhost";
	static String port = "5432";
	static String db = "gis";
	static String user = "postgres";
	public static void DriverRegistration() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver .jar not found");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
	}
	public static Connection create(){
		//String url = "?"+user;
		//String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
		Connection conn = null;
		try {
			 conn = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db,user,"");
		} catch (SQLException e) {
			DriverRegistration();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection c){
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement stmt){
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getRoads(double lati, double longi){
		Connection c = Database.create();
		Statement stmt = null;
		String response ="null";
		try {
			stmt = c.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		
		String sql = "SELECT * FROM \"SA_roads\"(cast("+lati+" as double precision),cast("+longi+" as double precision),cast(0.1 as double precision));";
		System.out.println(sql);
	    try {
			 rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		try {
			if(rs.next()){
			response = rs.getString("roads");
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeStatement(stmt);
		closeConnection(c);		
		return response;
		
	}
    public static String getHouses(double lati, double longi){
    	Connection c = Database.create();
    	Statement stmt = null;
    	String response = "null";
    	try {
    		stmt = c.createStatement();
    	} catch (SQLException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	ResultSet rs = null;
    	
    	String sql = "SELECT * FROM \"SA_houses\"(cast("+lati+" as double precision),cast("+longi+" as double precision),cast(0.1 as double precision));";
    	System.out.println(sql);
        try {
    		 rs = stmt.executeQuery(sql);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    	try {
    		if(rs.next()){
    		response = rs.getString("houses");
    	    }
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	closeStatement(stmt);
		closeConnection(c);	
    	return response;
	}
    public static String getCities(double lati, double longi){
    	Connection c = Database.create();
    	Statement stmt = null;
    	String response = "null";
    	try {
    		stmt = c.createStatement();
    	} catch (SQLException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	ResultSet rs = null;
    	
    	String sql = "SELECT * FROM \"SA_cities\"(cast("+lati+" as double precision),cast("+longi+" as double precision),cast(0.1 as double precision));";
    	System.out.println(sql);
        try {
    		 rs = stmt.executeQuery(sql);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    	try {
    		if(rs.next()){
    		response = rs.getString("cities");
    	    }
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	closeStatement(stmt);
		closeConnection(c);	
    	return response;
	}


}
