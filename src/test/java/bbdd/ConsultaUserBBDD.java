package bbdd;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.User;

class ConsultaUserBBDD {

	User user;
	
	@BeforeEach
	public void inicializate() {
		user=new User("Roberto","Giordano", "rgiordano","rgiordano@al.uloyola.es","qwertyuiop");
	}
	
	@Test
    public void readUserTest() throws SQLException {
        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        Assert.assertEquals(userBBDD.read(user.getUsername(),user.getPassword()),user);
    }
	
	@Test
    public void readAllUsersTest() throws SQLException {
        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        System.out.println(userBBDD.readAll());
    }

}
