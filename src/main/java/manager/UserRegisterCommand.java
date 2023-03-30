package manager;

import bbdd.ConsultasBBDD;
import dao.User;

import java.util.Collections;
import java.util.List;

public class UserRegisterCommand implements Command {

    private User user;

    public UserRegisterCommand(User user) {
        this.user = user;
    }

    @Override
    public List<Object> execute() {
        ConsultasBBDD.registerUserBBDD(user); //register the user in the database, if there is an error, is managed internally
        CommandInvoker.setUser(user); //set the user just register as logged.
        return Collections.singletonList(user);
    }
}
