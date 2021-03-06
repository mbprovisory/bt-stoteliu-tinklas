package lt.baltictalents.stoteliutinklas.data.beans;

import java.util.regex.Matcher;
import  java.util.regex.Pattern;

public class Route implements Creatable {

    private String routeMeanAndNb;
    private int routeNb;
    private String startStation;
    private String endStation;
    private String transportMean;
    private int transportMeanConst;
    
    public static final int TROLLEYBUS = 1;
    public static final int BUS = 2;
    public static final int EXPRESSBUS = 3;
    private static final int OTHER = 0;
    
    public Route() {}
    
    public Route(String routeMeanAndNb, String startStation, String endStation) {
	this.routeMeanAndNb = routeMeanAndNb;
	this.startStation = startStation;
	this.endStation = endStation;
	
	this.transportMeanConst  = setMean(routeMeanAndNb);
	this.routeNb = setNb(routeMeanAndNb);
	this.transportMean = setMeanString(routeMeanAndNb);
    }
    
    
    public Route getNew(String line) {
	char l = '"';
	char k = ' ';
	line = line.replace(l,k );
	String[] route = line.split(",");
	for(String st: route) {
	    st = st.trim();
	    //System.out.print(st + "   ++   ");
	}
	    //System.out.println("  ------------------   ");
	return new Route(route[0],route[1],route[2]);
	    }
    
    
    private int setNb(String meanAndNb) {
	int nb = -1;
	Pattern pt = Pattern.compile("\\d+");
	Matcher mt = pt.matcher(meanAndNb);
	if(mt.find()&&mt.groupCount()<1) {
	    nb = Integer.parseInt(mt.group());
	    //System.out.println(" gr.count  "   +   mt.groupCount());
	} else if(mt.groupCount()>=1) {System.out.println("Blogas eilutes formatas: " + meanAndNb);}
	return nb;
    }
    
    private String setMeanString(String meanAndNb) {
	String mean = null;
	Pattern pt = Pattern.compile("\\D+");
	Matcher mt = pt.matcher(meanAndNb);
	if(mt.find()&&mt.groupCount()<1) {
	    mean = mt.group();
	}else if(mt.groupCount()>=1) {System.out.println("Blogas eilutes formatas: " + meanAndNb);}
	return mean;
    }


    private int setMean(String mean) {
	if(mean.contains("roleib")) {
	     return TROLLEYBUS;
	    }else if (mean.contains("reit")) {
		return EXPRESSBUS;
	    }else if (mean.contains("utob")) {
		return BUS;
	    } else return OTHER;
    }

    public String getRouteMeanAndNb() {
        return this.routeMeanAndNb;
    }

    public String getStartStation() {
        return this.startStation;
    }

    public String getEndStation() {
        return this.endStation;
    }
    
    public String toString() {
	return transportMean +  transportMeanConst  + " -- " + routeNb + "  --  " + startStation + "   "  +  endStation;
    }

    public int getTransportMeanConst() {
        return this.transportMeanConst;
    }    
    
    public String getTransportMean() {
        return this.transportMean;
    }
    
}
