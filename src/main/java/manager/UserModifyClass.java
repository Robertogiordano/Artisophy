package manager;

import bbdd.ConsultasBBDD;
import dao.User;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserModifyClass implements Command {
    User newUser;

    public UserModifyClass(User newUser) {
        this.newUser = newUser;
    }

    @Override
    public List<Object> execute() {
        try {
            CommandInvoker.setUser(ConsultasBBDD.modifyUserBBDD(CommandInvoker.getUser(),newUser));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Collections.singletonList(newUser);
    }
}
