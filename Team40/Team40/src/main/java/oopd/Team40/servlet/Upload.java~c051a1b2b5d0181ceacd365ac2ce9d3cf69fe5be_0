package oopd.Team40.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import oopd.Team40.data.DBHelper;
import oopd.Team40.model.Circuit;
import oopd.Team40.model.Drawing;

public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String DIRECTORY = "";
	
    public Upload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Circuit circuit=new Circuit();
		DBHelper dbhelp = DBHelper.getInstance();
		Drawing draw = new Drawing();
	    ServletContext servletContext = getServletContext();
	    String contextPath = servletContext.getRealPath(File.separator);
	    this.DIRECTORY = contextPath;
	    String name = null;
	    int size = 0;
	    
        if(ServletFileUpload.isMultipartContent(request))
	    {
            try {
                 	List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                 	for(FileItem item : multiparts)
                 	{
                 		if(!item.isFormField())
                 		{
                 			name = new File(item.getName()).getName();
                 			item.write( new File(DIRECTORY + name));
                 		}
                 	}
                 	request.setAttribute("message", "File Uploaded Successfully");
            	} catch (Exception ex) 
            		{
            			request.setAttribute("message", "File Upload Failed due to " + ex);
            		}          
         
	    }
	    else{
            request.setAttribute("message","Sorry this Servlet only handles file upload request");
        	}
        circuit.readXMLFile(contextPath + name);
        
		try 
		{
			dbhelp.addCircuit(circuit);
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


