

//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

@WebServlet("/welcomeMsg")
public class welcomeMsg extends HttpServlet
{

   public void processRequest (HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException
   {
      PrintWriter out = res.getWriter ();

      String Nm  = req.getParameter ("StringSoFar");
      if(Nm.equals("North Grounds")){
    	  out.print ("For people who live in ");
    	  out.print (Nm);out.print (" ,</FONT>\n");
      out.print ("You could take CGS/NorthLine/GreenLine to The Corner and Grounds");
      out.print ("</FONT>\n");}
      else if(Nm.equals("The Corner")||Nm.equals("On Grounds")){
        	  out.print ("For people who live in ");
        	  out.print (Nm);out.print (" ,</FONT>\n");
          out.print ("You could take CGS/NorthLine/GreenLine to North Grounds");
          out.print ("</FONT>\n");}
      

      out.close();
   }


    // Method doPost - just calls processRequest
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       processRequest (request, response);
    }

    // Method doGet - just calls processRequest
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       processRequest (request, response);
    }

}
