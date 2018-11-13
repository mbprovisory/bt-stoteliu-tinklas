package lt.baltictalents.stoteliutinklas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.stoteliutinklas.pavyzdys.SampleResponse;
import lt.baltictalents.stoteliutinklas.pavyzdys.SampleService;

@RestController
public class StoteliuTinklasController {
	
	@Autowired
	SampleService sampleService;
	
	@RequestMapping(path="/", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    public SampleResponse sampleResponse() {
    	return this.sampleService.getSampleResponse();
    }
	
}