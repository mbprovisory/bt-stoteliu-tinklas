package lt.baltictalents.stoteliutinklas.implementacija;

import lt.baltictalents.stoteliutinklas.interfeisai.SampleService;

public class Factory {
	public SampleService getSampleService() {
		return new SampleServiceImpl();
	}
}