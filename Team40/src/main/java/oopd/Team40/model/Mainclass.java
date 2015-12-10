package oopd.Team40.model;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oopd.Team40.data.DBHelper;

public class Mainclass implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("hello");
        DBHelper dbhelper = new DBHelper("root", "root");
		dbhelper.createDatabase("digitalCircuits");
		dbhelper.createCircuitsTable();
		//System.out.println( dbhelper.getDbname());
		
		
	}

	

	
}
