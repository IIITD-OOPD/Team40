package oopd.Team40.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import oopd.Team40.model.Circuit;
import oopd.Team40.model.Gate;

public class DBHelper {

	private static String user;
	private static String pwd;
	private String dbname;
	private static DBHelper dao = new DBHelper("root","root");
	
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
     

	public DBHelper(String user, String pwd) 
	{
		this.user = user;
		this.pwd = pwd;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}

	}

	public boolean createDatabase(String dbname) 
	{
		this.dbname = dbname;
		Connection con = null;
		try 
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/",user, pwd);
			Statement stmt = con.createStatement();
			stmt.execute(" Create database if not exists " + dbname);
			con.close();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			try 
			{
				con.close();
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			return false;
		}
	}

	public void destroyDatabase() 
	{
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, pwd);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(" DROP database " + dbname);
			con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}

	public Connection getConnection()
	{
		Connection con = null;
		try 
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ dbname, user, pwd);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}

	public boolean createCircuitsTable()
	{
		Statement stmt;
		try 
		{
			Connection connection = getConnection();
			stmt = connection.createStatement();
			String sql = "CREATE TABLE if not exists circuits (c_id Integer AUTO_INCREMENT,c_name varchar(25),PRIMARY KEY (c_id))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE if not exists Inputs (c_id Integer,I_id varchar(25),PRIMARY KEY (c_id,I_id))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE if not exists Outputs (c_id Integer,O_id varchar(25),PRIMARY KEY (c_id,O_id))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE if not exists Gates (c_id Integer,G_id varchar(25),type varchar(25),Input_1 varchar(25),Input_2 varchar(25),PRIMARY KEY (c_id,G_id))";
			stmt.executeUpdate(sql);
			
			connection.close();
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public void addCircuit(Circuit circuit) throws SQLException 
	{
		Connection connection = getConnection();
		if(circuit.getName()!=null)
		{
			Statement stmt = connection.createStatement();
			String sql = "insert into circuits (c_name) values (?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,circuit.getName());
			ps.execute();
			String name = circuit.getName();
			sql = "SELECT * FROM circuits where c_name = '"+name+"' ";
			ResultSet rs= stmt.executeQuery(sql);
			int id = 0 ;
	    	while(rs.next())
	    	{ 
	    		id = rs.getInt("c_id");
	    	}
	    	System.out.println(id);
			HashMap<String,Integer> hash = new HashMap<String,Integer>();
			hash = circuit.getInputs();
			Set<String> keys = hash.keySet();
			for(String key : keys)
			{
				sql = "insert into Inputs (c_id,I_id) values (?,?)";
				ps = connection.prepareStatement(sql);
			    ps.setInt(1,id);
			    ps.setString(2,key);
				ps.execute();
				
			}
		
			hash = circuit.getOutputs();
			keys = hash.keySet();
			for(String key : keys)
			{
				sql = "insert into Outputs (c_id,O_id) values (?,?)";
				ps = connection.prepareStatement(sql);
			    ps.setInt(1,id);
			    ps.setString(2,key);
				ps.execute();
			}
		
			ArrayList<Gate> gates= new ArrayList<Gate>();
			gates=circuit.getGates();
			if(gates.size()>0)
			{
				for(Gate gate:gates)
				{   
					sql = "insert into Gates (c_id,G_id,type,Input_1,Input_2) values (?,?,?,?,?)";
					ps = connection.prepareStatement(sql);
				    ps.setInt(1,id);
				    ps.setString(2,gate.getId());
				    ps.setString(3,gate.getType());
				    HashMap<String,Integer> input = new HashMap<String,Integer>();
				    input = gate.getInput();
				    System.out.println(input+"this is gate input type");
				    keys = input.keySet();
				    
				    String str[] = new String[2];
				    int i=0;
				    for(String key : keys)
					{  
				    	str[i]=key;
				    	i++;
					}
				    ps.setString(4,str[0]);
				    ps.setString(5,str[1]);
				    ps.execute();
				}
			}
		}
	}

	public List<String> allCircuit() throws ClassNotFoundException, SQLException 
	{
		Connection connection = getConnection();
		List<String> sList = new ArrayList<String>();
		Statement stmt = connection.createStatement();
		String sql = "SELECT c_name FROM circuits";
		ResultSet rs= stmt.executeQuery(sql);
		if(!rs.next())
		{
			System.out.println("Not Found");
		}
		else
		{
			while(rs.next())
			{ 
				sList.add(rs.getString("c_name"));
			}
		}
		connection.close();
		return sList;
	}

	public String getDbname() {
		return dbname;
	}

    public Circuit getCircuit(String circuitName) throws SQLException 
    {	
		Circuit circuit = new Circuit(circuitName);
		Connection connection = getConnection();
		Statement stmt = connection.createStatement();
		
		String sql = "SELECT c_id FROM circuits where c_name='"+circuitName+"' ";
		ResultSet rs= stmt.executeQuery(sql);
		int circuitId = 0;
		while(rs.next())
		{ 
			circuitId = rs.getInt("c_id");
			break;
		}
		
		sql = "SELECT I_id FROM inputs where c_id='"+circuitId+"' ";
		rs= stmt.executeQuery(sql);
		while(rs.next())
		{
			circuit.getInputs().put(rs.getString("I_id"),-1);
		}
		
		sql = "SELECT O_id FROM outputs where c_id='"+circuitId+"' ";
		rs= stmt.executeQuery(sql);
		while(rs.next())
		{
			circuit.getOutputs().put(rs.getString("O_id"),-1);
		}
		int i=0;
		sql = "SELECT * FROM gates where c_id='"+circuitId+"' ";
		rs= stmt.executeQuery(sql);
		while(rs.next())
		{   HashMap<String,Integer> input = new HashMap<String, Integer>();
		    input.put(rs.getString("Input_1"),-1);
		    input.put(rs.getString("Input_2"),-1);
		    Gate g = new Gate(rs.getString("G_id"),rs.getString("type"), input,-1);
			circuit.getGates().add(g);
		}
		
		return circuit;
		
	}


}
