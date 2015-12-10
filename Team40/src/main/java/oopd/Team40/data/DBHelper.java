package oopd.Team40.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

	
	private static String user;
	private static String pwd;
	private String dbname;
	private static DBHelper dao;
	
	public static DBHelper getInstance()
	{
		if(dao == null)
		{   
			dao = new DBHelper(user,pwd);
			return dao;
		}
			
		else 
		{
			return dao;
		}
	}
     

	public DBHelper(String user, String pwd) {
		this.user = user;
		this.pwd = pwd;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public boolean createDatabase(String dbname) {
		this.dbname = dbname;
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/",user, pwd);
			Statement stmt = con.createStatement();
			stmt.execute("drop database if exists " + dbname);
			stmt = con.createStatement();
			stmt.execute(" Create database " + dbname);
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	public void destroyDatabase() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, pwd);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(" DROP database " + dbname);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ dbname, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public boolean createCircuitsTable() {
		Statement stmt;
		try {
			Connection connection = getConnection();
			stmt = connection.createStatement();
			String sql = "CREATE TABLE circuits (name varchar(25))";
			stmt.executeUpdate(sql);
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getDbname() {
		return dbname;
	}
	
	

}
