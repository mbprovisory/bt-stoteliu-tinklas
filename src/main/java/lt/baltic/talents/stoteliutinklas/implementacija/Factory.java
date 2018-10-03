package lt.baltic.talents.stoteliutinklas.implementacija;

import lt.baltic.talents.stoteliutinklas.interfeisai.SampleService;

public class Factory {
	public SampleService getSampleService() {
		return new SampleServiceImpl();
	}
}
