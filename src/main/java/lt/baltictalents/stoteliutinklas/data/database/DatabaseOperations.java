package lt.baltictalents.stoteliutinklas.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Route;
import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.data.layer.RoutesListFactory;

public class DatabaseOperations 
{
	/* gal tiks Genadijaus uzduociai:
     *  SELECT * FROM statistics WHERE date BETWEEN datetime('now', localtime') AND datetime ( 'now', '-1 month')
     */
	
	
	static protected String url = "jdbc:sqlite:C:\\sqlitedbs\\StoteliuTinklas.db";
	
	 /**
     * Connect to the url database
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
     * Gets stations from the database Stations record and returns List<Stations> by oldest to newest pavilions
     */
	public static List<Station> getPavilionsByOldestDate()
	{
		List<Station> ret = new LinkedList<Station>();
		
		//---
		 String sql = "SELECT name, longitude, latitude, date FROM Stations ORDER BY date";
		 Date neededDate = null;
         
         try (Connection conn = connect();
              Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql)){
             
             // loop through the result set
             while (rs.next()) 
             {
            	 
            	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            	 try 
            	 {
            		 if( rs.getString("date") == null) neededDate = null;
            		 else neededDate = df.parse(rs.getString("date"));
            	 }
            	 catch (ParseException e)
            	 {
            		 System.out.println(e.getMessage());
            	 }
            	 
                 ret.add(
                		 new Station(rs.getString("name"),
                				 rs.getString("longitude"),
                				 rs.getString("latitude"),
                				 null,
                				 neededDate
                				 )
                		 );
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         
		return ret;
	}
	
    /*
     * date as YYYY-MM-DD
     * Sets a date of the pavilion in Stations db table
     */
    public static void setPavilionDate(int id, String date) 
    {
    	String sql = "UPDATE Stations SET date = ?"
                + " WHERE id = ?";
    	
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
        	pstmt.setString(1, date);
            pstmt.setInt(2, id);
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*
     * Sets date at id record to current date (now) in Stations db table
     */
    public static void touchPavilionDate(int id)
    {
    	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate now = LocalDate.now();
    	String date = df.format(now);
    	
    	setPavilionDate(id, date);
    }


	/*
	 * Database table Stoteles to connection's getList
	 */
    public static void StationsDatabaseTableTogetStotelesList(DataListFactory connection)
    {
    	 String sql = "SELECT name, longitude, latitude, date FROM Stations";
         List<Station> ret = new LinkedList<Station>();
         Date neededDate=null;
         
         try (Connection conn = connect();
              Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql)){
             
             // loop through the result set
             while (rs.next()) 
             {
            	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            	 try {
            	     neededDate = df.parse(rs.getString("date"));
            	 } catch (ParseException e) {
            	    e.printStackTrace();
            	 } catch (NullPointerException e)
            	 {
            		 neededDate = null;
            	 }
            	 
                 ret.add(
                		 new Station(rs.getString("name"),
                				 rs.getString("longitude"),
                				 rs.getString("latitude"),
                				 null,
                				 neededDate
                				 )
                		 );
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         connection.SetStoteles(ret);
    }
    
    
    /*
     * 1. From getStoteles list of stationsConnection to Database table Stoteles 
     * (adds anew if not created/ nothing if table created)
     * 2. From getStoteles list of routesConnection to Database table Routes 
     * (adds anew if not created/ nothing if table created)
     * 3. NOT IMPLEMENTED: Fill Stations_Routes (many-to-many) table
     */ 
	public static void initializeDatabase(DataListFactory stationsConnection, RoutesListFactory routesConnection) 
	{
		try(Connection conn = DriverManager.getConnection(url))
		{
			try {
				int checkExit = 0;
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as rowcount from Stations");
			    // checking if Stations table exists and has rows
			    if (rs.next() == true) {
			    	if(rs.getInt("rowcount") != 0)
			    		checkExit++;
			    }
			    
			    stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT COUNT(*) as rowcount from Routes");
			    // checking if Routes table exists and has rows
			    if (rs.next() == true) {
			    	if(rs.getInt("rowcount") != 0) checkExit++;
			    if(checkExit==2) return;
			    }
			}
			catch (Exception e)
			{
				
			}
			
			//Code below will exec if either Stations or Routes has NO DATA in it's tables OR does not exist
			
			List<Station> stations = stationsConnection.getStoteles();
			List<Route> routes = routesConnection.getRoutes();
			//CREATION
			// SQL statement for creating a new table
	        String sqlStations = "CREATE TABLE IF NOT EXISTS Stations (\n"
	                + "	id integer PRIMARY KEY,\n"
	                + "	name text NOT NULL,\n"
	                + "	longitude text NOT NULL,\n"
	                + "	latitude text NOT NULL,\n"
	                + "	date DATE\n"//TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS")
	                + " );";
	        
	        String sqlRoutes = "CREATE TABLE IF NOT EXISTS Routes (\n"
	                + "	id integer PRIMARY KEY,\n"
	                + "	routeMeanAndNb text NOT NULL,\n"
	                + "	startStation text NOT NULL,\n"
	                + "	endStation text NOT NULL\n"
	                + " );";
	        
	        try (
	            Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sqlStations);
	            stmt.execute(sqlRoutes);
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        //INSERTION
	        sqlStations = "INSERT INTO Stations(name, longitude, latitude, date) VALUES(?,?,?,?)";
	        sqlRoutes = "INSERT INTO Routes(routeMeanAndNb, startStation, endStation) VALUES(?,?,?)";
	        
	        PreparedStatement pstmtStations=null;
	        PreparedStatement pstmtRoutes=null;
	        try{
	        	
	        	//conn = connect();
	        	pstmtStations = conn.prepareStatement(sqlStations);
	        	pstmtRoutes= conn.prepareStatement(sqlRoutes);
	        	
	        	if(conn == null) return;
	        	conn.setAutoCommit(false);
	        	
	        	for(Station s : stations)
	        	{
	        		pstmtStations.setString(1, s.getName());
	        		pstmtStations.setString(2, s.getLongitude());
		            pstmtStations.setString(3, s.getLatitude());
		            pstmtStations.setString(4, null); 
		            pstmtStations.executeUpdate();
		            
		            
	        	}
	        	
	        	for(Route r : routes)
	        	{
	        		pstmtRoutes.setString(1, r.getRouteMeanAndNb());
	        		pstmtRoutes.setString(2, r.getStartStation());
	        		pstmtRoutes.setString(3, r.getEndStation());
	        		pstmtRoutes.executeUpdate();
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
	                if (pstmtStations != null) pstmtStations.close();
	                if (pstmtRoutes != null) pstmtRoutes.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e3) {
	                System.out.println(e3.getMessage());
	            }
	            
	        }
		}
		catch(Exception e){
			
			System.out.println(e.toString());
		}
		finally
		{
			
		}
		
		//3. NOT IMPLEMENTED: Fill Stations_Routes (many-to-many) table
		
	}
	
	
}


	

