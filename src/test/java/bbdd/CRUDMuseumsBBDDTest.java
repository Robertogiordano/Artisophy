package bbdd;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.Museum;

class CRUDMuseumsBBDDTest {

	static Museum museum;
	static Museum upMuseum;
	
	
	@BeforeEach
	public void inizializateMuseums() {
		museum = new Museum(1,"Louvre", "Paris","9am","6pm","",
				"El Museo del Louvre es el museo nacional de Francia consagrado tanto a las bellas artes como a la arqueología y las artes decorativas anteriores al impresionismo. Está ubicado en París, la capital del país, en el antiguo palacio real del Louvre.",
				0.0,"https://www.louvre.fr/es","https://en.wikipedia.org/wiki/Louvre",
				"https://www.google.com/maps/place/Museo+del+Louvre/@48.8606111,2.337644,15z/data=!4m2!3m1!1s0x0:0xb975fcfa192f84d4?sa=X&ved=2ahUKEwj-2KSK-oD-AhUCUqQEHXnAB7gQ_BJ6BAhGEAg");
		upMuseum = new Museum(1,"Louvre", "Paris","9am","6pm","",
				"El Museo del Louvre es el museo nacional de Francia consagrado tanto a las bellas artes como a la arqueología y las artes decorativas anteriores al impresionismo. Está ubicado en París, la capital del país, en el antiguo palacio real del Louvre.",
				0.0,"https://www.louvre.fr/es","https://en.wikipedia.org/wiki/Louvre",
				"https://www.google.com/maps/place/Museo+del+Louvre/@48.8606111,2.337644,15z/data=!4m2!3m1!1s0x0:0xb975fcfa192f84d4?sa=X&ved=2ahUKEwj-2KSK-oD-AhUCUqQEHXnAB7gQ_BJ6BAhGEAg");
	}

    @Test
    public void MuseumTest(){
        CRUDMuseumsBBDD museumsBBDD=CRUDMuseumsBBDD.getInstance();
        museumsBBDD.create(museum);
    }

    @Test
    public void updateArtistTest() throws SQLException {

        CRUDMuseumsBBDD museumsBBDD=CRUDMuseumsBBDD.getInstance();
        museumsBBDD.update(upMuseum,"name='"+museum.getName()+"'");
    }

    @Test
    public void deleteMuseumTest() throws SQLException {
        CRUDMuseumsBBDD museumsBBDD=CRUDMuseumsBBDD.getInstance();
        museumsBBDD.delete(upMuseum);
    }
    
    @AfterEach
	public void deleteMuseumBBDD() throws SQLException {
    	CRUDMuseumsBBDD museumsBBDD=CRUDMuseumsBBDD.getInstance();
    	museumsBBDD.delete(museum);
    	museumsBBDD.delete(upMuseum);
	}

}
