package manager;

import java.sql.SQLException;
import java.util.List;

public interface Command {
    List<Object> execute() throws SQLException;
}
