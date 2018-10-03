package lt.baltictalents.stoteliutinklas.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lt.baltictalents.stoteliutinklas.implementacija.Factory;
import lt.baltictalents.stoteliutinklas.interfeisai.SampleService;

@SpringBootApplication
@ComponentScan(
	value= {"lt.baltictalents.stoteliutinklas.controller", "lt.baltictalents.stoteliutinklas.config"}
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(new Class[] { Application.class }, args);
    }
}

