package manager;

import bbdd.ArtElementType;
import bbdd.ConsultasBBDD;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SeeMuseumCommand implements Command {

        private final String n;

        public SeeMuseumCommand(String n) {
            this.n = n;
        }

        @Override
        public List<Object> execute() {
            try {
                return Collections.singletonList(ConsultasBBDD.filterArtElementId(ArtElementType.MUSEUMS,n));
            } catch (SQLException e) {
                throw new RuntimeException("Impossible get museum by id");
            }
        }
    }