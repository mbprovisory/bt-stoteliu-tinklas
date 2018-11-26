package lt.baltictalents.stoteliutinklas.data.textbased;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lt.baltictalents.stoteliutinklas.data.beans.Route;

public class TestTextBRoutes {

    TextBasedRoutesDb routes = new TextBasedRoutesDb();
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
	assertEquals(92, routes.getRoutes().size());
	for(Route rt: routes.getRoutes()) {System.out.println(rt);}
	//fail("Not yet implemented");
    }

}
