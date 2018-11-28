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

import lt.baltictalents.stoteliutinklas.data.beans.Station;

public class TextBasedDb {
    private List<Station>  stoteles;
    private String uriString = "file:///home/rimantas/new.txt";
    public URI file = getURI(uriString) ;

    public TextBasedDb() {
	this.stoteles = createDb();
    }

    public String getUriString() {
        return this.uriString;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }

   /* public List<Station> createDb1(){
	List<Station> list = new ArrayList<Station>(2000);
	try (Stream<String> lines = Files.lines(Paths.get(file), Charset.defaultCharset())) {
	    lines.forEach(System.out::println);
	  } catch (IOException ex) {
	    	    ex.printStackTrace();
	}
	return list;
    } */
    
    public List<Station> createDb(){
  	List<Station> list = new ArrayList<Station>();
  	try (Stream<String> lines = Files.lines(Paths.get(file), Charset.defaultCharset())) {
  	    lines.forEach(line -> process(line, list));
  	  } catch (IOException ex) {
  	      	    ex.printStackTrace();
  	}
  	return list;
      }    

    private Object process(String line, List<Station> list) {
	/*char l = '"';
	char k = ' ';
	line = line.replace(l,k );
	String[] station = line.split(",");
	String[] routes = station[3].split(";");
	for(String st: station) {
	    st = st.trim();
	    //System.out.println(st);
	    }
	for(String rt: routes) {
	    rt = rt.trim();
	    //System.out.println("-------" + rt);
	}
	Station st = new Station(station[0], station[1],station[2], routes);*/
	list.add(new Station().getNew(line));
	return list;
    }

    private URI getURI(String string) {
	URI uri = null;
	try {
	    uri =  new URI(string);
	} catch (URISyntaxException ex) {
	    ex.printStackTrace();
	}
	return uri;
    }

    

    public List<Station> getStoteles() {
	return this.stoteles;
    }

}
