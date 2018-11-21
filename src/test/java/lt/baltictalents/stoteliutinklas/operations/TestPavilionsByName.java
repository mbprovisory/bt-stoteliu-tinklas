package lt.baltictalents.stoteliutinklas.operations;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;


public class TestPavilionsByName {
    public DataListFactory connection = new DataListFactory();
    
    PavilionsByName pavilions = new PavilionsByName(connection);
    
    @Test
    public void testGet() {
	List<Station> testPavil = pavilions.get("tus");	
	assertEquals(4, testPavil.size());
    }

}
