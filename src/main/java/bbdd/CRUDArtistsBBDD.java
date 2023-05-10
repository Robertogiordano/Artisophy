package bbdd;


import dao.ArtElement;
import dao.Artist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CRUDArtistsBBDD implements DAOInterface {
    private static CRUDArtistsBBDD instance;
    private ConnectionManager connection;

    private CRUDArtistsBBDD(){
        connection=new ConnectionManager();
        connection.getConnection();
    }
    public void closeConnection(){
        connection.closeConnection();
        instance=null;
    }
    public static CRUDArtistsBBDD getInstance() {
        if(instance==null){
            instance=new CRUDArtistsBBDD();
        }
        return instance;
    }

    private Artist getArtistElement(ResultSet rs) throws SQLException {
        Artist artist = new Artist(rs.getInt("id"), rs.getString("name_artist"), rs.getString("name"), rs.getString("surname"), rs.getInt("birthYear"), rs.getInt("deathYear"), rs.getString("description"),rs.getString("wiki"));
        return artist;
    }

    @Override
    public void create(ArtElement o) {
        Artist a;

        if(o instanceof Artist){
            a=(Artist) o;
        }else{
            throw new RuntimeException();
        }

        String query = "INSERT INTO Artist (name_artist, name, surname, birthYear, deathYear, description, wiki) VALUES ( ?,?,?, ?, ?,?,?)";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setString(1, a.getNameArtist());
            stmt.setString(2, a.getName());
            stmt.setString(3, a.getSurname());
            stmt.setInt(4, a.getBirthYear());
            stmt.setInt(5, a.getDeathYear());
            stmt.setString(6, a.getDescription());
            stmt.setString(7, a.getWiki());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ArtElement> read(String condition) throws SQLException {
        List<ArtElement> artists = new ArrayList<>();
        String query = "SELECT * FROM Artist WHERE "+condition;
        try (PreparedStatement stmt = connection.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                artists.add(getArtistElement(rs));
            }
        }
        return artists;
    }

    @Override
    public void update(ArtElement o, String condition) {
        Artist a;

        if(o instanceof Artist){
            a=(Artist) o;
        }else{
            throw new RuntimeException();
        }

        String query = "UPDATE Artist SET id=?, name_artist=?, name=?, surname=?, birthYear=?, deathYear=?, description=?, wiki=? WHERE "+condition;
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getNameArtist());
            stmt.setString(3, a.getName());
            stmt.setString(4, a.getSurname());
            stmt.setInt(5, a.getBirthYear());
            stmt.setInt(6, a.getDeathYear());
            stmt.setString(7, a.getDescription());
            stmt.setString(8, a.getWiki());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ArtElement o) {
        Artist a;

        if(o instanceof Artist){
            a=(Artist) o;
        }else{
            throw new RuntimeException();
        }

        String query = "DELETE FROM Artist WHERE id=? and name_artist=?";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getNameArtist());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
