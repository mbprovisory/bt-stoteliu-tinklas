package lt.baltictalents.stoteliutinklas.operations;

import java.util.LinkedList;
import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.hardcode.HardCodedDb;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

/*1)Įvedus kvadrato koordinates, t.y. 4 skaičius, kur pirmi du skaičiai būtų kvadrato kairys 
 * viršutinis kampas, o paskutiniai du – kvadrato dešinys apatinis kampas, atspausdinti visus 
 * stotelių paviljonus tame kvadrate.
 */
public class PavilionsByRectangle {
	DataListFactory connection;
	public PavilionsByRectangle(DataListFactory connection)
	{
		this.connection = connection;
	}
	
	public List<Station> get(String lon, String lat,String lon1, String lat1) {

		List<Station> fromDb = connection.getStoteles();
	List<Station> listStation = new LinkedList<>();

	for (Station l : fromDb) {
		double x0,y0,x1,y1,xx,yy;
		
		x0 = Double.parseDouble(lon);
		x1 = Double.parseDouble(lon1);
		y0 = Double.parseDouble(lat);
		y1 = Double.parseDouble(lat1);
		xx = Double.parseDouble(l.getLongitude());
		yy = Double.parseDouble(l.getLatitude());
		if  (((x0 <= xx) && (xx <= x1))&& (((y0 <= yy) && (yy <= y1)))) {
		
		listStation.add(l);
		}
	}
	return listStation;
	}
	
}  
