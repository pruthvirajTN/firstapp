

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
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
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
		String n=null;
		String mail=null;
		String Name=request.getParameter("name");
		String Email=request.getParameter("email");
		String UserName=request.getParameter("username");
		String Password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/village","root","root");
			PreparedStatement s=con.prepareStatement("select * from users");
			ResultSet rs=s.executeQuery();
			boolean flag=false;
			while(rs.next()) {
				 n=rs.getString("Username");
				 mail=rs.getString("Email");
				
			
			if(n.equals(UserName)&& mail.equals(Email)) {
				response.sendRedirect("Duplicate.html");
				
			}
			}
			if(!flag){
			PreparedStatement ps=con.prepareStatement("insert into users(Name,Email,Username,Password) values(?,?,?,?)");
		ps.setString(1,Name);
		ps.setString(2, Email);
		ps.setString(3,UserName);
		ps.setString(4, Password);
		int rs1=ps.executeUpdate();
		response.sendRedirect("Created.html");
		con.close();
			}
		}
		catch(Exception e){
			
		}
		
	}

}
