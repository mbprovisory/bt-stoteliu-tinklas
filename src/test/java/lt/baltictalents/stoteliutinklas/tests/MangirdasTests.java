package lt.baltictalents.stoteliutinklas.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lt.baltictalents.stoteliutinklas.config.Application;
import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.helper.MainProcess;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRadius;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRouteNumberAndDirection;
import lt.baltictalents.stoteliutinklas.operations.SameStationPavilionsByRange;

public class MangirdasTests {
	
	Application application;
	MainProcess mainProcess;
	
	DataListFactory connection = new DataListFactory();
	PavilionsByRadius pavilionsByRadius;
	PavilionsByRouteNumberAndDirection  pavilionsByRouteNumberAndDirection;
	SameStationPavilionsByRange sameStationPavilionsByRange;
	
	@Before
	public void setUp() throws Exception {
		pavilionsByRadius = new PavilionsByRadius(connection);
		pavilionsByRouteNumberAndDirection = new PavilionsByRouteNumberAndDirection(connection);
		sameStationPavilionsByRange = new SameStationPavilionsByRange(connection);
	}

	@Test
	public void testPavilionsByRadius() {
		assertEquals(connection.getStoteles().size(),
				pavilionsByRadius.get("25.2", "54.7", 99999, PavilionsByRadius.Measurement.RADIUS).size());
		assertEquals(connection.getStoteles().size(),
				pavilionsByRadius.get("25.2", "54.7", 99999, PavilionsByRadius.Measurement.METERS).size());
		
//		assertNotEquals(connection.getStoteles().size()+1,
//				pavilionsByRadius.get("25.2", "54.7", 0.00001, PavilionsByRadius.Measurement.RADIUS).size());
//		assertNotEquals(connection.getStoteles().size()+1,
//				pavilionsByRadius.get("25.2", "54.7", 20, PavilionsByRadius.Measurement.METERS).size());
		boolean cond1 = connection.getStoteles().size()>=pavilionsByRadius.get("25.2", "54.7", 0.00001, PavilionsByRadius.Measurement.RADIUS).size();
		assertTrue(cond1);
		assertTrue(connection.getStoteles().size()>=pavilionsByRadius.get("25.2", "54.7", 20, PavilionsByRadius.Measurement.METERS).size());
	}
	
	@Test
	public void testPavilionsByRouteNumberAndDirection() {
		assertEquals(22, pavilionsByRouteNumberAndDirection.get("Autobusas 2G: Stotis - Santariškės").size());
	}
	
	@Test
	public void testSameStationPavilionsByRange() {
		assertNotEquals(connection.getStoteles().size(), sameStationPavilionsByRange.get(99999));
		assertNotEquals(connection.getStoteles().size(), sameStationPavilionsByRange.get(30));
		
	}
	
	@Test
	public void testApplicationEntryPoint() { //Issues
	//	String[] args = {"PavilionsByName", "Kareiv"};
		//Application.main(args, true);
		
	}
	
	
//	@Test
//	public void testMainProcess(){
//		//tests from interactive shell
//		String[] args = {"PavilionsByName", "Kareiv"};
//		//App
//	}
//	
//	@Test
//	public void testMainProcess_SelectOperations() {
//		String[] args = {"PavilionsByName", "Kareiv"};
//		//mainProcess.selectOperations no way to enter
//	}

}
