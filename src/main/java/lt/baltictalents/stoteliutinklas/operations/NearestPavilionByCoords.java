package lt.baltictalents.stoteliutinklas.operations;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

import java.util.Comparator;
import java.util.function.Function;

import lt.baltictalents.stoteliutinklas.data.beans.Coordinates;
import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;

public class NearestPavilionByCoords {

	public DataListFactory connection;
	
	public NearestPavilionByCoords(DataListFactory connection) {
		this.connection = connection;
	}
	
	public Station get(String lon, String lat) {
		Coordinates kord = new Coordinates(lon, lat);
		//Function<Station, double> mapperDist(st -> st.getDistanceDeg(new Coordinates(lon, lat)));
		Optional<Double> sarasas = connection.getStoteles().stream().map(st->st.getDistanceDeg(kord)).collect(minBy(Comparator.naturalOrder()));
		List<Double> sarasas1 = connection.getStoteles().stream().map(st->st.getDistanceDeg(kord)).collect(toList());
		List<Station> sarasas2 = connection.getStoteles().stream().sorted((st1, st2) -> { return (int)((st1.getDistanceDeg(kord)-st2.getDistanceDeg(kord))*1000);}).collect(toList());
		Optional<Station> sarasas3 = connection.getStoteles().stream().collect(minBy((st1, st2) -> { return (int)((st1.getDistanceDeg(kord)-st2.getDistanceDeg(kord))*1000);})); 
		Station nearest = sarasas3.get();
//		for (Station eile: sarasas3) {
//			System.out.println(eile);
//		}
		System.out.println(sarasas3);
		return nearest;
	}

//	private double getDistance(Coordinates kord) {
//
//		
//		
//		Station st = null;
//		return st.getDistanceDeg(kord);
//	}
}
