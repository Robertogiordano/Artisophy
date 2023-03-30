package bbdd;

import dao.ArtElement;
import dao.Artwork;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CRUDArtworksBBDD implements DAOInterface {
    private static CRUDArtworksBBDD instance;

    private static ConnectionManager connection;

    private CRUDArtworksBBDD(){
        connection=new ConnectionManager();
        connection.getConnection();
    }

    public void closeConnection(){
        connection.closeConnection();
    }
    public static CRUDArtworksBBDD getInstance() {
        if(instance==null){
            instance=new CRUDArtworksBBDD();
        }
        return instance;
    }

    private Artwork getArtworkElement(ResultSet rs) throws SQLException {
        Artwork artwork = new Artwork(rs.getInt("id"), rs.getString("imgPath"), rs.getString("name"), rs.getInt("year"),rs.getInt("autor_id"), rs.getInt("museum_id"),rs.getString("description"),rs.getString("wiki"));
        return artwork;
    }

    @Override
    public void create(ArtElement o) {
        Artwork a;

        if(o instanceof Artwork){
            a=(Artwork) o;
        }else{
            throw new RuntimeException("Wrong instance");
        }

        String query = "INSERT INTO Artwork (imgPath, name, year, autor_id, museum_id, description, wiki) VALUES ( ?, ?, ?, ?,?,?,?)";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setString(1, a.getImgPath());
            stmt.setString(2, a.getName());
            stmt.setInt(3, a.getYear());
            stmt.setInt(4, a.getAutor_id());
            stmt.setInt(5, a.getMuseum_id());
            stmt.setString(6, a.getDescription());
            stmt.setString(7, a.getWiki());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ArtElement> read(String condition) throws SQLException {
        List<ArtElement> artworks = new ArrayList<>();
        String query = "SELECT * FROM Artwork WHERE "+condition;
        try (PreparedStatement stmt = connection.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                artworks.add(getArtworkElement(rs));
            }
        }
        return artworks;
    }

    @Override
    public void update(ArtElement o, String condition) {
        Artwork a;

        if(o instanceof Artwork){
            a=(Artwork) o;
        }else{
            throw new RuntimeException();
        }

        System.out.println("Id è "+a.getAutor_id());

        String query = "UPDATE Artwork SET id=?, imgPath=?, name=?, year=?, autor_id=?, museum_id=?, description=?, wiki=? WHERE "+condition;
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getImgPath());
            stmt.setString(3, a.getName());
            stmt.setInt(4, a.getYear());
            stmt.setInt(5, a.getAutor_id());
            stmt.setInt(6, a.getMuseum_id());
            stmt.setString(7, a.getDescription());
            stmt.setString(8, a.getWiki());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ArtElement o) {
        Artwork a;

        if(o instanceof Artwork){
            a=(Artwork) o;
        }else{
            throw new RuntimeException();
        }

        String query = "DELETE FROM Artwork WHERE id=? and imgPath=? and name=? and year=? and autor_id=? and museum_id=? and description=? and wiki=?";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getImgPath());
            stmt.setString(3, a.getName());
            stmt.setInt(4, a.getYear());
            stmt.setInt(5, a.getAutor_id());
            stmt.setInt(6, a.getMuseum_id());
            stmt.setString(7, a.getDescription());
            stmt.setString(8, a.getWiki());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
