package lt.baltictalents.stoteliutinklas.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

/*sprint2 5)	Galimybė įvesti sąrašą maršrutų ir grąžinkite stoteles, kur jie susikerta. Šiuo atveju 
 * atspausdinkite ne paviljonus, o tik stotelių tekstinius pavadinimus (be koordinačių).
 */
public class SameStationsByCrossingRoutes {

	private DataListFactory connection;
	
	public SameStationsByCrossingRoutes(DataListFactory connection){
		
		this.connection = connection;
	}
	
	public List<String> get(String crossingRoutes1,String crossingRoutes2) { // paduodu marsrutu sarasa
		
		StationsAndPavilionsByRouteNumber stat = 
				new StationsAndPavilionsByRouteNumber(connection);//kveciu Rimanto metoda kuris grazina stoteles pavadinimus
		
		List<Station> data1 = stat.get(crossingRoutes1);
		List<Station> data2 = stat.get(crossingRoutes2);
		List<Station> stations = new LinkedList<Station>();
		
		for (Station st1: data1) {
			for (Station st2: data2){
				if (st1.equals(st2)) {
					stations.add(st1);
				}
			}
		}
		
		List<String> st = new ArrayList<>();
		 for (Station s: stations) {
			 st.add(s.getName());
		 }
		 
		return st;
	}
/*public List<String> get(String crossingRoutes1,String crossingRoutes2,String crossingRoutes3) { // paduodu marsrutu sarasa
		
		StationsAndPavilionsByRouteNumber stat = 
				new StationsAndPavilionsByRouteNumber(connection);//kveciu Rimanto metoda kuris grazina stoteles pavadinimus
		
		List<Station> data1 = stat.get(crossingRoutes1);
		List<Station> data2 = stat.get(crossingRoutes2);
		List<Station> stations = new LinkedList<Station>();
		List<Station> data3 = stat.get(crossingRoutes3);
		
		for (Station st1: data1) {
			for (Station st2: data2){
				for(Station st3: data3) {
					if (st1.equals(st2) && st1.equals(st3)) {
						stations.add(st1);
					}
				}
			}
		}
		
		List<String> st = new ArrayList<>();
		 for (Station s: stations) {
			 st.add(s.getName());
		 }
		 
		return st;
	}*/
}
