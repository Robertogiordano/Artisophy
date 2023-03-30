package bbdd;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.Artwork;

class CRUDArtWorkBBDDTest {

	static Integer artist_id;
    static Integer museum_id;
    static Artwork  artwork;
    static Artwork upArtwork;
    
    @BeforeEach
    public void initialize() throws SQLException {
    	artist_id=7;
    	museum_id=3;    	
    	
    	artwork=new Artwork(1, "img1","Meninas",1656,artist_id,museum_id,
    			"Las meninas o La familia de Felipe IV se considera la obra maestra del pintor del Siglo de Oro español Diego Velázquez.","https://en.wikipedia.org/wiki/Las_meninas");
        upArtwork=new Artwork(1, "img1","Meninas",1656,artist_id,museum_id, 
        		"Las meninas o La familia de Felipe IV se considera la obra maestra del pintor del Siglo de Oro español Diego Velázquez. Acabado en 1656, según Antonio Palomino, fecha unánimemente aceptada por la crítica, corresponde al último periodo estilístico del artista, el de plena madurez.","https://en.wikipedia.org/wiki/Las_meninas");
    }


    @Test
    public void createArtworkTest(){
        CRUDArtworksBBDD artworksBBDD=CRUDArtworksBBDD.getInstance();
        artworksBBDD.create(artwork);
    }

    @Test
    public void updateArtworkTest() throws SQLException {
        CRUDArtworksBBDD artworksBBDD=CRUDArtworksBBDD.getInstance();
        artworksBBDD.update(upArtwork,"name='"+artwork.getName()+"'");
    }

    @Test
    public void deleteArtworkTest() throws SQLException {
        CRUDArtworksBBDD artworksBBDD=CRUDArtworksBBDD.getInstance();
        artworksBBDD.delete(artwork);
        artworksBBDD.delete(upArtwork);
    }
    
    @AfterEach
	public void deleteArtistBBDD() throws SQLException {
    	CRUDArtworksBBDD artworksBBDD=CRUDArtworksBBDD.getInstance();
    	artworksBBDD.delete(artwork);
    	artworksBBDD.delete(upArtwork);
	}

}
