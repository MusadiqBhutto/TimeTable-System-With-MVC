package com.mbhutto.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class Connect
{
	protected static Connection conn;
	public static Connection getConnection() throws SQLException, ClassNotFoundException  //Method that connect 2 database
	{
		if(conn == null)
		{
			// Load the Oracle JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connect to the database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_time_table_system","root","Test!234%");		}
		return conn ;
	}
}