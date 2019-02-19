package com.javalearning.DBConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class DBConnectionTest {

	public static void main(String[] args) throws SQLException {
		
		String sql = "SELECT * FROM STUDENT WHERE ROLLNUM = "+1;
		
		OracleDataSource ds = new OracleDataSource();
		ds.setURL("jdbc:oracle:thin:Panna123/Panna123@localhost:1521/DMDB81");
		
		
		Connection con = ds.getConnection();
		//DatabaseMetaData metadata = con.getMetaData();		
		//System.out.println("JDBC Driver version is :"+ metadata.getDriverVersion());
		
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		/*boolean flag = stmt.execute("insert into STUDENT values(4, 'Vimal', 'Mishra', 32, 'Bangalore')");
		
		if(flag)
			System.out.println("insert is done");
		else
			System.out.println("insertion failed");*/
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			int rollnum = rs.getInt("ROLLNUM");
			String firstName = rs.getString("FIRSTNAME");
			String lastName = rs.getString("LASTNAME");
			int age = rs.getInt("AGE");
			String add = rs.getString("ADDRES");
			
			System.out.println("Roll number :: "+rollnum);
			System.out.println("First Name :: "+firstName);
			System.out.println("Last Name :: "+lastName);
			System.out.println("Age :: "+age);
			System.out.println("Address :: "+add);
		}
		
	}

}
