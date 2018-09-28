package lt.baltictalents.stoteliutinklas.data.beans;

public class Stotele {
	String pavadinimas;
	String lon;
	String lat;
	String[] marsrutai;
	
	
	
	public Stotele(String pavadinimas, String lon, String lat, String[] marsrutai) {
		super();
		this.pavadinimas = pavadinimas;
		this.lon = lon;
		this.lat = lat;
		this.marsrutai = marsrutai;
	}
	public String getPavadinimas() {
		return pavadinimas;
	}
	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String[] getMarsrutai() {
		return marsrutai;
	}
	public void setMarsrutai(String[] marsrutai) {
		this.marsrutai = marsrutai;
	}
}