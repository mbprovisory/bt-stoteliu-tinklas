package lt.baltictalents.stoteliutinklas.data.beans;

public class Coordinates {
    double longitude;
    double latitude;
    
    //public Coordinates() {}
    
    public Coordinates(String logitudeString, String latitudeString) {
	this.latitude = Double.parseDouble(latitudeString);
	this.longitude = Double.parseDouble(logitudeString);
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
