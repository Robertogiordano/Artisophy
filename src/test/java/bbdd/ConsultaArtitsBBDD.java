package bbdd;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dao.Artist;

class ConsultaArtitsBBDD {

	static Artist artist;
	
	@BeforeEach
	public void crearArtist() {
		artist = new Artist(0,"Pablo Picasso","Pablo","Picasso",1881,1973,"Pablo Ruiz y Picasso, simply known as Pablo Picasso, was a Spanish painter, sculptor and lithographer, among the most influential of the 20th century","https://en.wikipedia.org/wiki/Pablo_Picasso");
	}

	@Test
	public void readAllArtistsTest() throws SQLException {
		CRUDArtistsBBDD artistsBBDD=CRUDArtistsBBDD.getInstance();
		artistsBBDD.read("1").stream().forEach(a -> System.out.println(a));
	}
	
	@Test
	public void readArtistTest() throws SQLException {
		CRUDArtistsBBDD artistsBBDD=CRUDArtistsBBDD.getInstance();
		Assert.assertEquals(artistsBBDD.read("name='"+artist.getName()+"'").get(0),artist);
	}

}
