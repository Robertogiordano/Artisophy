package bbdd;



import dao.ArtElement;
import dao.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasBBDD {

    public static List<ArtElement> getArtElements(ArtElementType category) throws SQLException {
        List<ArtElement> artElements=new ArrayList<>();

        switch (category){
            case ARTISTS:
                CRUDArtistsBBDD artistsBBDD= CRUDArtistsBBDD.getInstance();
                artElements.addAll(artistsBBDD.read("1"));
                artistsBBDD.closeConnection();
                break;
            case MUSEUMS:
                CRUDMuseumsBBDD museumsBBDD= CRUDMuseumsBBDD.getInstance();
                artElements.addAll(museumsBBDD.read("1"));
                museumsBBDD.closeConnection();
                break;
            case ARTWORKS:
                CRUDArtworksBBDD artworksBBDD= CRUDArtworksBBDD.getInstance();
                artElements.addAll(artworksBBDD.read("1"));
                artworksBBDD.closeConnection();
                break;
        }

        return artElements;
    }

    public static List<ArtElement> filterArtElementName(ArtElementType category, String condition) throws SQLException{
        List<ArtElement> artElements=new ArrayList<>();

        switch (category){
            case ARTISTS:
                CRUDArtistsBBDD artistsBBDD= CRUDArtistsBBDD.getInstance();
                artElements.addAll(artistsBBDD.read("name='"+condition+"'"));
                artistsBBDD.closeConnection();
                break;
            case MUSEUMS:
                CRUDMuseumsBBDD museumsBBDD= CRUDMuseumsBBDD.getInstance();
                artElements.addAll(museumsBBDD.read("name='"+condition+"'"));
                museumsBBDD.closeConnection();
                break;
            case ARTWORKS:
                CRUDArtworksBBDD artworksBBDD= CRUDArtworksBBDD.getInstance();
                artElements.addAll(artworksBBDD.read("name='"+condition+"'"));
                artworksBBDD.closeConnection();
                break;
        }

        return artElements;
    }

    public static List<ArtElement> filterArtElementId(ArtElementType category, String condition) throws SQLException{
        List<ArtElement> artElements=new ArrayList<>();

        switch (category){
            case ARTISTS:
                CRUDArtistsBBDD artistsBBDD= CRUDArtistsBBDD.getInstance();
                artElements.addAll(artistsBBDD.read("id='"+condition+"'"));
                artistsBBDD.closeConnection();
                break;
            case MUSEUMS:
                CRUDMuseumsBBDD museumsBBDD= CRUDMuseumsBBDD.getInstance();
                artElements.addAll(museumsBBDD.read("id='"+condition+"'"));
                museumsBBDD.closeConnection();
                break;
            case ARTWORKS:
                CRUDArtworksBBDD artworksBBDD= CRUDArtworksBBDD.getInstance();
                artElements.addAll(artworksBBDD.read("id='"+condition+"'"));
                artworksBBDD.closeConnection();
                break;
        }

        return artElements;
    }
    
    public static List<ArtElement> filterArtElement(ArtElementType category, String condition) throws SQLException{
        List<ArtElement> artElements=new ArrayList<>();

        switch (category){
            case ARTISTS:
                CRUDArtistsBBDD artistsBBDD= CRUDArtistsBBDD.getInstance();
                artElements.addAll(artistsBBDD.read(condition));
                artistsBBDD.closeConnection();
                break;
            case MUSEUMS:
                CRUDMuseumsBBDD museumsBBDD= CRUDMuseumsBBDD.getInstance();
                artElements.addAll(museumsBBDD.read(condition));
                museumsBBDD.closeConnection();
                break;
            case ARTWORKS:
                CRUDArtworksBBDD artworksBBDD= CRUDArtworksBBDD.getInstance();
                artElements.addAll(artworksBBDD.read(condition));
                artworksBBDD.closeConnection();
                break;
        }

        return artElements;
    }

    public static List<User> getUsers() throws SQLException {
        List<User> users=new ArrayList<>();

        CRUDUserBBDD userBBDD= CRUDUserBBDD.getInstance();
        users.addAll(userBBDD.readAll());
        userBBDD.closeConnection();

        return users;
    }

    public static User checkLogin(User user){
        try {
            CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
            User responseUser=userBBDD.read(user.getUsername(), user.getPassword());
            userBBDD.closeConnection();
            return responseUser;
        } catch (SQLException e) {
            return null;
        }
    }

    public static Boolean registerUserBBDD(User user){
        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        userBBDD.create(user);
        userBBDD.closeConnection();
        return true;
    }

    public static User modifyUserBBDD(User oldUser,User newUser) throws SQLException {
        CRUDUserBBDD userBBDD=CRUDUserBBDD.getInstance();
        userBBDD.update(newUser,"username='"+oldUser.getUsername()+"'");
        userBBDD.closeConnection();

        userBBDD=CRUDUserBBDD.getInstance();
        User user= userBBDD.read(newUser.getUsername(),newUser.getPassword());
        userBBDD.closeConnection();

        return user;

    }
}
