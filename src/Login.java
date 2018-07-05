

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
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
		String pw=null;
		String username=null;
		
		String uname=request.getParameter("user");
		String Password=request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			PrintWriter out=response.getWriter();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/village","root","root");
			PreparedStatement ps=con.prepareStatement("select *  from users where Username=? and Password=?");
			
			ps.setString(1,uname);
			ps.setString(2,Password);
			
			
			ResultSet rs=ps.executeQuery();
			boolean flag=false;
			while(rs.next()) {
			 username=rs.getString("Username");
			 pw=rs.getString("Password");
			 if(username.compareTo(uname)==0 && pw.compareTo(Password)==0){
				 
					
					out.println("login success");
					out.print("Welcome, "+username);  
					response.sendRedirect("index.html");
					flag=true;
			}
			}
			
			if(!flag){
				out.println("login failed");
				response.sendRedirect("failed.html");
			}	
				
			
			
			
			
		}
		catch(Exception e) {
			
		}
			
		
	}

}
