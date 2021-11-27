package com.Assignment.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class EventDAO {
	
	public String updateEventStatus(Map<String, Long> mapp) throws SQLException {
				Connection connection = null;
				Statement statement = null;
				ResultSet resultSet = null;
				String conUrl = "jdbc:mysql://localhost:3306/";
				String driverName = "com.mysql.jdbc.Driver";
				String databaseName = "Event";
				String usrName = "root";
				String usrPass = "root";
			try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(conUrl + databaseName,usrName, usrPass);
			statement = connection.createStatement();
			for(Map.Entry<String,Long> xx : mapp.entrySet()) {
				String updateQuery = "UPDATE EventDetail SET alert='TRUE' WHERE RollNo="+xx.getKey();
				statement.executeUpdate(updateQuery);
			}
	 } catch (ClassNotFoundException e) {
			}
			return "SUCESS";
	}

}
