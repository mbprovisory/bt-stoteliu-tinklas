package lt.baltictalents.stoteliutinklas.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRadius;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRouteNumberAndDirection;

public class MangirdasTests {
	
	DataListFactory connection = new DataListFactory();
	PavilionsByRadius pavilionsByRadius;
	PavilionsByRouteNumberAndDirection  pavilionsByRouteNumberAndDirection;
	
	@Before
	public void setUp() throws Exception {
		pavilionsByRadius = new PavilionsByRadius(connection);
		pavilionsByRouteNumberAndDirection = new PavilionsByRouteNumberAndDirection(connection);
	}

	@Test
	public void testPavilionsByRadius() {
		assertEquals(connection.getStoteles().size(),
				pavilionsByRadius.get("25.2", "54.7", 99999, PavilionsByRadius.Measurement.RADIUS).size());
	}
	
	@Test
	public void testPavilionsByRouteNumberAndDirection() {
		assertEquals(22, pavilionsByRouteNumberAndDirection.get("Autobusas 2G: Stotis - Santariškės").size());
		
	}

}
