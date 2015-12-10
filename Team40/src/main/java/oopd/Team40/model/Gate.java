package oopd.Team40.model;

import java.util.HashMap;

public class Gate 
{
	String id;
	String type;
	HashMap<String,Integer> input = new HashMap<String,Integer>();
	int output;
	
	public Gate(String id, String type, HashMap<String, Integer> input, int output) 
	{
		this.id = id;
		this.type = type;
		this.input = input;
		this.output = output;
	}
}
