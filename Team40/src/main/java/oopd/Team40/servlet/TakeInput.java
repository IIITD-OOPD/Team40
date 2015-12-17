package oopd.Team40.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oopd.Team40.model.Circuit;
import oopd.Team40.model.Gate;

public class TakeInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TakeInput() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] inputs = request.getParameterValues("id");
        List list =  Arrays.asList(inputs);
		//System.out.println(list.size());
	    Circuit circuit = (Circuit)request.getSession().getAttribute("circuit");
	  //  System.out.println(circuit.getName()+ "I am in input");
		
	       
	    	int flag=0;
	    	for(String key : circuit.getInputs().keySet())
	    	{
	    		flag=0;
	    		for(int i=0; i<list.size();i++)
		    	{
		    	    if(key.equals(list.get(i)))
		    	    {
		    	        flag=1;
		    	    	circuit.getInputs().put(key,1);
		    	    	break;
		    	    }
		    	}
	    		if(flag==0)
	    			circuit.getInputs().put(key,0);
	    	}
	    	
	    	
	    	//	System.out.println(circuit.getInputs());
	    	
	    	
	    	// System.out.println(list.get(j));
	    	HashMap<String,Integer> id = new HashMap<String,Integer>();
	    	id.putAll(circuit.getInputs());
    		System.out.println(id+"yes");
	    	
		    int count=0;
		    while(count!=circuit.getGates().size())
		    {
		    	for(int j=0;j<circuit.getGates().size();j++)
			    {
		    		Gate gate=circuit.getGates().get(j);
		    		if(!id.containsKey("G"+gate.getId()))
		    		{
		    			flag=0;
		    			HashMap<String, Integer> input=gate.getInput();
		    			for(String key : input.keySet())
				    	{
		    				if(!id.containsKey(key))
		    				{	
		    					flag=1;
		    					break;
		    				}
				    	}
		    			if(flag==0)
		    			{
		    				String type = gate.getType();
		    				
		    			}
		    		}
			    }
		    }
		 /*    for(int j=0;j<circuit.getGates().size();j++)
		    {   
		    	Gate gate=circuit.getGates().get(j);
		    	HashMap<String, Integer> input=gate.getInput();
		    	for(String key : input.keySet())
		    	{
		    		if(key.contains("I"))
		    		{
			    			for(String key1 :circuit.getInputs().keySet())
				    		{
				    			if(key.equals(key1))
				    			{
				    				gate.getInput().put(key,circuit.getInputs().get(key1));
				    			}
				    		}
		    			    
		    		}
		    	}
		    	   
		    //	System.out.println(gate.getInput());
		    } */
	}
}
