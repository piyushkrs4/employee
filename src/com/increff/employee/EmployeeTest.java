package com.increff.employee;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

public class EmployeeTest {
	@Test
	public void sayHello() throws ClassNotFoundException, SQLException, IOException {
		//System.out.println("Hello!");
		EmployeeApi.main(new String[0]);
		
	}
	
}
