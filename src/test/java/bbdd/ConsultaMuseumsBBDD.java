package bbdd;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Assert;
import dao.Museum;

class ConsultaMuseumsBBDD {
	
	static Museum museum;
	
	@Test
    public void readMuseumTest() throws SQLException {
        CRUDMuseumsBBDD museumsBBDD=CRUDMuseumsBBDD.getInstance();
        Assert.assertNotEquals(museumsBBDD.read("name='"+museum.getName()+"'").get(0),null);
    }    
	
	@Test
    public void readAllMuseumTest() throws SQLException {
        CRUDMuseumsBBDD museumsBBDD=CRUDMuseumsBBDD.getInstance();
        Assert.assertNotEquals(museumsBBDD.read("1").size(),0);
    }

}
