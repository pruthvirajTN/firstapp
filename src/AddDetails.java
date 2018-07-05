

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddDetails
 */
@WebServlet("/AddDetails")
public class AddDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddDetails() {
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
		String aa=null;
		String name=request.getParameter("Name");
		String fname=request.getParameter("FatherName");
		String mname=request.getParameter("MotherName");
		String a=request.getParameter("Age");
		int age=Integer.parseInt(a);
		String gender=request.getParameter("gender");
		String aadharno=request.getParameter("AadharNo");
		
		String ContactNo=request.getParameter("ContactNo");
		 HttpSession session=request.getSession();
		 session.setAttribute("name",name); 
		
		String dob=request.getParameter("date");
		
		 PrintWriter pw=response.getWriter();
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 
			 
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/village","root","root");
			 PreparedStatement  s=con.prepareStatement("select * from villagedata");
			 boolean flag=false;
			 ResultSet rs=s.executeQuery();
			 
			 while(rs.next()) {
			  aa=rs.getString("AadharNo");
			  if(aa.equals(aadharno)){
				  flag=true;
					 response.sendRedirect("Details1.html");
			 }
			
			 }
			 
			 if(flag==false) {
			 PreparedStatement ps = con.prepareStatement("insert into villagedata (Name,FatherName,MotherName,Age,AadharNo,ContactNo,Gender,DOB) values (?,?,?,?,?,?,?,?) ");
			 
			 ps.setString(1, name);
			 ps.setString(2,fname );
			 ps.setString(3,mname );
			 ps.setLong(4,age);
			 ps.setString(5,aadharno );
			 ps.setString(6,ContactNo );
			 ps.setString(7,gender );
			 ps.setString(8,dob );
			 
			 int  i=ps.executeUpdate();
			 
			 if(i!=0)
		 {
		
			response.sendRedirect("Details.html");
				System.out.println("Details Added successfully");
		}
			 else {
				 System.out.println("Details NOt Added successfully");
				 
			 }	  
			
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
