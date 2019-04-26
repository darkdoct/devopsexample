package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	// Private Constructor to enforce usage of factory method
	//private ConnectionFactory() {}
	
	public static Connection getConnection() {
	    try {
	        // This line is what did the trick for me locally
	        Class.forName("oracle.jdbc.OracleDriver");
	        return DriverManager.getConnection(System.getenv("JDBC_URL"), System.getenv("JDBC_USERNAME"), System.getenv("JDBC_PASSWORD"));
	    } catch (SQLException e) {
	        System.err.println("SQL State: " + e.getSQLState());
	        System.err.println("Error Code: " + e.getErrorCode());
	        System.err.println("Reason: " + e.getMessage());
	        throw new RuntimeException("Failed to obtain database connection");
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("Failed to load JDBC Driver");
	    }
	}
}
