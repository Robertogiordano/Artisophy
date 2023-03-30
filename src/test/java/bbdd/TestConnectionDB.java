package bbdd;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.Artist;
import dao.User;

class TestConnectionDB {

	 @Test
	 public void connectionTest(){
	 }

	   @Test
	    public void getAllUsersTest() throws SQLException {
	       List<User> users=ConsultasBBDD.getUsers();
	       System.out.println(users);
	   }

	   @Test
	   public void readUserTest() throws SQLException {
	       User user=new User("Roberto","Giordano", "rgiordano","rgiordano@al.uloyola.es","qwertyuiop");

	       CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
	       System.out.println(userBBDD.read(user.getUsername(), user.getPassword()));
	   }

	   @Test
	    public void checkLoginTest(){
	       User user=new User("Roberto","Giordano", "rgiordano","rgiordano@al.uloyola.es","qwertyuiop");
	       System.out.println(ConsultasBBDD.checkLogin(user));
	   }

	   @Test
	    public void insertArtistTest(){
	       Artist picasso=new Artist(0,"Pablo Picasso",1881,1973,"Pablo Ruiz y Picasso, simply known as Pablo Picasso, was a Spanish painter, sculptor and lithographer, among the most influential of the 20th century","https://en.wikipedia.org/wiki/Pablo_Picasso");
	       Artist dali=new Artist(1,"Salvador Dalí",1904,1989,"Salvador Dalí, Marquis of Pùbol, born Salvador Domingo Felipe Jacinto Dalí i Domènech, was a Spanish painter, sculptor, writer, photographer, filmmaker, designer, screenwriter and mystic","https://en.wikipedia.org/wiki/Salvador_Dal%C3%AD");

	       CRUDArtistsBBDD artistBBDD=CRUDArtistsBBDD.getInstance();
	       artistBBDD.create(picasso);
	       artistBBDD.create(dali);
	   }

	   @Test
	    public void updateArtistTest(){
	        Artist picasso=new Artist(0,"Pablo Picasso",1881,1973,"Pablo Ruiz y Picasso, simply known as Pablo Picasso, was a Spanish painter, sculptor and lithographer, among the most influential of the 20th century","https://en.wikipedia.org/wiki/Pablo_Picasso");
	        Artist dali=new Artist(1,"Salvador Dalí",1904,1989,"Salvador Dalí, Marquis of Pùbol, born Salvador Domingo Felipe Jacinto Dalí i Domènech, was a Spanish painter, sculptor, writer, photographer, filmmaker, designer, screenwriter and mystic","https://en.wikipedia.org/wiki/Salvador_Dal%C3%AD");

	        CRUDArtistsBBDD artistBBDD=CRUDArtistsBBDD.getInstance();
	        artistBBDD.update(picasso,"name='"+picasso.getName()+"'");
	        artistBBDD.update(dali,"name='"+dali.getName()+"'");
	    }

}
