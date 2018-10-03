package lt.baltictalents.stoteliutinklas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ltbaltictaents.stoteliutinklas.implementacija.Factory;
import ltbaltictaents.stoteliutinklas.interfeisai.SampleService;

@RestController
public class StoteliuTinklasController {
	
	Factory factory = new Factory();
	
	@RequestMapping(path="/", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    public String homePage() {
    	return ((SampleService) factory.getSampleService()).getHello();
    }
	
}