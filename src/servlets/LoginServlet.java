package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final static String DB_URL = "jdbc:mysql://localhost:3306/wenxin";    
	final static String USER = "wenxin";
	final static String PWD = "123654";
	
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
	 }
	
	 public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
		
		String name = req.getParameter("name");
		int flag = 0;
		String pwd = req.getParameter("pwd");
		 
		flag = queryByUsername(name,pwd);
		
		if(flag == 1) {
			HttpSession session=req.getSession();   
		    session.setAttribute("username", name); 
		    res.sendRedirect("home.jsp");  
		}
		else {
			res.sendRedirect("login_f.jsp");
			
			
		} 
	 }
	 
	 public int queryByUsername(String username, String password) {
			ResultSet rs = null;
			Statement stmt = null;
			Connection conn = null;
			int flag = 0;
	   
			try 
			{
	         // Register JDBC driver
				Class.forName(JDBC_DRIVER);
	         // System.out.println("MySQL JDBC Driver Registered");
		          
	         // Open a connection
				conn = DriverManager.getConnection(DB_URL, "wenxin", "123654");
	         // System.out.println("Connection established");
		   
	         // Execute SQL query
				stmt = conn.createStatement();
				String query = "SELECT * FROM userInfor";
				rs = stmt.executeQuery(query); 
	          
				while (rs.next())
				{
					if(rs.getString("name").equals(username) && rs.getString("password").equals(password) ) {
	            			flag = 1;
					}
				}	
				rs.close();
                stmt.close();
                conn.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
		return flag;
    }
}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	         

	