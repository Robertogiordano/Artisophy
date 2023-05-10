package bbdd;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.User;

class ConsultaUserBBDD {

	User user;
	
	@BeforeEach
	public void initialize() {
		user=new User("Roberto","Giordano", "rgiordano","rgiordano@al.uloyola.es","qwertyuiop");
	}
	
	@Test
    public void readUserTest() throws SQLException {
        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        Assert.assertNotEquals(userBBDD.read(user.getUsername(),user.getPassword()),user);
    }
	
	@Test
    public void readAllUsersTest() throws SQLException {
        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        Assert.assertNotEquals(userBBDD.readAll().size(),0);
    }

    @Test
    public void checkLoginTest() throws SQLException{
        User rg=new User("rgiordano","qwertyuiop");
        User user=ConsultasBBDD.checkLogin(rg);
        Assert.assertEquals(rg.getUsername(),user.getUsername());
        Assert.assertEquals(rg.getPassword(),user.getPassword());
    }

}
