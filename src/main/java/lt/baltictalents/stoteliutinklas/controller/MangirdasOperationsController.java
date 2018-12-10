package lt.baltictalents.stoteliutinklas.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.stoteliutinklas.pavyzdys.SampleResponse;
import lt.baltictalents.stoteliutinklas.pavyzdys.SampleService;

@RestController
public class MangirdasOperationsController {
	
	public class JSONResponse {
		public String name; // laimintis, zenklas, pvz. sulinys ar zirkles
	}
	
	@Autowired
	SampleService sampleService;
	
	@RequestMapping(path="/greeting", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    public Map<String, String> sampleResponse(@RequestBody String body) {
		
		JSONObject jsonObj=new JSONObject(body);
		String name=jsonObj.getString("name");
		//return “success”;
		HashMap<String, String> map = new HashMap<>();
	    map.put("key", name+" modified ");
	    map.put("foo", "bar");
	    map.put("aa", "bb");
	    return map;
		
		
    	//return this.sampleService.getSampleResponse();
    }
	
}