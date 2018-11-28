package lt.baltictalents.stoteliutinklas.operations;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.helper.Helper;
/*
 *6) Galimybė gauti stotelių pavadinimus, kurių paviljonai nutolę vienas nuo kito per mažesnį atstumą, 
 *nei nurodyta įvestyje. Pvz.: 1) Įvedu 30 2) Man parašo visas stoteles ir jų paviljonus, jei visi paviljonai
 *nuo bet kurio kito nutolę maximum per 30 metrų. Rezultatai turėtų būti atspausdinti grupėmis, t.y. 
 *pradžioje vienos stotelės paviljonai, po to kitos ir t.t.
 */
public class SameStationPavilionsByRange 
{
	protected DataListFactory connection;
	
	public SameStationPavilionsByRange(DataListFactory connection)
	{
		this.connection = connection;
	}
	
	public Set<Station> get(int range) 
	{
		List<Station> stations = connection.getStoteles();
		Set<Station> ret = new HashSet<Station>();
		Set<String> checked = new HashSet<String>();
		PavilionsByName pbn = new PavilionsByName(connection);
		List<Station> target = null;
		String currName;
		
		for(Station s : stations)
		{
			currName = s.getName();
			target = pbn.get(currName);
			
			for(int i=0; i < target.size(); i++)
			{
				for(int j=i+1; j < target.size(); j++)
				{
					double d1 = Double.parseDouble(target.get(i).getLatitude());
					double d2 = Double.parseDouble(target.get(i).getLongitude());
					double d3 = Double.parseDouble(target.get(j).getLatitude());
					double d4 = Double.parseDouble(target.get(j).getLongitude());
					
					if (Helper.getDistanceBetweenTwoPointsInMeters(d1,d2,d3,d4)<=range)
					{
						ret.add(target.get(i));
						ret.add(target.get(j));
					}
					
				}
			}
				
			
		}
	 
		
		
		
		return ret;
	}
	
}  
