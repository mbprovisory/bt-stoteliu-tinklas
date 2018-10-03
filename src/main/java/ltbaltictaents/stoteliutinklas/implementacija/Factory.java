package ltbaltictaents.stoteliutinklas.implementacija;

import ltbaltictaents.stoteliutinklas.interfeisai.SampleService;

public class Factory {
	public SampleService getSampleService() {
		return new SampleServiceImpl();
	}
}
