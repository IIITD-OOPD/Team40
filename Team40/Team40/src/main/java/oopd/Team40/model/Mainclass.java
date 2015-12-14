package oopd.Team40.model;

import java.sql.SQLException;

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
	
        DBHelper dbhelper = DBHelper.getInstance();
		dbhelper.createDatabase("digitalCircuits");
		dbhelper.createCircuitsTable(); 
		
	}	
}
