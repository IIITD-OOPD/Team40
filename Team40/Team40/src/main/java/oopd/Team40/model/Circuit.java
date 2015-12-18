package oopd.Team40.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Circuit 
{
	private String name;
	private HashMap<String,Integer> inputs = new HashMap<String,Integer>();
	private HashMap<String,Integer> outputs = new HashMap<String,Integer>();
	private ArrayList<Gate> gates = new ArrayList<Gate>();
    
	
	public String getName() {
		return name;
	}


	public HashMap<String, Integer> getInputs() {
		return inputs;
	}


	public HashMap<String, Integer> getOutputs() {
		return outputs;
	}


	public ArrayList<Gate> getGates() {
		return gates;
	}


	public void print()
	{
		System.out.println(name);
		System.out.println(inputs);
		System.out.println(outputs);
		
		for(int i = 0; i < gates.size(); i++) {   
		    gates.get(i).print();
		} 
	}
	
	
	public void readXMLFile(String fileName) 
	{	
		try 
		{
			File xmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			name=doc.getDocumentElement().getAttribute("name");
			
			NodeList inputList = doc.getElementsByTagName("input");
			for(int i=0;i<inputList.getLength();i++)
			{
				Node node = inputList.item(i);
				inputs.put("I"+node.getAttributes().getNamedItem("id").getNodeValue(),-1);	
			}	
			
			NodeList gateList = doc.getElementsByTagName("gate");
			String id;
			String type;
			
			int output;
			
			for(int i=0;i<gateList.getLength();i++)
			{
				Element gateNode = (Element) gateList.item(i);
				HashMap<String,Integer> gateInput = new HashMap<String,Integer>();
				id=gateNode.getAttributes().getNamedItem("id").getNodeValue();
				type=gateNode.getAttributes().getNamedItem("type").getNodeValue();
				
				NodeList gateInputList = gateNode.getElementsByTagName("input-source"); 
				
				for(int j=0;j<gateInputList.getLength();j++)
				{
					Node gateInputNode = gateInputList.item(j);
					
					if(gateInputNode.getAttributes().getNamedItem("type").getNodeValue().equals("input"))
					{
						gateInput.put("I"+gateInputNode.getAttributes().getNamedItem("id").getNodeValue(),-1);
					}
					else
					if(gateInputNode.getAttributes().getNamedItem("type").getNodeValue().equals("gate"))
				    {
						gateInput.put("G"+gateInputNode.getAttributes().getNamedItem("id").getNodeValue(),-1);
					}
				}
				output=-1;
				Gate gate=new Gate(id,type,gateInput,output);
				gates.add(gate);
			}
			
			NodeList outputList = doc.getElementsByTagName("output");
			for(int i=0;i<outputList.getLength();i++)
			{
				Element outputNode = (Element) outputList.item(i);
				NodeList outputInputList = outputNode.getElementsByTagName("input-source"); 
				for(int j=0;j<outputInputList.getLength();j++)
				{
					Node outputInputNode = outputInputList.item(j);
					if(outputInputNode.getAttributes().getNamedItem("type").getNodeValue().equals("input"))
					{
						outputs.put("I"+outputInputNode.getAttributes().getNamedItem("id").getNodeValue(),-1);
					}
					else
					if(outputInputNode.getAttributes().getNamedItem("type").getNodeValue().equals("gate"))
				    {
						outputs.put("G"+outputInputNode.getAttributes().getNamedItem("id").getNodeValue(),-1);
					}
				}
			}	
			
		  } 
		
		catch (Exception e) {
			e.printStackTrace();
		 }
	}
}
//outputNode.getAttributes().getNamedItem("id").getNodeValue()+