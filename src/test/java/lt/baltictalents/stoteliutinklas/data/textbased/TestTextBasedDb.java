/**
 * 
 */
package lt.baltictalents.stoteliutinklas.data.textbased;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author rimantas
 *
 */
public class TestTextBasedDb {
    TextBasedDb txtdb;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	//txtdb = new TextBasedDb();
    }

    @Test
    public void test() {
	assertEquals(3, new TextBasedDb().createDb().size());
    }

}
