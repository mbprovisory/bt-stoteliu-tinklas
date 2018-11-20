package lt.baltictalents.stoteliutinklas.operations;


import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.data.beans.*;

public class PavilionsByName {
    public DataListFactory connection;
    
    public PavilionsByName(DataListFactory connection) {
	this.connection = connection;
    }
    
    public List<Station> get(String pavilName){
	final String pName = pavilName.toLowerCase();
	
	Predicate<? super Station> predicateName = st -> st.getName().toLowerCase().contains(pName);
	
	List<Station> stotelByName = connection.getStoteles().stream().filter(predicateName ).collect(toList());
	
	return stotelByName;	
    }
}
