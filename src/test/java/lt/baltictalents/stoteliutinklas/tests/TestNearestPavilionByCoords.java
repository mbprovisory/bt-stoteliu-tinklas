package lt.baltictalents.stoteliutinklas.tests;

import static org.junit.Assert.*;

import lt.baltictalents.stoteliutinklas.data.beans.*;
import org.junit.Test;

import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.operations.NearestPavilionByCoords;

public class TestNearestPavilionByCoords {

	DataListFactory connection = new DataListFactory();
	NearestPavilionByCoords pavilionas = new NearestPavilionByCoords(connection);
	
	
	@Test
	public void testNearestPavilionByCoords() {
		assertEquals("Kalvarijos", pavilionas.get("25.28", "54.74").getName()); 
		assertEquals("Naujakiemio st.", pavilionas.get("25.2814121", "54.7238768").getName());
		assertEquals("Ąžuolyno st.", pavilionas.get("25.2353227", "54.7047766").getName());
		assertEquals("Ateities st.", pavilionas.get("25.2658484", "54.7374934").getName());
	}

	@Test
	public void testGet() {
		assertTrue(true);
	}
	
	@Test
	public void testGit() {
		assertFalse(false);
	}

}
