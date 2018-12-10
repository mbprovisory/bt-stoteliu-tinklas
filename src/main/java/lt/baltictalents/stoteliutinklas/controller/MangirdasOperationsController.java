package lt.baltictalents.stoteliutinklas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.layer.DataListFactory;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRadius;
import lt.baltictalents.stoteliutinklas.operations.PavilionsByRadius.Measurement;
import lt.baltictalents.stoteliutinklas.pavyzdys.SampleResponse;
import lt.baltictalents.stoteliutinklas.pavyzdys.SampleService;

@RestController
public class MangirdasOperationsController {
	
	public class JSONResponse {
		public String name; // laimintis, zenklas, pvz. sulinys ar zirkles
	}
	
	@Autowired
	SampleService sampleService;
	
	/*
	 {
	 	"Clon":"25",
	 	"Clat":"54",
	 	"radius":100000,
	 	"measurement":"meters"
	 }
	 
	 */
	@RequestMapping(path="/PavilionsByRadius", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    public Map<String, String> sampleResponse(@RequestBody String body) {
		DataListFactory connection = new DataListFactory();
		connection.setConnectionType(1); //HARDCODED DB
		PavilionsByRadius pavilionsByRadius = new PavilionsByRadius(connection);
		
		
		JSONObject jsonObj=new JSONObject(body);
		String Clon=jsonObj.getString("Clon");
		String Clat=jsonObj.getString("Clat");
		Double radius=jsonObj.getDouble("radius");
		String measurement=jsonObj.getString("measurement");
		
		Measurement measure;
		if(measurement.equalsIgnoreCase("radius")) measure = Measurement.RADIUS;
		else measure = Measurement.METERS;
		
		List<Station> results = pavilionsByRadius.get(Clon, Clat, radius, measure);
		
		HashMap<String, String> map = new HashMap<>();
		
	    int line = 0;
	    for(Station s : results)
	    {	
	    	map.put(Integer.toString(line), s.toString());
	    	line++;
	    }
	    map.put("clon", Clon);
	    map.put("clat", Clat);
	    map.put("radius", Double.toString(radius));
	    map.put("measurement", measurement);
	    return map;
		
		
    	//return this.sampleService.getSampleResponse();
    }
	
}