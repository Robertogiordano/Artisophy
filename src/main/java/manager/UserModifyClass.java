package manager;

import bbdd.ConsultasBBDD;
import dao.User;

import java.util.Collections;
import java.util.List;

public class UserModifyClass implements Command {
    User newUser;

    public UserModifyClass(User newUser) {
        this.newUser = newUser;
    }

    @Override
    public List<Object> execute() {
            CommandInvoker.setUser(ConsultasBBDD.modifyUserBBDD(CommandInvoker.getUser(),newUser));
            return Collections.singletonList(newUser);
    }
}
