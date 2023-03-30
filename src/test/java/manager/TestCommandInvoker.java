/*package manager;

import dao.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class TestCommandInvoker {
    User user=new User("Roberto","Giordano", "rgiordano","rgiordano@al.uloyola.es","qwertyuiop");
    CommandInvoker commandInvoker=CommandInvoker.getInstance(user);
    
    @Before
    public void init() {
    CommandInvoker.setArtwork_ActiveId(1);
    }
    @Test
    public void registerTest() throws SQLException {
        commandInvoker.executeCommand(CommandsType.REGISTER);
        Assert.assertEquals(CommandInvoker.getUser(),user);
    }

    @Test
    public void logoutTest(){
        commandInvoker.executeCommand(CommandsType.LOGOUT);
        Assert.assertEquals(CommandInvoker.getUser(),null);
        CommandInvoker.releaseInstance();
    }

    @Test
    public void loginTest() {
        commandInvoker=CommandInvoker.getInstance(user);
        commandInvoker.executeCommand(CommandsType.LOGIN);
        Assert.assertEquals(CommandInvoker.getUser(),user);
    }

    @Test
    public void getAllArtists(){
        Assert.assertNotEquals(commandInvoker.executeCommand(CommandsType.SEE_ARTIST),1);
    }
}
*/