package lt.baltictalents.stoteliutinklas.operations;

import java.util.LinkedList;
import java.util.List;
import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.helper.Helper;
/*
 * 5) Įvedus apskritimo duomenis, t.y. centro coordinates ir spindulį, grąžinti visus paviljonus 
 * esančius apskritime, t.y. paviljonus, kurie yra nutolę nuo apskritimo centro per mažesnį atstumą, 
 * nei spindulys.
 */
public class PavilionsByRadius 
{
	public enum Measurement {RADIUS, METERS}
	
	protected DataListFactory connection;
	
	public PavilionsByRadius(DataListFactory connection)
	{
		this.connection = connection;
	}
	
	public List<Station> get(String Clon, String Clat, double radius, Measurement measurement) 
	{
		List<Station> data = connection.getStoteles();
		List<Station> ret = new LinkedList<>();
		radius *= radius;
		double expr=0;
		
		for (Station station : data) 
		{
			if(measurement == Measurement.RADIUS)
			{
				//TODO: expr to new class
				expr = Math.pow(Double.parseDouble(station.getLongitude()) - Double.parseDouble(Clon),2)
						+ Math.pow(Double.parseDouble(station.getLatitude()) - Double.parseDouble(Clat),2);
				if (expr<radius) ret.add(station);
			}
			else
			{
				expr = Helper.getDistanceBetweenTwoPointsInMeters(
						Double.parseDouble(station.getLatitude()), 
						Double.parseDouble(station.getLongitude()), 
						Double.parseDouble(Clat), 
						Double.parseDouble(Clon));
				if (expr<radius) ret.add(station);
			}
		}
		return ret;
	}
	
}  
