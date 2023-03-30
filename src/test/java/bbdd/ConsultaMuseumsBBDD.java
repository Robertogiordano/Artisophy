package bbdd;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Assert;
import dao.Museum;

class ConsultaMuseumsBBDD {
	
	static Museum museum;
	
	@BeforeEach
	public void inizializateMuseums() {
		museum = new Museum(2,"National gallery", "Trafalgar Square","10am","6pm","",
				"The National Gallery is an art museum in Trafalgar Square in the City of Westminster, in Central London, England.",
				0.0,"https://www.nationalgallery.org.uk","https://en.wikipedia.org/wiki/National_Gallery",
				"https://www.google.com/maps/place/51°30'32.1%22N+0°07'41.9%22W/@51.508929,-0.128299,15z/data=!4m4!3m3!8m2!3d51.508929!4d-0.128299?hl=en");
	}
	
	@Test
    public void readMuseumTest() throws SQLException {
        CRUDMuseumsBBDD museumsBBDD=CRUDMuseumsBBDD.getInstance();
        Assert.assertEquals(museumsBBDD.read("name='"+museum.getName()+"'").get(0),museum);
    }    
	
	@Test
    public void readAllMuseumTest() throws SQLException {
        CRUDMuseumsBBDD museumsBBDD=CRUDMuseumsBBDD.getInstance();
        museumsBBDD.read("1").stream().forEach(a -> System.out.println(a));
    }

}
