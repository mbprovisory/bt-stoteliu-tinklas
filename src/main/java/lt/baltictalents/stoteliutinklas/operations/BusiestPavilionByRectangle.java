package lt.baltictalents.stoteliutinklas.operations;

import java.util.List;

import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.data.beans.Station;

public class BusiestPavilionByRectangle {
    
    public DataListFactory connection;
    
    public BusiestPavilionByRectangle(DataListFactory connection) {
	this.connection = connection;
    }
    
    public List<Station> get(Rectangle rectangle){
	if(rectangle ==(null)) {return connection.getStoteles();}// TODO equals metodas?
	
	
	return connection.getStoteles();
    }

}
