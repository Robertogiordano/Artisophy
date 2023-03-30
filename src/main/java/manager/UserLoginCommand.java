package manager;

import bbdd.ConsultasBBDD;
import dao.User;

import java.util.Collections;
import java.util.List;

public class UserLoginCommand implements Command {

    private User user;

    public UserLoginCommand(User user) {
        this.user = user;
    }

    @Override
    public List<Object> execute() {
        if(ConsultasBBDD.checkLogin(user)!=null){
            CommandInvoker.setUser(user);
            System.out.println("User trovato");
            return Collections.singletonList(user);
        }

        System.out.println("User NON trovato");
        return null;
    }
}
