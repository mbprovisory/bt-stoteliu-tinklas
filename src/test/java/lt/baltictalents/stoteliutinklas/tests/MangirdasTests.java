package lt.baltictalents.stoteliutinklas.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import lt.baltictalents.stoteliutinklas.config.Application;
import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.helper.Helper;
import lt.baltictalents.stoteliutinklas.helper.MainProcess;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRadius;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRouteNumberAndDirection;
import lt.baltictalents.stoteliutinklas.operations.SameStationPavilionsByRange;

public class MangirdasTests {
	//
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
		
		assertEquals(0,
				pavilionsByRadius.get("25.2", "54.7", 0.0000001, PavilionsByRadius.Measurement.RADIUS).size());
		assertEquals(0,
				pavilionsByRadius.get("25.2", "54.7", 1, PavilionsByRadius.Measurement.METERS).size());
		//boolean cond1 = connection.getStoteles().size()>=pavilionsByRadius.get("25.2", "54.7", 0.00001, PavilionsByRadius.Measurement.RADIUS).size();
		//assertTrue(cond1);
		//assertTrue(connection.getStoteles().size()>=pavilionsByRadius.get("25.2", "54.7", 20, PavilionsByRadius.Measurement.METERS).size());
	}
	
	@Test
	public void testPavilionsByRouteNumberAndDirection() {
		assertEquals(22, pavilionsByRouteNumberAndDirection.get("Autobusas 2G: Stotis - Santariškės").size());
	}
	
	@Test
	public void testSameStationPavilionsByRange() {
		
		DataListFactory mockConnection = new DataListFactory();
		SameStationPavilionsByRange sameStationPavilionsByRange = new SameStationPavilionsByRange(mockConnection);
		mockConnection.setConnectionType(3); //VIRTUALDB
		List<Station> mockList = new LinkedList<Station>();
		//grid mocks
		mockList.add(new Station("1x1", "1", "1", null));
		mockList.add(new Station("2x1", "2", "1", null));
		mockList.add(new Station("3x1", "3", "1", null));
		mockList.add(new Station("1x2", "1", "2", null));
		mockList.add(new Station("2x2", "2", "2", null));
		mockList.add(new Station("3x2", "3", "2", null));
		mockList.add(new Station("1x3", "1", "3", null));
		mockList.add(new Station("2x3", "2", "3", null));
		mockList.add(new Station("3x3", "3", "3", null));
		
		//dupe mocks
		mockList.add(new Station("1x1", "1.00001", "1.00001", null));
		mockList.add(new Station("1x1", "1.00002", "1.00002", null));
		mockList.add(new Station("3x2", "3.00001", "2.00001", null));
		
		//out mocks
		Set<Station> mockNeededOutput = new HashSet<Station>();
		mockNeededOutput.add(new Station("1x1", "1", "1", null));
		mockNeededOutput.add(new Station("1x1", "1.00001", "1.00001", null));
		mockNeededOutput.add(new Station("1x1", "1.00002", "1.00002", null));
		mockNeededOutput.add(new Station("3x2", "3", "2", null));
		mockNeededOutput.add(new Station("3x2", "3.00001", "2.00001", null));
		
		mockConnection.SetStoteles(mockList);
		//new tests
		Set<Station> mockOutput = sameStationPavilionsByRange.get(300);
		boolean bool = mockOutput.contains(new Station("1x1", "1.00001", "1.00001", null));
		assertEquals(5, mockOutput.size());
		
		assertTrue(mockOutput.containsAll(mockNeededOutput));
		//assertEquals(expected, actual);
		
		//old tests
		assertNotEquals(connection.getStoteles().size(), sameStationPavilionsByRange.get(99999));
		assertNotEquals(connection.getStoteles().size(), sameStationPavilionsByRange.get(30));

	}
	
	@Test
	public void testHelper_RegexpParser()
	{
		String input = "\"first arg\" second 3 4th";
		String[] target = Helper.RegexParser(input);
		String output="";
		for(String s : target) output += s + " ";
		output = output.trim();
		assertTrue(input.equals(output));
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
