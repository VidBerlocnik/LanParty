import java.util.Date;

public class User {
    String Username;
    Integer Id;
    Integer TeamId;
    String Password;
    String DateOfBirth;
    String Email;
    Character Gender;

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
        DateOfBirth = null;
    }
    public User(String username, String password, String dateofbirth, String email, Character gender){
        Username = username;
        Password = password;
        DateOfBirth = dateofbirth;
        Email = email;
        Gender = gender;
    }
}
