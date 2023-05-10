package bbdd;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.Artist;

class CRUDArtitsBBDDTest {

	static Artist artist;
	static Artist upArtist;
	
	@BeforeEach
	public void crearArtist() {
		artist = new Artist(3, "Leonardo da Vinci","Leonardo","Da Vinci",1853,1890,"Leonardo da Vinci (Leonardo di ser Piero da Vinci) Loudspeaker.svg escuchar (Vinci, 15 de abril de 14522​-Amboise, 2 de mayo de 1519) fue un polímata florentino del Renacimiento italiano. Fue a la vez pintor, anatomista, arquitecto, paleontólogo,3​ botánico, escritor, escultor, filósofo, ingeniero, inventor, músico, poeta y urbanista.","https://en.wikipedia.org/wiki/Leonardo da Vinci");
		upArtist = new Artist(3, "Leonardo da Vinci","Leonardo","Da Vinci",1853,1890,"Leonardo da Vinci (Leonardo di ser Piero da Vinci) Loudspeaker.svg escuchar (Vinci, 15 de abril de 14522​-Amboise, 2 de mayo de 1519) fue un polímata florentino del Renacimiento italiano. Fue a la vez pintor, anatomista, arquitecto, paleontólogo,3​ botánico, escritor, escultor, filósofo, ingeniero, inventor, músico, poeta y urbanista.","https://en.wikipedia.org/wiki/Leonardo da Vinci");
	}
	
	@Test
    public void createArtistTest(){
		CRUDArtistsBBDD artistsBBDD=CRUDArtistsBBDD.getInstance();
		artistsBBDD.create(artist);
	}
	
	@Test
	public void updateArtistTest() throws SQLException {
		CRUDArtistsBBDD artistsBBDD=CRUDArtistsBBDD.getInstance();
		artistsBBDD.update(upArtist,"name='"+artist.getName()+"'");
	}
	
	@Test
	public void deleteArtistTest() throws SQLException {
		CRUDArtistsBBDD artistsBBDD=CRUDArtistsBBDD.getInstance();
		artistsBBDD.delete(artist);
		artistsBBDD.delete(upArtist);
	}
	
	@AfterEach
	public void deleteArtistBBDD() throws SQLException {
		CRUDArtistsBBDD artistsBBDD=CRUDArtistsBBDD.getInstance();
		artistsBBDD.delete(artist);
		artistsBBDD.delete(upArtist);
	}
}

