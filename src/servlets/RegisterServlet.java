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


public class RegisterServlet extends HttpServlet {
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
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		int flag = 0;
		 
		flag = insert2userInfor(name,pwd,email);
		
		if(flag == 1) {
			HttpSession session=req.getSession();   
		    session.setAttribute("username", name); 
		    res.sendRedirect("home.jsp");  
		}
		else if (flag == -1) {
			
			res.sendRedirect("register_f.html");
		} 
	 }
	
	 public int insert2userInfor(String username, String password, String email) {
//			ResultSet rs = null;
			Statement stmt1 = null;
			Statement stmt2 = null;
			Connection conn = null;
			int flag = 0;
	     
			try 
			{
	         // Register JDBC driver
				Class.forName(JDBC_DRIVER);
				
	         // Open a connection
				conn = DriverManager.getConnection(DB_URL, "wenxin", "123654");
				
	         // Execute SQL query
				stmt1 = conn.createStatement();
				
				String check = "select * from userInfor where name = '"+username+"';";

				ResultSet rs = stmt1.executeQuery(check); 
				
				if (!rs.next()) {
					
				
					stmt2 = conn.createStatement();
					String query = "insert into userInfor (name,password,email) values ('"+username+"', '"+password+"', '"+email+"');" ;
     
					int row = stmt2.executeUpdate(query);     
		        // System.out.println(row);
					if (row > 0)
						flag = 1;        
					else
						flag = 0;
				
					stmt1.close();
					stmt2.close();
					conn.close();
				}else {
					flag = -1;
				}
				
				
				}catch(Exception e){
	            e.printStackTrace();
	        }
		return flag;
    }
}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	         

	
