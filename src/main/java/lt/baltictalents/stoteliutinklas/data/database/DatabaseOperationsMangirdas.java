package lt.baltictalents.stoteliutinklas.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

public class DatabaseOperationsMangirdas 
{
	static protected String url = "jdbc:sqlite:C:\\sqlitedbs\\StoteliuTinklas.db";
	
	 /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private static Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	/*
	 * Creates and populates Stations table
	 */
	public static void getStotelesTextToDatabaseTable(DataListFactory connection) 
	{
		List<Station> stations = connection.getStoteles();
		//CREATION
		// SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Stations (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	longitude text NOT NULL,\n"
                + "	latitude text NOT NULL,\n"
                + "	date text\n"//TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS")
                + " );";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        //INSERTION
        sql = "INSERT INTO Stations(name, longitude, latitude, date) VALUES(?,?,?,?)";
        
        Connection conn=null;
        PreparedStatement pstmt=null;
        
        try{
        	
        	conn = connect();
        	pstmt = conn.prepareStatement(sql);
        	
        	if(conn == null) return;
        	conn.setAutoCommit(false);
        	
        	for(Station s : stations)
        	{
	        	pstmt.setString(1, s.getName());
	            pstmt.setString(2, s.getLongitude());
	            pstmt.setString(3, s.getLatitude());
	            pstmt.setString(4, null); 
	            pstmt.executeUpdate();
        	}
            
            conn.commit();
        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
            }
        }
        
		
	}
	
	/* TODO
	 * Creates and populates Stations_Routes table pointing to Stations:id and Routes:id
	 */
	public static void StationsAndRoutesDbTablesToStations_Routes(DataListFactory connection) 
	{
		
	}
	
	
	
	
	
	
	public static void create(String... args)
	{
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Stations (\n"
              //  + "	id integer PRIMARY KEY,\n"
                + "	pavadinimas text NOT NULL\n"
              //  + "	capacity real\n"
                + " );";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static void read(String... args) 
	{
        Connection conn = null;
        try {
           
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            String sql = "SELECT rowid, pavadinimas FROM stoteles";
            
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
           
           // loop through the result set
           while (rs.next()) {
               System.out.println(rs.getString("pavadinimas"));
           }
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
	
}


	

