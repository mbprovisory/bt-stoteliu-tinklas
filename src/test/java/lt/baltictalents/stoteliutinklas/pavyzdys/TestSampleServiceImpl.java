package lt.baltictalents.stoteliutinklas.pavyzdys;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSampleServiceImpl {
	
	SampleServiceImpl target = new SampleServiceImpl();

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetSampleResponse() {
		assertEquals("Zirkles", this.target.getSampleResponse().getWinningSign());
	}

}
