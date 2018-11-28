package lt.baltictalents.stoteliutinklas.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

import lt.baltictalents.stoteliutinklas.operations.PavilionsByRectangle;
import lt.baltictalents.stoteliutinklas.operations.RoutesNumbersByStationName;
import lt.baltictalents.stoteliutinklas.operations.SameStationsByCrossingRoutes;

public class GenadijusTests {
	
	DataListFactory connection = new DataListFactory();
	
	PavilionsByRectangle pavilionsByRectangle;
	RoutesNumbersByStationName routesNumbersByStationName;
	SameStationsByCrossingRoutes sameStationsByCrossingRoutes;
	
	@Before
	public void setUp() throws Exception {
		
		pavilionsByRectangle = new PavilionsByRectangle(connection);
		routesNumbersByStationName = new RoutesNumbersByStationName(connection);
		sameStationsByCrossingRoutes = new SameStationsByCrossingRoutes(connection);
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
	@Test
	public void SameStationsByCrossingRoutes() {
		List<String> test = sameStationsByCrossingRoutes.get("Autobusas 2G: Stotis - Santariškės","Autobusas 48 Santariškės - Laisvės pr. - Šeškinė");
		
		
		assertTrue(test.get(0).equals("Vaikų ligoninė"));
		assertTrue(test.get(1).equals("Žemynos st."));
		assertTrue(test.get(2).equals("Rygos st."));
		assertTrue(test.get(3).equals("Liudo Giros st."));
		assertTrue(test.get(4).equals("Mykolo Romerio universitetas"));
		assertTrue(test.get(5).equals("Santariškės"));
		assertTrue(test.get(6).equals("Vilniaus rajono poliklinika"));
		
	}

}
