package lt.baltictalents.stoteliutinklas.api;

import java.util.List;

import lt.baltictalents.stoteliutinklas.beans.Square;
import lt.baltictalents.stoteliutinklas.data.beans.Station;

public interface BusStopSearchService {
	public List<Station> getBusStopsInSquare(Square square);
}
