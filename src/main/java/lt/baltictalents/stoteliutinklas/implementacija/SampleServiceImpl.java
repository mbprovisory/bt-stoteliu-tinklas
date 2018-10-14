package lt.baltictalents.stoteliutinklas.implementacija;

import org.springframework.stereotype.Service;

import lt.baltictalents.stoteliutinklas.api.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public String getHello() {
		return "labas, matau, kad susikompiliavai ir pasileidai ðá karkasà Java1 kursui, sveikinu.";
	}

}
