package lt.baltictalents.stoteliutinklas.tests;

import static org.junit.Assert.*;


import org.junit.Test;

import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.operations.RoutesByPavilionCoords;

public class TestRoutesByPavilionCoords {
	
	DataListFactory connection = new DataListFactory();
	RoutesByPavilionCoords marsrutai = new RoutesByPavilionCoords(connection);

	@Test
	public void testRoutesByPavilionCoords() {
		assertEquals(1177, marsrutai.get(25.2832158, 54.6707631).size());
	}
	
}
