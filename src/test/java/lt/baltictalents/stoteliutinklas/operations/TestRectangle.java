package lt.baltictalents.stoteliutinklas.operations;

import static org.junit.Assert.*;
import org.junit.*;

import lt.baltictalents.stoteliutinklas.data.beans.Coordinates;

public class TestRectangle {
    
    Rectangle target = new Rectangle(new Coordinates("5","5"), new Coordinates("3","3"));
    Rectangle target1 = new Rectangle(new Coordinates("3","5"), new Coordinates("5","3"));
    
    @Test
    public void testContainRect() {
	Boolean containsP = this.target.contains(new Coordinates("4","4"));
	assertTrue(containsP);
	Boolean containsP1 = this.target.contains(new Coordinates("6","4"));
	assertFalse(containsP1);
	Boolean containsP3 = this.target1.contains(new Coordinates("6","4"));
	assertFalse(containsP3);
	Boolean containsP4 = this.target1.contains(new Coordinates("4","4"));
	assertTrue(containsP4);
    }

}
