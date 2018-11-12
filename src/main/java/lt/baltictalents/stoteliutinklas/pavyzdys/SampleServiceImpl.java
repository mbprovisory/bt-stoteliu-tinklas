package lt.baltictalents.stoteliutinklas.pavyzdys;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public SampleResponse getSampleResponse() {
		SampleResponse sr = new SampleResponse();
		sr.setWinningSign("Zirkes");
		return sr;
	}

}
