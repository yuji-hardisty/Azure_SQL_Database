package DBMS_project;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataHandler {
	
	private Connection conn;
	// Azure SQL connection credentials
	private String server = "server";
	private String database = "database";
	private String username = "username";
	private String password = "password";

	// Resulting connection string
	final private String url = String.format(
			"jdbc:sqlserver://" ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
			server, database, username, password);

	// Initialize and save the database connection
	private void getDBConnection() throws SQLException {
		if (conn != null) {
			return;
		}

		this.conn = DriverManager.getConnection(url);
	}

	// Return the result of selecting everything from the movie_night table
	public ResultSet getAllCustomers() throws SQLException {
		getDBConnection();
		final String sqlQuery = "SELECT * FROM customer;";
		final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		return stmt.executeQuery();
	}
	

	// Inserts a record into the customer table with the given attribute values
	public boolean addCustomers(String cname, String address, int category) throws SQLException {
		getDBConnection(); // Prepare the database connection

		// Prepare the SQL statement
		final String sqlQuery =
				"INSERT INTO customer "
					+ "(cname, address, category) "
				+ "VALUES "
					+ "(?, ?, ?)";
		final PreparedStatement stmt = conn.prepareStatement(sqlQuery);

		// Replace the '?' in the above statement with the given attribute values
		stmt.setString(1, cname);
		stmt.setString(2, address);
		stmt.setInt(3, category);
		// Execute the query, if only one record is updated, then we indicate success by returning true
		return stmt.executeUpdate() == 1;
	}

		// Inserts a category range to retrieve the equivalent customer 
		public boolean retrieveCustomers(int category_from, int category_to) throws SQLException {
			getDBConnection(); // Prepare the database connection
			boolean success;
			    final CallableStatement cstmt = conn.prepareCall("{call dbo.find_customers_with_category_range(?,?)}");{
		    	    cstmt.setInt("@category_from", category_from);  	  
		    	    cstmt.setInt("@category_to", category_to);      
		    	    success =  cstmt.execute();
		        }	
			    return success == true;
		}	
		public ResultSet retrieveCustomersResults(int category_from, int category_to) throws SQLException {
			getDBConnection(); // Prepare the database connection
			    final CallableStatement cstmt = conn.prepareCall("{call dbo.find_customers_with_category_range(?,?)}");{
		    	    cstmt.setInt("@category_from", category_from);  	  
		    	    cstmt.setInt("@category_to", category_to);      
		    	    return cstmt.executeQuery();
		        }	

		}			
		
}
