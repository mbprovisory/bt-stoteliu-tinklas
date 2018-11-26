package lt.baltictalents.stoteliutinklas.data.textbased;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTextBRoutes {

    TextBasedRoutesDb routes = new TextBasedRoutesDb();
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
	assertEquals(92, routes.getRoutes().size());
	//fail("Not yet implemented");
    }

}
