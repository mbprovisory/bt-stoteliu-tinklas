package lt.baltictalents.stoteliutinklas.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
	
	//https://en.wikipedia.org/wiki/Haversine_formula
	public static double getDistanceBetweenTwoPointsInMeters(double lat1,double lon1,double lat2,double lon2)
	{
		double R = 6378.137; // Radius of earth in KM
		double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
		double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
	    Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c;
	    return d * 1000; // meters
	}

	public static String[] RegexParser(String args) 
	{
	    String re = "((\".+?\")|(?:\\S+))";
	    Pattern p = Pattern.compile(re,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(args);
	    List<String> sList = new ArrayList<String>();
	    while (m.find())
	    {
	    	String string = m.group(1);
	    	string = string.replace("\"", "");
	    	sList.add(string);
	    	
	    }
	    String[] ret = sList.toArray(new String[0]);
		return ret;
	}
}
