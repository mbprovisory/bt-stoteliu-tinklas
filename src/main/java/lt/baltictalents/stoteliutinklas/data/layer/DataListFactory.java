package lt.baltictalents.stoteliutinklas.data.layer;

import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.hardcode.HardCodedDb;
import lt.baltictalents.stoteliutinklas.data.textbased.TextBasedDb;

public class DataListFactory {
    public static final int HARDCODEDDB = 1;
    public static final int TEXTBASEDDB = 2;
    private int connectionType = HARDCODEDDB;
    private List<Station> stoteles;    

    public int getConnectionType() {
        return this.connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

    public List<Station> getStoteles(){

	switch(connectionType) {
	case HARDCODEDDB: 
	    this.stoteles = new HardCodedDb().getStoteles();
	    return stoteles;

	case TEXTBASEDDB:
	    this.stoteles = new TextBasedDb().getStoteles();
	    return stoteles;
	    
	default: 
	    this.stoteles = new HardCodedDb().getStoteles();
	    return stoteles;
	}
    }
}
