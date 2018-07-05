

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String aadharno=request.getParameter("aano");
		
		
			 try {
		    	   Class.forName("com.mysql.jdbc.Driver");
		    	   PrintWriter out=response.getWriter();
					 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/village","root","root");
					 PreparedStatement ps=con.prepareStatement("select * from villagedata where Aadharno=?");
					ps.setString(1, aadharno);
					 ResultSet rs=ps.executeQuery();
					 out.print("<table border='1' width='100%'"); 
			           
		                out.println("<tr><th>Name</th><th>FatherName</th><th>MotherName</th><th>Age</th><th>AadharNo</th><th>ContactNo</th>"
		                	+ "<th>Gender</th><th>DOB</th></tr>");
					 while(rs.next()){
					
						 String name=rs.getString(1);
						 
			                String fname=rs.getString(2); 
			                
			                String mname=rs.getString(3);
			                
			                String age=rs.getString(4);
			               
			                String Aadharno=rs.getString(5);
			                
			                String Contactno=rs.getString(6);
			                
			                String Gender=rs.getString(7);
			               
			                String Dob=rs.getString(8);
			                out.println("<tr><td>"+name+"</td><td>"+fname+"</td><td>"+mname+"</td><td>"+age+"</td><td>"+Aadharno+"</td><td>"+Contactno+"</td><td>"+Gender+"</td><td>"+Dob+"</td></tr>"); 
					           
			              
						 }
					 out.println("</table>");
		       out.close(); 
		       
		        

	 	       

		}
			       catch(Exception e){
			    	   System.out.println(e);
			       }
		}
	}


