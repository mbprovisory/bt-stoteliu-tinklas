package lt.baltictalents.stoteliutinklas.pavyzdys;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestDvejuTekstuSujungejas {

	@Test
	public void test() {
		String tekstas1 = "T";
		String tekstas2 = "A";
		DvejuTekstuSujungejas sujungejas = new DvejuTekstuSujungejas();
		assertEquals("TA",sujungejas.sujunk(tekstas1,tekstas2));
	}
}
