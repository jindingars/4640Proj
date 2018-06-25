package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
	
	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final static String DB_URL = "jdbc:mysql://localhost:3306/wenxin";    
	final static String USER = "wenxin";
	final static String PWD = "123654";  
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String desc = req.getParameter("desc");
        String price = req.getParameter("price");
        String type = req.getParameter("type");
        String location = req.getParameter("location");
        String email = req.getParameter("email");
        PrintWriter pw = resp.getWriter();
        int flag = 0;
        flag = insert2commodity(desc, price, type, location,email);
        if(flag == 1)
        {
            pw.write("post successfully!");
        }else
        {
            pw.write("Failed!");
        }
    }
    
    
    private int insert2commodity(String desc, String price, String type, String location, String email) {
//		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		int flag = 0;
     
      
		try 
		{
         // Register JDBC driver
			Class.forName(JDBC_DRIVER);
          //System.out.println("MySQL JDBC Driver Registered");
	          
         // Open a connection
			conn = DriverManager.getConnection(DB_URL, "wenxin", "123654");
         // System.out.println("Connection established");
	   
         // Execute SQL query
			stmt = conn.createStatement();
			String query = "insert into commodity (description, price, email, type, location) values ('"+desc+"', '"+price+"', '"+email+"','"+type+"', '"+location+"');" ;
            
	         int row = stmt.executeUpdate(query);     
	        // System.out.println(row);
	         if (row > 0)
	        	 	flag = 1;        
	         else
	        	 	flag = 0;
		
            stmt.close();
            conn.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
	return flag;
}

}
