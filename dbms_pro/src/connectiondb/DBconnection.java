package connectiondb;

import java.sql.Connection;


import java.sql.DriverManager;
//import java.sql.SQLException;


	public class DBconnection
	{
	    public static Connection doConnect()
	    {
	        Connection con=null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	             con=DriverManager.getConnection("jdbc:mysql://localhost/learn","root","tamanna");
	            System.out.println("Connected....");
	        }
	        catch (Exception e)  //becoz there are two exceptions throw so we put parent class exception.
	          {
	            e.printStackTrace();
	          }
	        return con;
	    }

	    public static void main(String[] args)
	    {
	        doConnect();

	    }

	}





