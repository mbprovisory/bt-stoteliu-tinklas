package lt.baltictalents.stoteliutinklas.config;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.helper.MainProcess;
import lt.baltictalents.stoteliutinklas.operations.*;
import java.util.List;
import java.util.Optional;

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
        //SpringApplication.run(new Class[] { Application.class }, args);
    	//MainProcess.HandleArgs(args);
    	
    	Station listas = new NearestPavilionByCoords(new DataListFactory()).get("25.28", "54.74");
    	System.out.println("----------------------\n" + listas + "\n----------------------" );
    }
}

