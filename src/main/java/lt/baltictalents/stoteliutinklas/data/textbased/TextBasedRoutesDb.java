package lt.baltictalents.stoteliutinklas.data.textbased;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lt.baltictalents.stoteliutinklas.data.beans.Route;
//import lt.baltictalents.stoteliutinklas.data.beans.Station;

public class TextBasedRoutesDb {
    private List<Route>  routes;
    private String uriString = "file:///home/rimantas/autobusai.txt";
    public URI file = getURI(uriString) ;



    public String getUriString() {
	return this.uriString;
    }

    public void setUriString(String uriString) {
	this.uriString = uriString;
    }

    public List<Route> createDb(){
	List<Route> list = new ArrayList<Route>();
	try (Stream<String> lines = Files.lines(Paths.get(file), Charset.defaultCharset())) {
	    lines.forEach(line -> process1(line, list));
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	return list;
    } 

    
    
    private Object process1(String line, List<Route> list) {
	list.add(new Route().getNew(line));
	return list;
    }
/*
    public List<Route> createDb2(){
	List<Route> list = new ArrayList<Route>();
	try (Stream<String> lines = Files.lines(Paths.get(file), Charset.defaultCharset())) {
	    lines.forEach(line -> process(line, list));
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	return list;
    }    

    private Object process(String line, List<Route> list) {
	char l = '"';
	char k = ' ';
	line = line.replace(l,k );
	String[] route = line.split(",");
	for(String st: route) {
	    st = st.trim();
	   // System.out.print(st + "   ++   ");
	}
	   // System.out.println("  ------------------   ");

	Route st = new Route(route[0],route[1],route[2]);
	//System.out.println(st);
	list.add(st);
	return list;
    }*/

    private URI getURI(String string) {
	URI uri = null;
	try {
	    uri =  new URI(string);
	} catch (URISyntaxException ex) {
	    ex.printStackTrace();
	}
	return uri;
    }

    public TextBasedRoutesDb() {
	this.routes = createDb();
    }

    public List<Route> getRoutes() {
	return this.routes;
    }

}
