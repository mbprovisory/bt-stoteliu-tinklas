package lt.baltictalents.stoteliutinklas.data.layer;

import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Route;
//import lt.baltictalents.stoteliutinklas.data.beans.Station;
//import lt.baltictalents.stoteliutinklas.data.hardcode.HardCodedDb;
//import lt.baltictalents.stoteliutinklas.data.textbased.TextBasedDb;
import lt.baltictalents.stoteliutinklas.data.textbased.TextBasedRoutesDb;

public class RoutesListFactory {
    public static final int HARDCODEDDB = 1;
    public static final int TEXTBASEDDB = 2;
    public static final int VIRTUALDB = 3;
    private int connectionType = HARDCODEDDB;
    private List<Route> routes;    

    public int getConnectionType() {
        return this.connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

    public List<Route> getRoutes(){

	switch(connectionType) {
	case TEXTBASEDDB: 
	    this.routes = new TextBasedRoutesDb().getRoutes();
	    return routes;

//	case someotherTEXTBASEDDB:
//	    this.stoteles = new TextBasedDb().getStoteles();
//	    return stoteles;
	    
	case VIRTUALDB:
	    return this.routes;
	    
	default: 
	    this.routes = new TextBasedRoutesDb().getRoutes();
	    return routes;
	}
    }
    
    //netrinti
//    public void SetStoteles(List<Station> stoteles) {
//    	this.stoteles = stoteles;
//    	this.connectionType = RoutesListFactory.VIRTUALDB;
//    }
}
