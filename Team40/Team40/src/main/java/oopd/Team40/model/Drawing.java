package oopd.Team40.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Drawing {
    HashMap<String,String> gatetype = new HashMap<String, String>();
    
    		
	public HashMap<String, String> getGatetype() {
		return gatetype;
	}


	public TreeMap<Integer,String> Draw(Circuit circuit) {
	
    	HashMap<String,Integer> id = new HashMap<String,Integer>();
    	TreeMap<Integer,String> possition = new TreeMap<Integer,String>();
    	HashMap<String,String> gatetype = new HashMap<String, String>();
        ArrayList<Integer> poss = new ArrayList<Integer>();
        
        
        poss.add(88);
        poss.add(328);
        poss.add(176);
        poss.add(416);
        poss.add(264);
        poss.add(504);
        int p=0;
	    
    	id.putAll(circuit.getInputs());
		
    	
	    int count=0,flag;
	    System.out.println(circuit.getGates().size()+"size");
	    while(count!=circuit.getGates().size())
	    {
	    	System.out.println(count+"count");
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
	    					count++;
	    					if(output==true)
	    						id.put("G"+gate.getId(), 1);
	    					else
	    						id.put("G"+gate.getId(), 0);	
	    					
	    					possition.put(poss.get(p),gate.getId());
	    					gatetype.put(gate.getId(),gate.getType());
	    					p++;
	    			}
	    		}
		    }
	    	System.out.println(id);
	    	System.out.println(possition+"possition print");
	    	
	    }
	   this.gatetype = gatetype; 
	   return possition;
	    }
	
	
}
