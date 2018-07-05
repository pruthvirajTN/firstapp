

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Update() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String Aa=null;
		String aadharno=request.getParameter("AadharNo");
		
		
		
		 PrintWriter pw=response.getWriter();
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/village","root","root");
			 PreparedStatement ps = con.prepareStatement("select * from villagedata ");
			 
			 
			
			 ResultSet rs=ps.executeQuery();
		while(rs.next()) {
		Aa=rs.getString("AadharNo");
			
		}
				  
			if(Aa.compareTo(aadharno)==0) {
				
			}
			else {
				System.out.println("No update");
				
			}
			//Creating arraylist  
			 //Adding object in arraylist  
			  
			  //Traversing list through Iterator  
			    
			  
			
		 
			con.close();
		 
		 }
	
catch( Exception e) {
	
}
		 }
}
