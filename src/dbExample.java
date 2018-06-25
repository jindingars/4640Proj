//package examples;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class jdbcExample
 */
@WebServlet("/dbExample")
public class dbExample extends HttpServlet 
{
   private static final long serialVersionUID = 1L;
   // JDBC driver name and database URL
   final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  

   // database name = web4640
   //   Note: Looking in the wrong database and/or wrong table may results in either 
   //         cannot connect to the database, not find table, or no result set. 
   //         Thus, make sure specify the correct database name
   // post to mySQL Database = 3308  (default port in XAMPP is 3306)
   final static String DB_URL = "jdbc:mysql://localhost:3306/wenxin";    
   
   //  Database credentials
   final static String USER = "wenxin";
   final static String PWD = "123654";
      
// Create a new user account "web4640" -- don't touch "root" account
// edit privileges, change password tab, assign password to the user ("pwd4640")
// be sure to refresh under title phpmyadmin at top-left corner   
   
   String msg = "";
   
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      String title = "Simple example: Servlet and Database";
      out.println("<html>\n" + 
                  "<head><title>" + title + "</title></head>\n" +
    		      "<body>\n" + 
                  "  <h1 align=\"center\">" + title + "</h1>\n");
      out.println("  <center>");
      out.println("  <form  method=\"post\">");      
      out.println("     <button type=\"submit\" name=\"btn\" value=\"create\" />Create table</button>");
      out.println("     <button type=\"submit\" name=\"btn\" value=\"insert\" />Insert data</button>");
      out.println("     <button type=\"submit\" name=\"btn\" value=\"select\" />Retrieve data</button>");
      out.println("     <button type=\"submit\" name=\"btn\" value=\"update\" />Update data</button>");
      out.println("     <button type=\"submit\" name=\"btn\" value=\"delete\" title=\"Caution: this is unrecoverable transaction!\" />Delete data</button>");
      out.println("     <button type=\"submit\" name=\"btn\" value=\"drop\" title=\"Caution: this is unrecoverable transaction!\" />Drop table</button>");
      out.println("  </form>");
      out.println("  Confirmation : " + msg);
      out.println("</body>");
      out.println("</html>");    
   }   

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {	  
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      String action = request.getParameter("btn");      
      
      if (action.equals("create"))
         msg = createTable();
      else if (action.equals("select"))
         msg = retrieveData(response);
      else if (action.equals("insert"))
         msg = insertData();
      else if (action.equals("update"))
         msg = updateData();
      else if (action.equals("delete"))
    	 msg = deleteData();
      else if (action.equals("drop"))
    	 msg = dropTable();      

      doGet(request, response);      // redraw the screen
   }
   
   
