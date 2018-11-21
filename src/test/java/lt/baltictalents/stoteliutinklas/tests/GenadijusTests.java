package lt.baltictalents.stoteliutinklas.tests;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;


import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

import lt.baltictalents.stoteliutinklas.operations.PavilionsByRectangle;
import lt.baltictalents.stoteliutinklas.operations.RoutesNumbersByStationName;


public class GenadijusTests {
	
	DataListFactory connection = new DataListFactory();
	
	PavilionsByRectangle pavilionsByRectangle;
	RoutesNumbersByStationName routesNumbersByStationName;
	
	@Before
	public void setUp() throws Exception {
		
		pavilionsByRectangle = new PavilionsByRectangle(connection);
		routesNumbersByStationName = new RoutesNumbersByStationName(connection);
	}
	

	@Test
	public void testPavilionsByRectangle() {
		
		assertEquals(connection.getStoteles().size(),
				pavilionsByRectangle.get("00.00", "00.00" ,"99.99", "99.99").size());
		
		assertEquals(1,pavilionsByRectangle.get("25.2807600", "54.7553200","25.2807800", "54.7553300").size());
		
		assertEquals("Vaikų ligoninė",
				pavilionsByRectangle.get("25.2807600", "54.7553200","25.2807800", "54.7553300").get(0).getName());
	}
	
	@Test
	public void RoutesNumbersByStationName() {
		assertEquals("Autobusas 2G: Stotis - Santariškės",
				routesNumbersByStationName.get("Vaikų ligoninė").get(0)[0]);
		
	
	}

}
