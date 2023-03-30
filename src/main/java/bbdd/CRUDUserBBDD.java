package bbdd;

import dao.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CRUDUserBBDD {
    private static CRUDUserBBDD instance;

    private static ConnectionManager connection;

    private CRUDUserBBDD(){
        connection=new ConnectionManager();
        connection.getConnection();
    }

    public void closeConnection(){
        connection.closeConnection();
    }
    public static CRUDUserBBDD getInstance() {
        if(instance==null){
            instance=new CRUDUserBBDD();
        }
        return instance;
    }

    private User getUserElement(ResultSet rs) throws SQLException {
        User user = new User(rs.getString("name"), rs.getString("surnames"), rs.getString("username"), rs.getString("email"),rs.getString("password"));
        return user;
    }


    public void create(User u) {
        String query = "INSERT INTO User (name,surnames,username,email,password) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurnames());
            stmt.setString(3, u.getUsername());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getPassword());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public User read(String username, String password) throws SQLException {
        String query = "SELECT * FROM User WHERE username='"+username+"' and password='"+password+"'";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return getUserElement(rs);
            }else{
                throw new RuntimeException();
            }
        }
    }
    public void update(User u, String condition) {
        String query = "UPDATE User SET name=?, surnames=?, username=?, email=?, password=? WHERE "+condition;
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurnames());
            stmt.setString(3, u.getUsername());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getPassword());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(User u) {

        String query = "DELETE FROM User WHERE name=? and surnames=? and username=? and email=? and password=?";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurnames());
            stmt.setString(3, u.getUsername());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getPassword());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> readAll() {
        List<User> users=new ArrayList<>();
        String query = "SELECT * FROM User WHERE 1";
        try (PreparedStatement stmt = connection.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(getUserElement(rs));
            }
        }catch (Exception e){
            throw new RuntimeException("Error read all users");
        }
        return users;
    }
}
