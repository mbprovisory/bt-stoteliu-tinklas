package lt.baltictalents.stoteliutinklas.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * TESTS Create Read Update Delete (CRUD) functionality in our newly SQLite database;
 */
public class DatabaseOperationsMangirdas 
{
	
	public static void read(String... args) 
	{
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\sqlitedbs\\StoteliuTinklas.db";
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


	

