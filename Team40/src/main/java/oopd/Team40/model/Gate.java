package oopd.Team40.model;

import java.util.HashMap;

public class Gate 
{
	private String id;
	private String type;
	private HashMap<String,Integer> input = new HashMap<String,Integer>();
	private int output;
	
	public Gate(String id, String type, HashMap<String, Integer> input, int output) 
	{
		this.id = id;
		this.type = type;
		this.input = input;
		this.output = output;
	}
	
	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public HashMap<String, Integer> getInput() {
		return input;
	}

	public int getOutput() {
		return output;
	}

	public void print()
	{
		System.out.println(id);
		System.out.println(type);
		System.out.println(input);
		System.out.println(output);
	}
}
