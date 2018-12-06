package lt.baltictalents.stoteliutinklas.data.beans;

import java.util.Date;

public class Station implements Creatable {
	String name;
	String longitude;
	String latitude;
	String[] routes;
	Date date = null;

	public Station(String name, String lon, String lat, String[] routes) {
		super();
		this.name = name;
		this.longitude = lon;
		this.latitude = lat;
		this.routes = routes;
	}
	public Station(String name, String lon, String lat, String[] routes, Date date) {
		this(name, lon, lat, routes);
		this.date = date;
		
	}
	
	

	public Station() {
	    // TODO Auto-generated constructor stub
	}



	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String[] getRoutes() {
		return routes;
	}

	public void setRoutes(String[] routes) {
		this.routes = routes;
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder(getName());
	    sb.append("    longit.: ").append(getLongitude()).append(";  latit.: ").append(getLatitude());
	    return sb.toString();
	}
	
	public String toLongString() {
	    StringBuilder sb = new StringBuilder(this.toString());
	    for(String s: getRoutes()) {sb.append("\n   -   ").append(s);}
	    return sb.toString();
	}
	
	public double getDistanceDeg(Coordinates coordinates) {
	    double stationLongitude = Double.parseDouble(this.getLongitude());
	    double deltaLongitude = coordinates.getLongitude() - stationLongitude;
	    double stationLatitude = Double.parseDouble(this.getLatitude());
	    double deltaLatitude = coordinates.getLatitude() - stationLatitude;
	    deltaLongitude = deltaLongitude * Math.cos(stationLatitude * 2 * Math.PI /360);//correction for latitude
	    
	    double distance = deltaLongitude * deltaLongitude + deltaLatitude * deltaLatitude;
	    distance = Math.sqrt(distance);
	    return distance;
	    }
	
	
	 @Override
	    public boolean equals(Object obj) 
	    { 
	          
		    // if both the object references are  
		    // referring to the same object. 
		    if(this == obj) return true; 
	          
	        if(obj == null || obj.getClass()!= this.getClass()) 
	            return false; 
	          
	        // type casting of the argument.  
	        Station station = (Station) obj; 
	          
	        // comparing the state of argument with  
	        // the state of 'this' Object. 
	        return (station.name.equals(this.name)  && 
	        		station.latitude.equals(this.latitude) &&
	        		station.longitude.equals(this.longitude)); 
	    } 
	      
	    @Override
	    public int hashCode() 
	    { 
	        return (this.name+this.latitude+this.longitude).hashCode();
	    }



	    @Override
	    public Station getNew(String line) {
		char l = '"';
		char k = ' ';
		line = line.replace(l,k );
		String[] station = line.split(",");
		String[] routes = station[3].split(";");
		for(String st: station) {
		    st = st.trim();
		    //System.out.println(st);
		    }
		for(String rt: routes) {
		    rt = rt.trim();
		    //System.out.println("-------" + rt);
		}
		return  new Station(station[0], station[1],station[2], routes);
	    } 

}