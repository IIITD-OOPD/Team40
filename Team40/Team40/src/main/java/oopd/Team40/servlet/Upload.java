package oopd.Team40.servlet;

import java.io.File;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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

/**
 * Servlet implementation class Upload
 */

public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
    private String DIRECTORY = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   //	doGet(request, response);
		
		Circuit circuit=new Circuit();
	    ServletContext servletContext = getServletContext();
	    String contextPath = servletContext.getRealPath(File.separator);
	   // String contextPath = "F://meetika//December MTech//Eclipse Workspace//Team40";
	    
		this.DIRECTORY = contextPath;
	    String name = null;
	

		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                       name = new File(item.getName()).getName();
                       item.write( new File(DIRECTORY + name));
                    }
                }
           request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message","Sorry this Servlet only handles file upload request");
        }
        System.out.println(contextPath);
        circuit.readXMLFile(contextPath + name);
        
		DBHelper dbhelp = DBHelper.getInstance();
		try {
			dbhelp.addCircuit(circuit);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		int size = 0;
		size = circuit.getInputs().size();
		request.getSession().setAttribute("size",size);
	    
		request.getSession().setAttribute("circuit",circuit);
		request.getRequestDispatcher("/circuit.jsp").forward(request, response);
		
		
    }



		
	}


