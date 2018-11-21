/**
 * 
 */
package lt.baltictalents.stoteliutinklas.operations;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

/**
 * @author rimantas
 *
 */
public class TestStationsByRoute {

    /**
     * @throws java.lang.Exception
     */
    StationsAndPavilionsByRouteNumber stations;
    @Before
    public void setUp() throws Exception {
	DataListFactory connection = new DataListFactory();
	stations = new StationsAndPavilionsByRouteNumber(connection);
    }

    @Test
    public void test() {
	List<Station> stByRoute = stations.get("16");
	assertEquals(117, stByRoute.size());
    }

}
