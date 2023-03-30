package bbdd;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.Artwork;

class ConsultaArtWorkBBDD {

	static Integer artist_id;
    static Integer museum_id;
    static Artwork artwork;
    static Artwork upArtwork;
    
    @BeforeEach
    public void initialize() throws SQLException {
    	artist_id=5;
    	museum_id=2;    	
    	
    	artwork=new Artwork(1, "img1","Sunflower",1888,artist_id,museum_id,"unflowers (original title, in French: Tournesols) is the title of two series of still life paintings by the Dutch painter Vincent van Gogh.","https://en.wikipedia.org/wiki/Sunflowers_(Van_Gogh_series)");
    }
    
    @Test
    public void readAllArtworkTest() throws SQLException {
        CRUDArtworksBBDD artworksBBDD=CRUDArtworksBBDD.getInstance();
        artworksBBDD.read("1").stream().forEach(a -> System.out.println(a));
    }
    
	@Test
    public void readArtworkTest() throws SQLException {
        CRUDArtworksBBDD artworksBBDD=CRUDArtworksBBDD.getInstance();
        Assert.assertEquals(artworksBBDD.read("name='"+artwork.getName()+"'").get(0),artwork);
    }
}
