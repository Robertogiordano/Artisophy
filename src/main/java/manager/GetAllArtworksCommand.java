package manager;

import bbdd.ArtElementType;
import bbdd.ConsultasBBDD;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class GetAllArtworksCommand implements Command {
    @Override
    public List<Object> execute() {
        try {
            return Collections.singletonList(ConsultasBBDD.getArtElements(ArtElementType.ARTWORKS));
        } catch (SQLException e) {
            throw new RuntimeException("Impossible get all artworks");
        }
    }
}