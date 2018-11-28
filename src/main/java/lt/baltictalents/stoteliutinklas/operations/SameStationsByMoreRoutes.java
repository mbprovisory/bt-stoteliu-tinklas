package lt.baltictalents.stoteliutinklas.operations;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

public class SameStationsByMoreRoutes {
	
	public DataListFactory connection;
	
	public SameStationsByMoreRoutes(DataListFactory connection) {
		this.connection = connection;
	}
	
	public Comparator<Station> compareRoutes = new Comparator<Station>() {

		@Override
		public int compare(Station s1, Station s2) {

			int n1 = s1.getRoutes().length;
			int n2 = s2.getRoutes().length;
			
			return n2 - n1;
		}
	};
	
	public List<Station> findMoreRoutes(int stoteliuSkaicius) {
			
			List<Station> data = connection.getStoteles();
			List<Station> name = new LinkedList<>();
			
			data.sort(compareRoutes);	
			
			for(int i = 0; i < stoteliuSkaicius; i++) {
				name.add(data.get(i));
				//System.out.println(name.get(i).getName() + ": " + name.get(i).getRoutes().length);
			}
			
			return name;
		}
		
	}
	


