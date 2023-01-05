package com.increff.employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;




public class EmployeeApi {
	
	public static final Logger logger = Logger.getLogger("HelloWorld.class");
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		//BasicConfigurator.configure();
		
		Properties props = new Properties();
		InputStream inStream = new FileInputStream("employee.properties");
		props.load(inStream);
		logger.warning("Working error");
		logger.info("Working info");
		Class.forName(props.getProperty("jdbc.driver"));  
		Connection con=DriverManager.getConnection
					(props.getProperty("jdbc.url"),props.getProperty("jdbc.username"),props.getProperty("jdbc.password")); 
		insert(con);
		select(con);
		delete(con);
		con.close();
		
	}
	
	private static void insert(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		logger.info("Inserting employee");
		for(int i = 0; i < 3; i++) {
			int id = i;
			String name = "name " + i;
			int age = 20 + i;
			String querry = "insert into employee values("+ id + ", '" + name + "' ," + age + ")";
			//logger.info(querry);
			stmt.executeUpdate(querry);
		}
	}
	
	private static void select(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		logger.info("Selecting employee");
		ResultSet rs = stmt.executeQuery("select * from employee");
		while(rs.next()) {
			logger.info(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));  
		}
	}

	private static void delete(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		logger.info("Deleting employee");
		ResultSet rs = stmt.executeQuery("select * from employee");
		List<Integer> idList = new ArrayList<Integer>();
		while(rs.next()) {
			idList.add(rs.getInt(1));
		}
		for(int i = 0; i < idList.size(); i++) {
			String querry = "delete from employee where id = " + idList.get(i);
			//logger.info(querry);
			stmt.executeUpdate(querry);
		}
	}
}
