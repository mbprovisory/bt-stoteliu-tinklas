package lt.baltictalents.stoteliutinklas.operations;

import java.util.List;
//import java.util.Optional; //paemę mažą stačiakampį gausime tuščią list'ą, todėl r. naudoti šitą;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;

import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.data.beans.Coordinates;
import lt.baltictalents.stoteliutinklas.data.beans.Station;

public class BusiestPavilionByRectangle {
    
    public DataListFactory connection;
    
    public BusiestPavilionByRectangle(DataListFactory connection) {
	this.connection = connection;
    }
    
    public List<Station> get(String longit1, String latit1,String longit2,String latit2){
	Coordinates corner1 = new Coordinates(longit1, latit1);
	Coordinates corner2 = new Coordinates(longit2,latit2);
	Rectangle rectangle = new Rectangle(corner1, corner2);
	
	return getBusiestPavilionsFromSortedList(getStationsSortedByRoutesThenNames((getInRectangle(rectangle)))); 
	}
    
    public List<Station> getOpt(String longit1, String latit1, String longit2, String latit2){ //integruotas metodas;
	Coordinates corner1 = new Coordinates(longit1, latit1);
	Coordinates corner2 = new Coordinates(longit2,latit2);
	Rectangle rectangle = new Rectangle(corner1, corner2);
	
	Comparator<? super Station> comparatorByRoutesThenNames = (st1, st2)  -> {if (st1.getRoutes().length-st2.getRoutes().length==0) {
	    return st1.getName().compareTo(st2.getName());
	} else {return st1.getRoutes().length - st2.getRoutes().length;}
	};
	
	List<Station> stotelesOI = connection.getStoteles()
		.stream()
		.filter(st ->  rectangle.contains(new Coordinates(st.getLongitude(),st.getLatitude())))
		.sorted(comparatorByRoutesThenNames )
		.collect(toList());
	
	int routesCountMax = stotelesOI.get(0).getRoutes().length;
	
	List<Station>busiestPavilion = stotelesOI.stream().filter(st -> st.getRoutes().length==routesCountMax).collect(toList());
	return busiestPavilion;
	 }
    
    public List<Station> getInRectangle(Rectangle rectangle){
	if(rectangle ==(null)) {return connection.getStoteles();}// TODO equals metodas?
	List<Station> stotelesInRect = 
		connection.getStoteles()
		.stream()
		.filter(st ->  rectangle.contains(new Coordinates(st.getLongtitute(),st.getLatitude())))
		.collect(toList());
	
	return stotelesInRect;
    }
    
    public List<Station> getStationsSortedByRoutesThenNames(List<Station> stations){
	Comparator<? super Station> comparatorByRoutesThenNames = (st2, st1)  -> {if (st1.getRoutes().length-st2.getRoutes().length==0) {
	    return st1.getName().compareTo(st2.getName());
	} else {return st1.getRoutes().length - st2.getRoutes().length;}
	};
	//if(false) {new String("do nothing");}//TODO consider generics,  aand safe checking;
	List<Station> sortedList = stations.stream()
			.sorted(comparatorByRoutesThenNames )
			.collect(toList());
	return sortedList; 
    }
    
    public List<Station> getBusiestPavilionsFromSortedList(List<Station> sortedList){
	int routesCountMax = sortedList.get(0).getRoutes().length;
	List<Station>busiestPavilion = sortedList.stream().filter(st -> st.getRoutes().length==routesCountMax).collect(toList());
	return busiestPavilion;
    }

    
}
