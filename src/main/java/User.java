public class User {
    String Username;
    Integer Id;
    Integer TeamId;
    String Password;

    public User(String username, String password, Integer id, Integer teamId){
        Username = username;
        Id = id;
        TeamId = teamId;
        Password = password;
    }

    public User(String username, String password){
        Username = username;
        Password = password;
    }

    public User(){
        Username = null;
        Id = null;
        TeamId = null;
        Password = null;
    }
}