//   This example shows each database interaction separately. 
//   In a real project, you should consider how to write code 
//   such that it is readable, reusable, manageable, and maintainable
//   (hint: decomposition and integration)   
   
   
   private String createTable() 
   {	  
      ResultSet rs = null;
      Statement stmt = null;
      Connection conn = null;
      
      String msg = "";      // feedback indicating whether the query is successful
      
      try 
      {
         // Register JDBC driver
         Class.forName(JDBC_DRIVER);
         // System.out.println("MySQL JDBC Driver Registered");
	          
         // Open a connection
         // conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/web4640", "web4640", "pwd4640");         
         conn = DriverManager.getConnection(DB_URL, "wenxin", "123654");
         // System.out.println("Connection established");
	   
         // Execute SQL query
         stmt = conn.createStatement();
         DatabaseMetaData dbm = conn.getMetaData();
         // check if the table is there
         ResultSet tables = dbm.getTables(null, null, "my_table", null);
         if (tables.next()) 
         {            
            // System.out.println("table exists, don't create");
        	 msg = "Table exists, don't create";
         }
         else 
         {
            // System.out.println("table does not exist, create it");        	 
            String query = "create table my_table (test_id varchar(255) not null, test_desc varchar(255) not null );";
            int row = stmt.executeUpdate(query);
            // System.out.println("create row = " + row);      // this row will results in 0 because the query did not retrieve anything
            
            // check if the table was actually created
            // getTables("schema_name", null, "table_name", new String[] {"table"})   
            tables = dbm.getTables(null, null, "my_table", null);//check if the table is actually created
            if (tables.next()) 
            {            
               // System.out.println("table was created ");
               msg = "Table was created";
            }
               
         }                 
         // Clean-up environment
         if (rs != null)
            rs.close();              
         stmt.close();
         conn.close();
         // System.out.println("close database");
                
         Driver driver = null;
         java.sql.DriverManager.deregisterDriver(driver);         
         // System.out.println("deregister driver");    // deregister driver to avoid memory leak on the server
         
      } catch (SQLException se) {
         se.printStackTrace();       // handle errors for JDBC
      } catch (Exception e) {            
         e.printStackTrace();        // handle errors for Class.forName
      } finally {
          // finally block used to close resources
          try {
             if (stmt != null)
                stmt.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se2) {
     	 // nothing we can do
         	 
          }             
          try
          {
             if (conn != null)
                conn.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se) {
             se.printStackTrace();
          } // end finally try   
          
       } // end try

      return msg;
   }
   
   private String retrieveData(HttpServletResponse response) throws ServletException, IOException
   {	  
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      ResultSet rs = null;
      Statement stmt = null;
      Connection conn = null;
      
      String msg = "";      // feedback indicating whether the query is successful
      
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
        		
         // Extract data from result set
         while (rs.next())
         {
            // Retrieve by column name
            String id  = rs.getString("name");
            String desc = rs.getString("password");
  
            // Display values on screen
            // out.println("test_id : " + id + ", test_desc : " + desc + " <br />");            
            msg = msg + "<br /> test_id : " + id + ", test_desc : " + desc + " <br />";
         }	   
                 
         // Clean-up environment
         if (rs != null)
            rs.close();         
         stmt.close();
         conn.close();
         // System.out.println("close database");
                
         Driver driver = null;
         java.sql.DriverManager.deregisterDriver(driver);         
         // System.out.println("deregister driver");
         
      } catch (SQLException se) {
         se.printStackTrace();       // handle errors for JDBC
      } catch (Exception e) {            
         e.printStackTrace();        // handle errors for Class.forName
      } finally {
          // finally block used to close resources
          try {
             if (stmt != null)
                stmt.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se2) {
     	 // nothing we can do
         	 
          }             
          try
          {
             if (conn != null)
                conn.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se) {
             se.printStackTrace();
          } // end finally try   
          
       } // end try

      return msg;
   }
   
   
   private String insertData() 
   {	  
      ResultSet rs = null;
      Statement stmt = null;
      Connection conn = null;
      
      String msg = "";      // feedback indicating whether the query is successful
      
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
         String query = "insert into my_table (test_id, test_desc) values ('id1', 'desc1');" ;
            
         int row = stmt.executeUpdate(query);            
         if (row > 0)
            msg = "A record was inserted";        
         else
            msg = "Unable to insert the record";

         // if no key is specified when creating the table, duplicates are allowed
         // try insert the same values multiple times to see what would happen with/without key
         // but we'll not worry about that for now
                 
         // Clean-up environment
         if (rs != null)
            rs.close();         
         stmt.close();
         conn.close();
         // System.out.println("close database");
                
         Driver driver = null;
         java.sql.DriverManager.deregisterDriver(driver);         
         // System.out.println("deregister driver");
         
      } catch (SQLException se) {
         se.printStackTrace();       // handle errors for JDBC
      } catch (Exception e) {            
         e.printStackTrace();        // handle errors for Class.forName
      } finally {
          // finally block used to close resources
          try {
             if (stmt != null)
                stmt.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se2) {
     	 // nothing we can do
         	 
          }             
          try
          {
             if (conn != null)
                conn.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se) {
             se.printStackTrace();
          } // end finally try   
          
       } // end try

      return msg;
   }
   
        
   private String updateData() 
   {	  
      ResultSet rs = null;
      Statement stmt = null;
      Connection conn = null;
      
      String msg = "";      // feedback indicating whether the query is successful
      
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
         String query = "UPDATE `my_table` SET `test_desc`='new_desc2' WHERE `test_id`='id1';" ;
         int row = stmt.executeUpdate(query);
         if (row > 0)
            msg = "A record was updated";
         else
            msg = "Unable to update the record";
                 
         // Clean-up environment
         if (rs != null)
            rs.close();         
         stmt.close();
         conn.close();
         // System.out.println("close database");
                
         Driver driver = null;
         java.sql.DriverManager.deregisterDriver(driver);         
         // System.out.println("deregister driver");
         
      } catch (SQLException se) {
         se.printStackTrace();       // handle errors for JDBC
      } catch (Exception e) {            
         e.printStackTrace();        // handle errors for Class.forName
      } finally {
          // finally block used to close resources
          try {
             if (stmt != null)
                stmt.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se2) {
     	 // nothing we can do
         	 
          }             
          try
          {
             if (conn != null)
                conn.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se) {
             se.printStackTrace();
          } // end finally try   
          
       } // end try

      return msg;
   }

   private String deleteData() 
   {	  
      ResultSet rs = null;
      Statement stmt = null;
      Connection conn = null;
   
      String msg = "";      // feedback indicating whether the query is successful
   
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
         String query = "DELETE FROM `my_table` WHERE test_id='id1';" ;
         int row = stmt.executeUpdate(query);     
         if (row > 0)
            msg = "A record was deleted";   
         else
            msg = "Unable to delete the record";
              
         // Clean-up environment
         if (rs != null)
            rs.close();         
         stmt.close();
         conn.close();
         // System.out.println("close database");
             
         Driver driver = null;
         java.sql.DriverManager.deregisterDriver(driver);         
         // System.out.println("deregister driver");
      
      } catch (SQLException se) {
         se.printStackTrace();       // handle errors for JDBC
      } catch (Exception e) {            
         e.printStackTrace();        // handle errors for Class.forName
      } finally {
          // finally block used to close resources
          try {
             if (stmt != null)
                stmt.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se2) {
        	 // nothing we can do
      	 
          }             
          try
          {
             if (conn != null)
                conn.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se) {
             se.printStackTrace();
          } // end finally try   
       
       } // end try

      return msg;
   }
   
   
   private String dropTable() 
   {	  
      ResultSet rs = null;
      Statement stmt = null;
      Connection conn = null;
      
      String msg = "";      // feedback indicating whether the query is successful
      
      try 
      {
         // Register JDBC driver
         Class.forName(JDBC_DRIVER);
         // System.out.println("MySQL JDBC Driver Registered");
	          
         // Open a connection
         conn = DriverManager.getConnection(DB_URL, "cs4640", "304947187");
         // System.out.println("Connection established");
	   
         // Execute SQL query
         stmt = conn.createStatement();
         DatabaseMetaData dbm = conn.getMetaData();
         // check if the table is there
         ResultSet tables = dbm.getTables(null, null, "my_table", null);
         if (tables.next()) 
         {            
            String query = "drop table my_table;";
            int row = stmt.executeUpdate(query);
         // System.out.println("create row = " + row);      // this row will results in 0 because the query did not retrieve anything
            
            // check if the table was actually dropped
            // getTables("schema_name", null, "table_name", new String[] {"table"})   
            tables = dbm.getTables(null, null, "my_table", null);
            if (tables.next()) 
            {            
               // System.out.println("table was not dropped ");
               msg = "Table was dropped";
            }
            else
            {            
                // System.out.println("table was dropped ");
                msg = "Table was dropped";
            }            
         }

         // Clean-up environment
         if (rs != null)
            rs.close();         
         stmt.close();
         conn.close();
         // System.out.println("close database");
                
         Driver driver = null;
         java.sql.DriverManager.deregisterDriver(driver);         
         // System.out.println("deregister driver");
         
      } catch (SQLException se) {
         se.printStackTrace();       // handle errors for JDBC
      } catch (Exception e) {            
         e.printStackTrace();        // handle errors for Class.forName
      } finally {
          // finally block used to close resources
          try {
             if (stmt != null)
                stmt.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se2) {
     	 // nothing we can do
         	 
          }             
          try
          {
             if (conn != null)
                conn.close();

             Driver driver = null;
             java.sql.DriverManager.deregisterDriver(driver);

          } catch (SQLException se) {
             se.printStackTrace();
          } // end finally try   
          
       } // end try

      return msg;
   }

    
}