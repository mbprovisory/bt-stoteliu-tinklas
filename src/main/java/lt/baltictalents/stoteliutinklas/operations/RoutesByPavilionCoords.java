package lt.baltictalents.stoteliutinklas.operations;

import java.util.List;
import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

public class RoutesByPavilionCoords {
	
	public DataListFactory connection;
	
	public RoutesByPavilionCoords (DataListFactory connection) {
		this.connection = connection;
	}

	public List<Station> get(double x1,double y1) {
		
		List<Station> route = connection.getStoteles(); 

		double x,y;

		for(int i = 0; i < connection.getStoteles().size(); i++) {
			x =  Double.parseDouble(connection.getStoteles().get(i).getLongitude());
			y =  Double.parseDouble(connection.getStoteles().get(i).getLatitude());	
			
			if ( x == x1 && y == y1 ) {
				for(int j = 0; j < connection.getStoteles().get(i).getRoutes().length; j++) {
					 System.out.println(connection.getStoteles().get(i).getRoutes()[j]);
				}
			}
			
		}
		
 		
		return route;
	}
	
}
