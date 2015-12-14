package oopd.Team40.model;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;



public class CircuitHandler 
{
	public static void readXMLFile(String fileName) 
	{	
		int noOfInputs=0,noOfOutputs=0,noOfGates=0;
		int input[]={1,0,1};
		try 
		{
			File xmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
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
		    							/*  for (int l = 0; l < gateInputNodeMap.getLength(); l++) 
		    							  {
		    								  Node gateInputAttrNode = gateInputNodeMap.item(l);
		    								  if(gateInputAttrNode.getNodeName().equals("type"))
		    								  {
		    									  if(gateInputAttrNode.getNodeValue().equals("gate"))
		    										  Gate[
		    								  }
										  }*/
		    				    	  }
								  }
		    				  }
		    		   }
		    		}
				}
		    }
			
			
	
			
			//if()
				//node.setNodeValue(String.valueOf(A));
			
			
		/*	NodeList nodesList = doc.getElementsByTagName("input");
			
			for(int i=0;i<nodesList.getLength();i++)
		    {
				Node node = nodesList.item(i);
				if(node.hasAttributes())
	    		{
	    		   NamedNodeMap nodeMap = node.getAttributes();
	    		   for(int j=0;j<nodeMap.getLength();j++)
	    		   {
	    			  Node attrNode = nodeMap.item(j);
	    			  if(attrNode.getNodeName().equals("type"))
	    				  if(attrNode.getNodeValue()=;
	    		   }
	    		}
		    }
*/
			/*for (int i = 0; i < nList.getLength(); i++) 
			{
				Node nNode = nList.item(i);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode;
					Float.valueOf(eElement.getElementsByTagName("price").item(0).getTextContent())
				}
			}
            int j=0;
			for (int i = 50; i < 60; i++) 
			{
				Node nNode = nList.item(i);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode;
					currency[j].setCurrentPrice(Float.valueOf(eElement.getElementsByTagName("price").item(0).getTextContent()));
					j++;				
				}
			}*/
			
		  } 
		
		catch (Exception e) {
			e.printStackTrace();
		 }
	}

}
