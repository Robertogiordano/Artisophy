package manager;

import bbdd.ArtElementType;
import bbdd.ConsultasBBDD;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SeeArtistCommand implements Command {
    private final String n;

    public SeeArtistCommand(String n) {
        this.n = n;
    }

    @Override
    public List<Object> execute() {
        try {
            return Collections.singletonList(ConsultasBBDD.filterArtElementId(ArtElementType.ARTISTS,n));
        } catch (SQLException e) {
            throw new RuntimeException("Impossible get artist by id");
        }
    }
}
