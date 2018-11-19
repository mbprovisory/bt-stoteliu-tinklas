package lt.baltictalents.stoteliutinklas.operations;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
/*
 * 9) Įvedus maršruto numerį (tiek autobusu, tiek troleibuso) ir kryptį, grąžinti visus tos krypties 
 * paviljonus, kuriuose stoja maršrutas.
 */
public class PavilionsByRouteNumberAndDirection 
{
	protected DataListFactory connection;
	
	public PavilionsByRouteNumberAndDirection(DataListFactory connection)
	{
		this.connection = connection;
	}
	
	//E.g. "Autobusas 2G: Stotis - Santariškės"
	public List<Station> get(String routeNameAndDirection) 
	{
		//TODO string normalization to class
		routeNameAndDirection = routeNameAndDirection.toLowerCase(Locale.forLanguageTag("lt"));
		
		List<Station> data = connection.getStoteles();
		List<Station> ret = new LinkedList<>();
		String[] routes = null;
		
		for (Station station : data) 
		{
			routes = station.getRoutes();
			for(int i = 0, bContinue=1; (i!=routes.length) && (bContinue==1); i++)
			{
				if (routes[i].toLowerCase(Locale.forLanguageTag("lt")).equals(routeNameAndDirection))
				{
					ret.add(station);
					bContinue = 0;
				}
			}
		}
		return ret;
	}
	
}  
