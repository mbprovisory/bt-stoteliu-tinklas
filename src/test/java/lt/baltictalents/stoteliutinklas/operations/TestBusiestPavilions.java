package lt.baltictalents.stoteliutinklas.operations;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

public class TestBusiestPavilions {

    BusiestPavilionByRectangle pavilion;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	DataListFactory connection = new DataListFactory();
	pavilion = new BusiestPavilionByRectangle(connection);
    }

    @Test
    public void test() {
	List<Station> busiestPav = pavilion.get("25.2992819", "54.6895504", "25.3025908", "54.7391209");
	assertEquals(2, busiestPav.size());
	//fail("Not yet implemented");
	//.collect(minBy(comparator))          čia comparator (st1,st2)-> {return (int)((st1.getDistDeg(kord)-st1.getDistDeg(kord))*100000);}  čia kord: Coordinates kord = new Coordinates(long,lat);
    }

}
