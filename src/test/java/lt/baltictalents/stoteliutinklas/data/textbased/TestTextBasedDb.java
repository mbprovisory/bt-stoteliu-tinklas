/**
 * 
 */
package lt.baltictalents.stoteliutinklas.data.textbased;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

/**
 * @author rimantas
 *
 */
public class TestTextBasedDb {
    public DataListFactory connection = new DataListFactory();
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	//txtdb = new TextBasedDb();
    }

    @Test
    public void test() {
	connection.setConnectionType(DataListFactory.TEXTBASEDDB);
	assertEquals(3, connection.getStoteles().size());
    }

}
