package manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.User;

public class CommandInvoker {

    private static CommandInvoker instance;
    private final Map<CommandsType, Command> commands = new HashMap<>();

    private String artist_ActiveId=null;
    private String artwork_ActiveId=null;
    private String museum_ActiveId=null;
    private static User user=null;

    public CommandInvoker(User user) {
        commands.put(CommandsType.ARTISTS, new GetAllArtistsCommand());
        commands.put(CommandsType.MUSEUM_GUIDE, new GetAllMuseumsCommand());
        commands.put(CommandsType.ART_GALERY, new GetAllArtworksCommand());
        commands.put(CommandsType.SEE_ARTIST, new SeeArtistCommand(artist_ActiveId));
        commands.put(CommandsType.SEE_MUSEUM, new SeeMuseumCommand(museum_ActiveId));
        commands.put(CommandsType.SEE_ARTWORK, new SeeArtworkCommand(artwork_ActiveId));
        commands.put(CommandsType.LOGIN,new UserLoginCommand(user));
        commands.put(CommandsType.REGISTER,new UserRegisterCommand(user));
        commands.put(CommandsType.MODIFY_USER,new UserModifyClass(user));
        commands.put(CommandsType.LOGOUT,new UserLogoutCommand());
    }

    public static CommandInvoker getInstance(User user){
        if(instance==null){
            instance=new CommandInvoker(user);
        }
        return instance;
    }

    public static void releaseInstance() {
        CommandInvoker.instance = null;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        CommandInvoker.user = user;
    }

    public String getArtist_ActiveId() {
        return artist_ActiveId;
    }

    public void setArtist_ActiveId(String artist_ActiveId) {
        commands.put(CommandsType.SEE_ARTIST, new SeeArtistCommand(artist_ActiveId));
        this.artist_ActiveId = artist_ActiveId;
    }

    public String getArtwork_ActiveId() {
        return artwork_ActiveId;
    }

    public void setArtwork_ActiveId(String artwork_ActiveId) {
        commands.put(CommandsType.SEE_ARTWORK, new SeeArtworkCommand(artwork_ActiveId));
        this.artwork_ActiveId = artwork_ActiveId;
    }

    public String getMuseum_ActiveId() {
        return museum_ActiveId;
    }

    public void setMuseum_ActiveId(String museum_ActiveId) {
        commands.put(CommandsType.SEE_MUSEUM, new SeeMuseumCommand(museum_ActiveId));
        this.museum_ActiveId = museum_ActiveId;
    }

    public List<Object> executeCommand(CommandsType commandName) {
        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Unknown command: " + commandName);
            return null;
        } else {
            return command.execute();
        }
    }
}
