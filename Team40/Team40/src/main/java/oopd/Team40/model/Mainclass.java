package oopd.Team40.model;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oopd.Team40.data.DBHelper;

public class Mainclass implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
	
        DBHelper dbhelper = DBHelper.getInstance();
		dbhelper.createDatabase("digitalCircuits");
		dbhelper.createCircuitsTable(); 
		
	}	
}
