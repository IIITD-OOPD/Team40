package oopd.Team40.servlet;



import java.io.IOException;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import oopd.Team40.model.Circuit;
import oopd.Team40.model.Drawing;
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
		
		Circuit circuit = (Circuit)request.getSession().getAttribute("circuit");
		HashMap<String,Integer> id = new HashMap<String,Integer>();
		String[] inputs = null;
		int count=0,flag;
		inputs = request.getParameterValues("id");
		if(inputs!= null)
        {   
			List<String> list =  Arrays.asList(inputs);
			flag=0;
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
        }
		else
		{
			for(String key : circuit.getInputs().keySet())
			{
				circuit.getInputs().put(key,0);
		    }
		}
		
		id.putAll(circuit.getInputs());
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
		    			int k=0;
		    			boolean output=false;
		    			boolean[] gateInput = new boolean[2];
		    			for(String key :input.keySet())
					    {
		    				if(id.containsKey(key))
					    	{
					    		if(id.get(key)==0)
					    		{
					    			gateInput[k]=false;
					    		}
					    		else
					    		{
					    			gateInput[k]=true;
					    		}
					    		k++;
					    	}
					    }
		    				
		    			if(type.equals("and"))
		    				output = gateInput[0] & gateInput[1]; 
		    			else
		    				if(type.equals("or"))
			    				output = gateInput[0] | gateInput[1]; 
		    			else
			    			if(type.equals("nand"))
				    			output = !(gateInput[0] & gateInput[1]); 
			    		else
		    				if(type.equals("nor"))
				    			output = !(gateInput[0] | gateInput[1]); 
		    			else
		    				if(type.equals("not"))
				    			output = !gateInput[0]; 
		    			else
		    				if(type.equals("xor"))
				    			output = gateInput[0] ^ gateInput[1]; 
		    			else
		    				if(type.equals("xnor"))
				    			output = !(gateInput[0] ^ gateInput[1]); 
		    					
		    					System.out.println(gateInput[0]);
		    					System.out.println(gateInput[1]);
		    					System.out.println(output);
		    			count++;
		    			if(output==true)
		    				id.put("G"+gate.getId(), 1);
		    			else
		    				id.put("G"+gate.getId(), 0);	
		    		}
		    	}
			}
		    //	System.out.println(id);
		}
		    
		int noOfOutput = circuit.getOutputs().size();
		int circuitOutput[] = new int[noOfOutput];
		int k=0;
		for(String key :circuit.getOutputs().keySet())
		{   
			if(id.containsKey(key))
    		{
				circuitOutput[k]= id.get(key);
		        k++;
    		}
    	}
	/*	for(int i=0;i< noOfOutput;i++)
		{
		   System.out.println(circuitOutput[i]+"i am output");
		}	
	*/	    
		String[] stringNums = new String[circuitOutput.length];
		int i = 0;
		while (i < circuitOutput.length) 
		{
			stringNums[i] = String.valueOf(circuitOutput[i++]);
		}
		List<String> outputList =  Arrays.asList(stringNums);
		request.setAttribute("list",outputList);
		Drawing draw = new Drawing();
		TreeMap<Integer,String> poss= draw.Draw(circuit);
		HashMap<String,String> gates = draw.getGatetype();
		request.setAttribute("gates",gates);
		request.setAttribute("possition",poss);
		request.getRequestDispatcher("/circuit.jsp").forward(request, response);
	}
}

