import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**Copyright (c) Mar 17, 2019 Georgi Ditsov to Present.
All rights reserved.
*/

public class Requests {
	//private static String driver = "com.mysql.jdbc.Driver";
	private static String host = "jdbc:mysql://localhost:3306/management";
	private static Connection connect;
	private String username;
	private String password;
	boolean isLogged;
	
	Requests(String username, String password){
		this.username = username;
		this.password = password;
		try {
			connect = DriverManager.getConnection(host, this.username, this.password);
			System.out.println("Verified user");
			this.isLogged = true;
		}catch (SQLException e){
			this.isLogged = false;
		}
	}
}
