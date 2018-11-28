package lt.baltictalents.stoteliutinklas.data.textbased;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lt.baltictalents.stoteliutinklas.data.beans.Route;
import lt.baltictalents.stoteliutinklas.data.layer.RoutesListFactory;

public class TestTextBRoutes {

    RoutesListFactory routes = new RoutesListFactory();
    
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
