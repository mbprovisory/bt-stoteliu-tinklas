package lt.baltictalents.stoteliutinklas.operations;

import lt.baltictalents.stoteliutinklas.data.beans.Coordinates;

public class Rectangle {
        
    Coordinates corner1; // TODO public, private, package?
    Coordinates corner2;
    Coordinates poI;

    public Rectangle(Coordinates corner1, Coordinates corner2) {
	this.corner1 = corner1;
	this.corner2 = corner2;
    }

    public boolean contains(Coordinates poI) {
	boolean containByLatitude1 = corner1.getLatitude() <= poI.getLatitude() && poI.getLatitude() <= corner2.getLatitude();
	boolean containByLatitude2 = corner1.getLatitude() >= poI.getLatitude() && poI.getLatitude() >= corner2.getLatitude();
	boolean containByLatitude = containByLatitude1 || containByLatitude2;
	boolean containByLongitude1 = corner1.getLongitude() <= poI.getLongitude() && poI.getLongitude() <= corner2.getLongitude();
	boolean containByLongitude2 = corner1.getLongitude() >= poI.getLongitude() && poI.getLongitude() >= corner2.getLongitude();
	boolean containByLongitude = containByLongitude1 || containByLongitude2;
	
	return containByLatitude && containByLongitude;
    }
}
