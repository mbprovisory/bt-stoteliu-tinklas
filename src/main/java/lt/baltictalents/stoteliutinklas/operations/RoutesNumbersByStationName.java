package lt.baltictalents.stoteliutinklas.operations;

import java.util.LinkedList;
import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByName;

public class RoutesNumbersByStationName {

	private DataListFactory connection;

    public RoutesNumbersByStationName(DataListFactory connection) {
	this.connection = connection;
    }
    public List<String[]> get (String stationName){
    	PavilionsByName rt = new PavilionsByName(connection);
    	
    	List<Station> num = rt.get(stationName);
    	List<String[]> routName = new LinkedList<String[]>();
    	
    	for (Station n:num){
    		routName.add(n.getRoutes());
    	
    	}
    	
		return routName;
    	
        }
}
    

