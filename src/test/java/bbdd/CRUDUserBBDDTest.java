package bbdd;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.User;

class CRUDUserBBDDTest {
	
	User user;
	User upUser;
	
	@BeforeEach
	public void inicializate() {
		user=new User("Laura","Ferrer Haba", "lauferhaba","lferhaba@al.uloyola.es","asdfghjkl");
		upUser=new User("Laura","Ferrer Haba", "lauferhaba","lferhaba@al.uloyola.es","zxcvbnm");
	}

    
    
    @Test
    public void createUserTest(){
        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        userBBDD.create(user);
    }

    @Test
    public void updateUserTest() throws SQLException {

        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        userBBDD.update(upUser,"username='"+upUser.getUsername()+"'");
    }

    @Test
    public void deleteUserTest() throws SQLException {
        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        userBBDD.delete(upUser);
    }
    
    @AfterEach
    public void deleteUserBBDD() throws SQLException {
    	CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
    	userBBDD.delete(user);
        userBBDD.delete(upUser);
    }
}
