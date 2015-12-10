package oopd.Team40.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Circuit 
{
	String name;
	HashMap<String,Integer> inputs = new HashMap<String,Integer>();
	HashMap<String,Integer> outputs = new HashMap<String,Integer>();
	ArrayList<Gate> gates = new ArrayList<Gate>();
	
	
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
				inputs.put("I"+node.getAttributes().getNamedItem("id"),-1);	
			}	
			
			NodeList gateList = doc.getElementsByTagName("gate");
			String id;
			String type;
			HashMap<String,Integer> gateInput = new HashMap<String,Integer>();
			int output;
			for(int i=0;i<gateList.getLength();i++)
			{
				Node gateNode = gateList.item(i);
				id=gateNode.getAttributes().getNamedItem("id").getNodeValue();
				type=gateNode.getAttributes().getNamedItem("type").getNodeValue();
				NodeList gateInputList = gateNode.getChildNodes(); 
				for(int j=0;j<gateInputList.getLength();j++)
				{
					Node gateInputNode = gateInputList.item(j);
					if(gateInputNode.getAttributes().getNamedItem("type").getNodeValue()=="input")
					{
						gateInput.put("I"+gateInputNode.getAttributes().getNamedItem("id").getNodeValue(),-1);
					}
					else
					if(gateInputNode.getAttributes().getNamedItem("type").getNodeValue()=="gate")
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
				Node outputNode = outputList.item(i);
				NodeList outputInputList = outputNode.getChildNodes(); 
				for(int j=0;j<outputInputList.getLength();j++)
				{
					Node outputInputNode = outputInputList.item(j);
					if(outputInputNode.getAttributes().getNamedItem("type").getNodeValue()=="input")
					{
						outputs.put(outputNode.getAttributes().getNamedItem("id").getNodeValue()+"I"+outputInputNode.getAttributes().getNamedItem("id").getNodeValue(),-1);
					}
					else
					if(outputInputNode.getAttributes().getNamedItem("type").getNodeValue()=="gate")
				    {
						outputs.put(outputNode.getAttributes().getNamedItem("id").getNodeValue()+"G"+outputInputNode.getAttributes().getNamedItem("id").getNodeValue(),-1);
					}
				}
			}	
			
		  } 
		
		catch (Exception e) {
			e.printStackTrace();
		 }
	}
}

/*
NodeList nodesList = doc.getDocumentElement().getChildNodes(); 


for(int i=0;i<nodesList.getLength();i++)
{
	Node node = nodesList.item(i);
	if(node.getNodeName().equals("input"))
	{		
		noOfInputs++;
	}
	if(node.getNodeName().equals("output"))
	{
		noOfOutputs++;
	}
	if(node.getNodeName().equals("gate"))
	{
		noOfGates++;
	}
}
System.out.println(noOfInputs +" "+noOfOutputs+" "+noOfGates);

int gate[]=new int[noOfGates];
int output[]=new int[noOfOutputs];

for(int i=0;i<nodesList.getLength();i++)
{
	Node node = nodesList.item(i);
	if(node.getNodeName().equals("gate"))
	{
		if(node.hasAttributes())
		{
		   NamedNodeMap nodeMap = node.getAttributes();
		   for(int j=0;j<nodeMap.getLength();j++)
		   {
			  Node attrNode = nodeMap.item(j);
			  if(attrNode.getNodeName().equals("type"))
				  if(attrNode.getNodeValue().equals("xor"))
				  {
					  NodeList inputList = node.getChildNodes();
					  for (int k = 0; k < inputList.getLength(); k++) 
					  {
						  Node gateInput = inputList.item(i);
						  if(gateInput.hasAttributes())
				    	  {
							  NamedNodeMap gateInputNodeMap = gateInput.getAttributes();
							  System.out.println(gateInputNodeMap.getNamedItem("type").getNodeValue());
							 for (int l = 0; l < gateInputNodeMap.getLength(); l++) 
							  {
								  Node gateInputAttrNode = gateInputNodeMap.item(l);
								  if(gateInputAttrNode.getNodeName().equals("type"))
								  {
									  if(gateInputAttrNode.getNodeValue().equals("gate"))
										  Gate[
								  }
							  }
				    	  }
					  }
				  }
		   }
		}
	}
}

*/