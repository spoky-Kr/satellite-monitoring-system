package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class dbConnect {

	private Connection connection;
    

	dbConnect() throws ClassNotFoundException, SQLException {
    
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monsputnik", "root", "0000");
    }
	
	  public Connection getConnection() {
		  return connection;
	  }
	  
	  public String getDate(String num, String sqlQuery, String targetField ) throws SQLException {
	  String res="";
	  Statement statement=connection.createStatement();
	  ResultSet resultSet=statement.executeQuery(sqlQuery);
	  Integer id;
	  String tField;
	  while(resultSet.next()) {
		  id=resultSet.getInt("id_sputnik");
		  tField=resultSet.getString(targetField);
		  if(id.toString().equals(num)) {
			  res=tField;
		  }

	  }
	  return res;
}
	  
}
