package lt.baltictalents.stoteliutinklas.data.layer;

import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.hardcode.HardCodedDb;

public class DataListFactory {
    
    private List<Station> stoteles;
    
    public List<Station> getStoteles(){

		this.stoteles = new HardCodedDb().getStoteles();
		return stoteles;
    }

}
