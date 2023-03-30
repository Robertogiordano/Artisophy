package manager;

import java.util.List;

public class UserLogoutCommand implements Command {
    @Override
    public List<Object> execute() {
        CommandInvoker.setUser(null);
        return null;
    }
}
