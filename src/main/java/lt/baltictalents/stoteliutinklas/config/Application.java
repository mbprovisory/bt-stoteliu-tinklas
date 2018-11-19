package lt.baltictalents.stoteliutinklas.config;

import lt.baltictalents.stoteliutinklas.helper.MainProcess;

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
    	MainProcess.HandleArgs(args);
    }
}

