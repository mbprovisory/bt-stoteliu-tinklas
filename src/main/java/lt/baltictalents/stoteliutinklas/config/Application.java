package lt.baltictalents.stoteliutinklas.config;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
	"lt.*"
)
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(new Class[] { Application.class }, args);
    	ArrayList<Integer> list = new ArrayList<Integer>();
	    for (int i = 0; i < 6; i++){
	        list.add(i);
	    }
	    int x = 0;
	    Iterator<Integer> it = list.iterator();
	    while (it.hasNext()){
	        x +=it.next();
	    }
	    System.out.println(x);
    }
}

