package lt.baltictalents.stoteliutinklas.operations;

import java.util.List;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;
//import java.util.Comparator;

import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
//import lt.baltictalents.stoteliutinklas.data.beans.Coordinates;
import lt.baltictalents.stoteliutinklas.data.beans.Station;


public class StationsAndPavilionsByRouteNumber {
    
    private DataListFactory connection;

    public StationsAndPavilionsByRouteNumber(DataListFactory connection) {
	this.connection = connection;
    }

    public List<Station> get(String routeNb){
    Predicate<? super Station> predicateByName= st -> {
	   for( String rt : st.getRoutes()) {if (rt.contains(routeNb)) { return true; } }
	return false;
    };
    
    List<Station> stotelesByRoute = connection.getStoteles()
	    .stream()
	    .filter(predicateByName)
	    .collect(toList());
    
    return stotelesByRoute;
    }
}
