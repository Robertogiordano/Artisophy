package bbdd;

import dao.ArtElement;
import dao.Museum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class CRUDMuseumsBBDD implements DAOInterface {
    private static CRUDMuseumsBBDD instance;

    private static ConnectionManager connection;

    private CRUDMuseumsBBDD(){
        connection=new ConnectionManager();
        connection.getConnection();
    }

    public void closeConnection(){
        connection.closeConnection();
    }
    public static CRUDMuseumsBBDD getInstance() {
        if(instance==null){
            instance=new CRUDMuseumsBBDD();
        }
        return instance;
    }

    private Museum getMuseumElement(ResultSet rs) throws SQLException {
        Museum museum = new Museum(rs.getInt("id"), rs.getString("name"), rs.getString("street"), rs.getString("openingHour"), rs.getString("closingHour"),rs.getString("phone"),rs.getString("description"),rs.getDouble("price"),rs.getString("webpageUrl"),rs.getString("wiki"),rs.getString("googleMaps"));
        return museum;
    }

    @Override
    public void create(ArtElement o) {
        Museum m;

        if(o instanceof Museum){
            m=(Museum)o;
        }else{
            throw new RuntimeException();
        }

        String query = "INSERT INTO Museum (name, street, openingHour, closingHour, phone, description, price, webpageUrl,wiki,googleMaps) VALUES (?, ?, ?,?,?, ?, ?, ?,?,?)";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setString(1, m.getName());
            stmt.setString(2, m.getStreet());
            stmt.setString(3, m.getOpeningHour());
            stmt.setString(4, m.getClosingHour());
            stmt.setString(5, m.getPhone());
            stmt.setString(6, m.getDescription());
            stmt.setDouble(7, m.getPrice());
            stmt.setString(8, m.getWebpageUrl());
            stmt.setString(9, m.getWiki());
            stmt.setString(10, m.getGoogleMaps());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ArtElement> read(String condition) throws SQLException {
        List<ArtElement> museums = new ArrayList<>();
        String query = "SELECT * FROM Museum WHERE "+condition;
        try (PreparedStatement stmt = connection.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                museums.add(getMuseumElement(rs));
            }
        }
        return museums;
    }

    @Override
    public void update(ArtElement o, String condition) {
        Museum m;

        if(o instanceof Museum){
            m=(Museum)o;
        }else{
            throw new RuntimeException();
        }

        String query = "UPDATE Museum SET id=?, name=?, street=?, openingHour=?, closingHour=?, phone=?, description=?, price=?, webpageUrl=?,wiki=?,googleMaps=? WHERE "+condition;
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setInt(1, m.getId());
            stmt.setString(2, m.getName());
            stmt.setString(3, m.getStreet());
            stmt.setString(4, m.getOpeningHour());
            stmt.setString(5, m.getClosingHour());
            stmt.setString(6, m.getPhone());
            stmt.setString(7, m.getDescription());
            stmt.setDouble(8, m.getPrice());
            stmt.setString(9, m.getWebpageUrl());
            stmt.setString(10, m.getWiki());
            stmt.setString(11, m.getGoogleMaps());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ArtElement o) {
        Museum m;

        if(o instanceof Museum){
            m=(Museum)o;
        }else{
            throw new RuntimeException();
        }

        String query = "DELETE FROM Museum WHERE id=? and name=? and street=? and openingHour=? and closingHour=? and phone=? and description=? and price=? and webpageUrl=? and wiki=? and googleMaps=?";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setInt(1, m.getId());
            stmt.setString(2, m.getName());
            stmt.setString(3, m.getStreet());
            stmt.setString(4, m.getOpeningHour());
            stmt.setString(5, m.getClosingHour());
            stmt.setString(6, m.getPhone());
            stmt.setString(7, m.getDescription());
            stmt.setDouble(8, m.getPrice());
            stmt.setString(9, m.getWebpageUrl());
            stmt.setString(10, m.getWiki());
            stmt.setString(11, m.getGoogleMaps());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
