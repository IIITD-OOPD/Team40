package oopd.Team40.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oopd.Team40.data.DBHelper;
import oopd.Team40.model.Circuit;
import oopd.Team40.model.Drawing;

public class CircuitChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CircuitChoose() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] circuitName = null ;
		Drawing draw = new Drawing();
		circuitName = request.getParameterValues("circuit_name");
		DBHelper dbhelp = DBHelper.getInstance();
		int size=0;
		Circuit circuit = new Circuit();
		try 
		{
			circuit = dbhelp.getCircuit(circuitName[0]);
		} 
		catch (SQLException e) 
		{		
			e.printStackTrace();
		}
		
		
		size = circuit.getInputs().size();
	    TreeMap<Integer,String> poss= draw.Draw(circuit);
		HashMap<String,String> gates = draw.getGatetype();
		request.getSession().setAttribute("size",size);
		request.setAttribute("gates",gates);
	    request.setAttribute("possition",poss);
		request.getSession().setAttribute("circuit",circuit);
		request.getRequestDispatcher("/circuit.jsp").forward(request, response);
		
	}

}
