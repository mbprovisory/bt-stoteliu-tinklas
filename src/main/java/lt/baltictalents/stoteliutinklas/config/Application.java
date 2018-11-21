
package lt.baltictalents.stoteliutinklas.config;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.helper.MainProcess;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRectangle;
import lt.baltictalents.stoteliutinklas.operations.RoutesNumbersByStationName;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
	"lt.*"
)
public class Application {

    public static void main(String[] args) {
    	//force push
        SpringApplication.run(new Class[] { Application.class }, args);
    	MainProcess.HandleArgs(args);
    	// cia pridariau pakeitimu
    	
//    	List<Station> stByRectangle = new PavilionsByRectangle(new DataListFactory()).get("25.2500000", "54.7500000","25.2807679", "54.7553245");
//    	for (Station st: stByRectangle) System.out.println(st.getName());
    	
//    	List<String[]> routesByStationName = new RoutesNumbersByStationName(new DataListFactory()).get("Vok");
//    	for (String[] rt: routesByStationName) System.out.println(rt.length);
    }
}

